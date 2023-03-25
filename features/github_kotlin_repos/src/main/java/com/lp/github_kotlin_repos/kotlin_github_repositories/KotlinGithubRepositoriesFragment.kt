package com.lp.github_kotlin_repos.kotlin_github_repositories

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lp.domain.model.GithubKotlinReposModel
import com.lp.feature.core.BaseFragmentBinding
import com.lp.github_kotlin_repos.adapter.GithubKotlinReposAdapter
import com.lp.github_kotlin_repos.databinding.FragmentKotlinGithubRepositoriesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class KotlinGithubRepositoriesFragment : BaseFragmentBinding<FragmentKotlinGithubRepositoriesBinding>() {
    private val viewModel: KotlinGithubRepositoriesViewModel by viewModel()

    override fun onCreateViewBinding(inflater: LayoutInflater):
            FragmentKotlinGithubRepositoriesBinding =
        FragmentKotlinGithubRepositoriesBinding.inflate(inflater)

    override fun configureView() {
        viewModel.getKotlinRepositories()
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.kotlinRepos.observe(owner) {
            populateRepositories(it)
        }
    }

    private fun populateRepositories(repos: List<GithubKotlinReposModel>) {
        val githubKotlinRepositories = GithubKotlinReposAdapter(repos)

        binding.rvGithubRepositories.adapter = githubKotlinRepositories
        binding.rvGithubRepositories.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
    }
}