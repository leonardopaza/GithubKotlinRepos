package com.lp.github_kotlin_repos.kotlin_github_repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lp.domain.model.GithubKotlinReposModel
import com.lp.domain.usecase.GetKotlinRepositoriesUseCase
import com.lp.github_kotlin_repos.paging.GithubRepositoriesPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class KotlinGithubRepositoriesViewModel(
        private val getKotlinRepositoriesUseCase: GetKotlinRepositoriesUseCase
    ) : ViewModel() {
        private val _kotlinRepos = MutableLiveData<Flow<PagingData<GithubKotlinReposModel>>>()
        val kotlinRepos: LiveData<Flow<PagingData<GithubKotlinReposModel>>> = _kotlinRepos

        fun getKotlinRepositories() {
            val newResult = Pager(
                PagingConfig(
                    pageSize = GithubRepositoriesPagingSource.NETWORK_PAGE_SIZE
                ),
                pagingSourceFactory = {
                    GithubRepositoriesPagingSource(
                        language = "language:kotlin",
                        sort = "stars",
                        getKotlinRepositoriesUseCase
                    )
                }
            ).flow.cachedIn(viewModelScope)

            _kotlinRepos.value = newResult
        }
}