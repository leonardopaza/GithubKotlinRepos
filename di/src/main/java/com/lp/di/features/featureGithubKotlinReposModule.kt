package com.lp.di.features

import com.lp.github_kotlin_repos.kotlin_github_repositories.KotlinGithubRepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureGithubKotlinReposModule = module {
    viewModel { KotlinGithubRepositoriesViewModel(get()) }
}