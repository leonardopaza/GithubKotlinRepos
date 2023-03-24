package com.lp.data.model.response

import com.google.gson.annotations.SerializedName

data class GithubKotlinReposResponse(
    @SerializedName("items")
    val items: List<GithubKotlinReposDataResponse> = listOf()
)

data class GithubKotlinReposDataResponse(
    @SerializedName("name")
    val repositoryName: String? = null,
    @SerializedName("owner")
    val owner: GithubKotlinReposDataOwnerResponse? = null,
    @SerializedName("forks_count")
    val forksQuantity: Long? = null,
    @SerializedName("stargazers_count")
    val starsQuantity: Long? = null
)

data class GithubKotlinReposDataOwnerResponse(
    @SerializedName("login")
    val authorName: String? = null,
    @SerializedName("avatar_url")
    val authorPictureUrl: String? = null
)
