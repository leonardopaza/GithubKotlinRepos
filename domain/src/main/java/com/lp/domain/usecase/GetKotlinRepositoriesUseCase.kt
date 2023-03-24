package com.lp.domain.usecase

import com.lp.domain.model.GithubKotlinReposModel
import com.lp.domain.repository.KotlinReposRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetKotlinRepositoriesUseCase(private val repository: KotlinReposRepository) {
    suspend fun execute(): GithubKotlinReposModel {
        return repository.getGithubKotlinRepositories().first()
    }
}