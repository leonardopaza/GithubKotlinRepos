package com.lp.domain.model

data class GithubKotlinReposModel(
    val authorName: String? = null,
    val authorPictureUrl: String? = null,
    val repositoryName: String? = null,
    var starsQuantity: Long? = null,
    var forksQuantity: Long? = null,
    val page: Int? = null,
    val isLocal: Boolean = false
)