package com.lp.data_local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lp.data.model.entity.GithubKotlinReposEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubKotlinReposDao {
    @Query("SELECT * FROM github_kotlin_repos WHERE page = :page")
    suspend fun getKotlinReposByPage(page: Int): List<GithubKotlinReposEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addList(repos: List<GithubKotlinReposEntity>)
}