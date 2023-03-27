package com.lp.data.datasource.local

import com.lp.domain.model.GithubKotlinReposModel
import kotlinx.coroutines.flow.Flow

interface KotlinReposLocalDataSource {
    suspend fun saveGithubKotlinRepositories(
        githubKotlinReposModelList: List<GithubKotlinReposModel>
    ): Boolean

    suspend fun getGithubKotlinRepositories(
        page: Int
    ): List<GithubKotlinReposModel>
}