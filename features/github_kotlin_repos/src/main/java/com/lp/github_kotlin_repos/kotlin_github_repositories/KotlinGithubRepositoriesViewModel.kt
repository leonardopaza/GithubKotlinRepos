package com.lp.github_kotlin_repos.kotlin_github_repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lp.domain.model.GithubKotlinReposModel
import com.lp.domain.usecase.GetKotlinRepositoriesUseCase
import kotlinx.coroutines.launch

class KotlinGithubRepositoriesViewModel(
        private val getKotlinRepositoriesUseCase: GetKotlinRepositoriesUseCase
    ) : ViewModel() {
        private val _kotlinRepos = MutableLiveData<List<GithubKotlinReposModel>>()
        val kotlinRepos: LiveData<List<GithubKotlinReposModel>> = _kotlinRepos

        fun getKotlinRepositories() {
            viewModelScope.launch {
                _kotlinRepos.value = getKotlinRepositoriesUseCase.execute()
            }
        }
}