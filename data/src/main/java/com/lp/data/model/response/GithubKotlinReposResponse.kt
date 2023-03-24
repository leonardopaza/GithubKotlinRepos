package com.lp.data.model.response

import com.google.gson.annotations.SerializedName

data class GithubKotlinReposResponse(
    @SerializedName("items")
    val items: List<GithubKotlinReposDataResponse> = listOf()
)

data class GithubKotlinReposDataResponse(
    @SerializedName("full_name")
    val repositoryName: String? = null
)
