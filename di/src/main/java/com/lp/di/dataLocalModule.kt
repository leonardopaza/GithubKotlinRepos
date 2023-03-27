package com.lp.di

import com.lp.data.datasource.local.KotlinReposLocalDataSource
import com.lp.data_local.datasource.KotlinReposLocalDataSourceImpl
import com.lp.data_local.utils.DatabaseFactory
import org.koin.dsl.module

val dataLocalModule = module {
    single {
        DatabaseFactory.createRoomDatabase(
            get(),
            "github-repos-database"
        )
    }

    single<KotlinReposLocalDataSource> { KotlinReposLocalDataSourceImpl(get()) }
}