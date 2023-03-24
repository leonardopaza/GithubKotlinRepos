package com.lp.data.mapper

import com.lp.data.model.response.GithubKotlinReposDataResponse
import com.lp.data.model.response.GithubKotlinReposResponse
import com.lp.domain.model.GithubKotlinReposDataModel
import com.lp.domain.model.GithubKotlinReposModel

object GithubKotlinReposMapper {
    fun toDomain(response: GithubKotlinReposResponse) = GithubKotlinReposModel(
        kotlinRepositories = listToDomainData(response.items)
    )

    private fun listToDomainData(list: List<GithubKotlinReposDataResponse>?): List<GithubKotlinReposDataModel> {
        val listResponse = mutableListOf<GithubKotlinReposDataModel>()
        list?.forEach {
            listResponse.add(
                GithubKotlinReposDataModel(
                    repositoryName = it.repositoryName
                )
            )
        }
        return listResponse.toList()
    }
}