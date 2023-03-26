package com.lp.data.mocks

import com.lp.domain.model.GithubKotlinReposModel

object KotlinReposRepositoryFactory {
    const val LANGUAGE_PARAM = "language:kotlin"
    const val SORT_PARAM = "stars"
    const val PAGE_PARAM = 1

    val DUMMY_GITHUB_KOTLIN_REPOS_LIST = listOf<GithubKotlinReposModel>()
}