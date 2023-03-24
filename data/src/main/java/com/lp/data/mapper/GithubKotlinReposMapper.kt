package com.lp.data.mapper

import com.lp.data.model.response.GithubKotlinReposResponse
import com.lp.domain.model.GithubKotlinReposModel

object GithubKotlinReposMapper {
    fun toDomain(response: GithubKotlinReposResponse) : List<GithubKotlinReposModel> {
        val listResponse = mutableListOf<GithubKotlinReposModel>()
        response.items.forEach {
            listResponse.add(
                GithubKotlinReposModel(
                    authorName = it.owner?.authorName,
                    authorPictureUrl = it.owner?.authorPictureUrl,
                    repositoryName = it.repositoryName,
                    starsQuantity = it.starsQuantity,
                    forksQuantity = it.forksQuantity
                )
            )
        }
        return listResponse.toList()
    }
}