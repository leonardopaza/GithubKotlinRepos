package com.lp.domain.repository

import com.lp.domain.model.GithubKotlinReposModel
import kotlinx.coroutines.flow.Flow

interface KotlinReposRepository {
    fun getGithubKotlinRepositories(
        language: String,
        sort: String,
        page: Int
    ): Flow<List<GithubKotlinReposModel>>
}