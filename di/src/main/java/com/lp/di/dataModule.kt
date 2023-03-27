package com.lp.di

import com.lp.data.repository.KotlinReposRepositoryImpl
import com.lp.domain.repository.KotlinReposRepository
import org.koin.dsl.module

val dataModule = module {
    single<KotlinReposRepository> { KotlinReposRepositoryImpl(get(), get()) }
}