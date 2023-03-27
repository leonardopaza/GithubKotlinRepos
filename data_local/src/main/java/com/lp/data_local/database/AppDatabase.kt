package com.lp.data_local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lp.data.model.entity.GithubKotlinReposEntity
import com.lp.data_local.dao.GithubKotlinReposDao

@Database(
    entities = [
        GithubKotlinReposEntity::class
    ],
    version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun GithubKotlinReposDao() : GithubKotlinReposDao
}