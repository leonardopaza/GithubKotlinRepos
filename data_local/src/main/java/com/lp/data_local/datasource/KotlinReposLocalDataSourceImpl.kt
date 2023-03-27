package com.lp.data_local.datasource

import android.util.Log
import com.lp.data.datasource.local.KotlinReposLocalDataSource
import com.lp.data.mapper.GithubKotlinReposMapper
import com.lp.data_local.database.AppDatabase
import com.lp.domain.model.GithubKotlinReposModel

class KotlinReposLocalDataSourceImpl(private val db: AppDatabase): KotlinReposLocalDataSource {

    override suspend fun saveGithubKotlinRepositories(
        githubKotlinReposModelList: List<GithubKotlinReposModel>
    ): Boolean {
        return try {
            db.GithubKotlinReposDao().addList(
                GithubKotlinReposMapper.toEntity(githubKotlinReposModelList)
            )
            true
        } catch (e: Exception) {
            Log.d("Erro Insert", e.localizedMessage, e)
            false
        }
    }

    override suspend fun getGithubKotlinRepositories(page: Int): List<GithubKotlinReposModel> =
        GithubKotlinReposMapper.toDomain(
            db.GithubKotlinReposDao().getKotlinReposByPage(page),
            page
        )
}