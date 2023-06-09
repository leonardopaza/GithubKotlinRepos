package com.lp.data_remote.datasource

import com.lp.data.datasource.remote.KotlinReposRemoteDataSource
import com.lp.data.mapper.GithubKotlinReposMapper
import com.lp.data_remote.service.SearchRepoWebService
import com.lp.domain.model.GithubKotlinReposModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class KotlinReposRemoteDataSourceImpl(
    private val webService: SearchRepoWebService
) : KotlinReposRemoteDataSource {
    override fun getGithubKotlinRepositories(
        language: String,
        sort: String,
        page: Int
    ): Flow<List<GithubKotlinReposModel>> = flow {
        emit(
            GithubKotlinReposMapper.toDomain(
                webService.getKotlinRepos(
                    language = language,
                    sort = sort,
                    page = page
                ),
                page
            )
        )
    }
}