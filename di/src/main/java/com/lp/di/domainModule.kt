package com.lp.di

import com.lp.domain.usecase.GetKotlinRepositoriesUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {
    factory { GetKotlinRepositoriesUseCase(get()) }
}