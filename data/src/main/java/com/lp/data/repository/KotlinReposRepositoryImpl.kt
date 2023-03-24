package com.lp.data.repository

import com.lp.data.datasource.remote.KotlinReposRemoteDataSource
import com.lp.domain.model.GithubKotlinReposModel
import com.lp.domain.repository.KotlinReposRepository
import kotlinx.coroutines.flow.Flow

class KotlinReposRepositoryImpl(
    private val dataSource: KotlinReposRemoteDataSource
) : KotlinReposRepository {
    override fun getGithubKotlinRepositories(): Flow<List<GithubKotlinReposModel>> =
        dataSource.getGithubKotlinRepositories()
}