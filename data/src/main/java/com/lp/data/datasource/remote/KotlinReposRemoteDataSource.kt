package com.lp.data.datasource.remote

import com.lp.domain.model.GithubKotlinReposModel
import kotlinx.coroutines.flow.Flow

interface KotlinReposRemoteDataSource {
    fun getGithubKotlinRepositories(
        language: String,
        sort: String,
        page: Int
    ): Flow<List<GithubKotlinReposModel>>
}