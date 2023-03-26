package com.lp.domain.usecase

import com.lp.domain.repository.KotlinReposRepository

class GetKotlinRepositoriesUseCase(private val repository: KotlinReposRepository) {
    fun run(params: Params? = null) = when (params) {
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