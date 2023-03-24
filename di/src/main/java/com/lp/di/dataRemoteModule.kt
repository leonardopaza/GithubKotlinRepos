package com.lp.di

import com.lp.data.datasource.remote.KotlinReposRemoteDataSource
import com.lp.data_remote.datasource.KotlinReposRemoteDataSourceImpl
import com.lp.data_remote.service.SearchRepoWebService
import com.lp.data_remote.utils.WebServiceFactory
import com.lp.di.BuildConfig.DEBUG
import com.lp.di.BuildConfig.GITHUB_API
import org.koin.dsl.module

val dataRemoteModule = module {
    single {
        WebServiceFactory.provideOkHttpClient(
            isDebugVersion = DEBUG
        )
    }

    single {
        WebServiceFactory.createWebService(
            get(),
            url = GITHUB_API
        ) as SearchRepoWebService
    }

    single<KotlinReposRemoteDataSource> { KotlinReposRemoteDataSourceImpl(get()) }
}