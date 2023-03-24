package com.lp.domain.model

data class GithubKotlinReposModel(
    val authorName: String? = null,
    val authorPictureUrl: String? = null,
    val repositoryName: String? = null,
    val starsQuantity: Long? = null,
    val forksQuantity: Long? = null
)