package com.lp.github_kotlin_repos.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lp.domain.model.GithubKotlinReposModel
import com.lp.domain.usecase.GetKotlinRepositoriesUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest

class GithubRepositoriesPagingSource(
    private val language: String,
    private val sort: String,
    private val getKotlinRepositoriesUseCase: GetKotlinRepositoriesUseCase
) : PagingSource<Int, GithubKotlinReposModel>() {

    private val initialPageIndex: Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubKotlinReposModel> {
        val position = params.key ?: initialPageIndex
        var response: LoadResult<Int, GithubKotlinReposModel> = LoadResult.Error(Throwable())

        return coroutineScope {
            val result = async {
                try {
                    getKotlinRepositoriesUseCase.run(
                        GetKotlinRepositoriesUseCase.Params(
                            language = language,
                            sort = sort,
                            page = params.key ?: initialPageIndex
                        )
                    ).collectLatest {
                        response = LoadResult.Page(
                            data = it,
                            prevKey = if (position == initialPageIndex) null else position - 1,
                            nextKey = if (it.isEmpty() || it.size < NETWORK_MIN_SIZE_TO_NEXT) null else position + 1
                        )
                    }
                } catch (e: Exception) {
                    response = LoadResult.Error(e)
                }
            }

            result.await()
            return@coroutineScope response
        }
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 1
        const val NETWORK_MIN_SIZE_TO_NEXT = 25
    }

    override fun getRefreshKey(state: PagingState<Int, GithubKotlinReposModel>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}