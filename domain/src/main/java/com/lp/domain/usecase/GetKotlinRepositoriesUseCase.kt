package com.lp.domain.usecase

import com.lp.domain.model.GithubKotlinReposModel
import com.lp.domain.repository.KotlinReposRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetKotlinRepositoriesUseCase(private val repository: KotlinReposRepository) {
    suspend fun run(params: Params? = null) : Flow<List<GithubKotlinReposModel>> {
        if (params == null) {
            throw RuntimeException("Params must not be null")
        } else {
            val result = repository.getLocalGithubKotlinRepositories(params.page)

            return if (result.isEmpty().not())
                flowOf(result)
            else
                repository.getGithubKotlinRepositories(
                    params.language,
                    params.sort,
                    params.page
                )
        }
    }

    data class Params(
        val language: String,
        val sort: String,
        val page: Int
    )
}