package com.lp.domain.usecase

import com.lp.domain.model.GithubKotlinReposModel
import com.lp.domain.repository.KotlinReposRepository
import kotlinx.coroutines.flow.*

class GetKotlinRepositoriesUseCase(private val repository: KotlinReposRepository) {
    suspend fun execute(params: Params?) = when (params) {
        null -> throw RuntimeException("Params must not be null")
        else -> {
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