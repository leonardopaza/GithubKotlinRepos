package com.lp.data.datasource.remote

import com.lp.data.model.response.GithubKotlinReposResponse
import com.lp.domain.model.GithubKotlinReposModel
import kotlinx.coroutines.flow.Flow

interface KotlinReposRemoteDataSource {
    fun getGithubKotlinRepositories(): Flow<GithubKotlinReposModel>
}