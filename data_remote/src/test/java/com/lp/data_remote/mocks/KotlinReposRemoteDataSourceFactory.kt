package com.lp.data_remote.mocks

import com.lp.data.model.response.GithubKotlinReposResponse

object KotlinReposRemoteDataSourceFactory {
    const val LANGUAGE_PARAM = "language:kotlin"
    const val SORT_PARAM = "stars"
    const val PAGE_PARAM = 1

    val DUMMY_GITHUB_KOTLIN_REPOS_RESPONSE = GithubKotlinReposResponse()
}