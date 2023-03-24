package com.lp.githubkotlinrepos

import android.app.Application
import com.lp.di.dataModule
import com.lp.di.dataRemoteModule
import com.lp.di.domainModule
import com.lp.di.features.featureGithubKotlinReposModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                featureGithubKotlinReposModule +
                domainModule +
                dataModule +
                dataRemoteModule
            ).androidLogger().androidContext(applicationContext)
        }
    }
}