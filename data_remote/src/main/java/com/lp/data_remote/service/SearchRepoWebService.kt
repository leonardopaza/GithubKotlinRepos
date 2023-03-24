package com.lp.data_remote.service

import com.lp.data.model.response.GithubKotlinReposResponse
import com.lp.data_remote.service.SearchRepoWebService.SearchRepoConstants.SEARCH_REPOSITORIES_URL
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRepoWebService {

    @GET(SEARCH_REPOSITORIES_URL)
    suspend fun getKotlinRepos(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ) : GithubKotlinReposResponse

    object SearchRepoConstants {
        const val SEARCH_REPOSITORIES_URL = "search/repositories"
    }
}