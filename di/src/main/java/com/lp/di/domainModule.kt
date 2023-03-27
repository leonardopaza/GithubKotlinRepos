package com.lp.di

import com.lp.domain.usecase.GetKotlinRepositoriesUseCase
import com.lp.domain.usecase.SaveKotlinRepositoriesUseCase
import com.lp.domain.utils.ThreadContextProvider
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {
    single { ThreadContextProvider() }

    factory { GetKotlinRepositoriesUseCase(get()) }

    factory { SaveKotlinRepositoriesUseCase(get()) }
}