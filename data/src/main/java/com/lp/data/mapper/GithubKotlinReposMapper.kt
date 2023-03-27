package com.lp.data.mapper

import com.lp.data.model.entity.GithubKotlinReposEntity
import com.lp.data.model.response.GithubKotlinReposResponse
import com.lp.domain.model.GithubKotlinReposModel

object GithubKotlinReposMapper {
    fun toDomain(response: GithubKotlinReposResponse, page: Int) : List<GithubKotlinReposModel> {
        val listResponse = mutableListOf<GithubKotlinReposModel>()
        response.items.forEach {
            listResponse.add(
                GithubKotlinReposModel(
                    authorName = it.owner?.authorName,
                    authorPictureUrl = it.owner?.authorPictureUrl,
                    repositoryName = it.repositoryName,
                    starsQuantity = it.starsQuantity,
                    forksQuantity = it.forksQuantity,
                    page = page
                )
            )
        }
        return listResponse.toList()
    }

    fun toDomain(list: List<GithubKotlinReposEntity>, page: Int) : List<GithubKotlinReposModel> {
        val listResponse = mutableListOf<GithubKotlinReposModel>()
        list.forEach {
            listResponse.add(
                GithubKotlinReposModel(
                    authorName = it.authorName,
                    authorPictureUrl = it.authorPictureUrl,
                    repositoryName = it.repositoryName,
                    starsQuantity = it.starsQuantity,
                    forksQuantity = it.forksQuantity,
                    page = page,
                    isLocal = true
                )
            )
        }
        return listResponse.toList()
    }

    fun toEntity(list: List<GithubKotlinReposModel>) : List<GithubKotlinReposEntity> {
        val listResponse = mutableListOf<GithubKotlinReposEntity>()
        list.forEach {
            listResponse.add(
                GithubKotlinReposEntity(
                    authorName = it.authorName ?: "",
                    authorPictureUrl = it.authorPictureUrl ?: "",
                    repositoryName = it.repositoryName ?: "",
                    starsQuantity = it.starsQuantity ?: 0,
                    forksQuantity = it.forksQuantity ?: 0,
                    page = it.page ?: 0
                )
            )
        }
        return listResponse.toList()
    }
}