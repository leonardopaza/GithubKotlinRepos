package com.lp.domain.model

data class GithubKotlinReposModel(
    val kotlinRepositories: List<GithubKotlinReposDataModel> = listOf()
)

data class GithubKotlinReposDataModel(
    val repositoryName: String? = null
)