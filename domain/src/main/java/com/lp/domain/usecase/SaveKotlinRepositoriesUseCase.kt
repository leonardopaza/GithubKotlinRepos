package com.lp.domain.usecase

import com.lp.domain.model.GithubKotlinReposModel
import com.lp.domain.repository.KotlinReposRepository

class SaveKotlinRepositoriesUseCase(private val repository: KotlinReposRepository) {
    suspend fun run(params: Params? =null) = when(params) {
        null -> throw RuntimeException("Params must not be null")
        else -> {
            repository.saveGithubKotlinRepositories(
                params.githubKotlinReposModelList
            )
        }
    }

    data class Params(
        val githubKotlinReposModelList: List<GithubKotlinReposModel>
    )
}