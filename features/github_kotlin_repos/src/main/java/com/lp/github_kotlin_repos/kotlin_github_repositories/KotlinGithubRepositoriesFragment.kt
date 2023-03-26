package com.lp.github_kotlin_repos.kotlin_github_repositories

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lp.feature.core.BaseFragmentBinding
import com.lp.github_kotlin_repos.adapter.GithubKotlinReposAdapter
import com.lp.github_kotlin_repos.databinding.FragmentKotlinGithubRepositoriesBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class KotlinGithubRepositoriesFragment : BaseFragmentBinding<FragmentKotlinGithubRepositoriesBinding>() {
    private val viewModel: KotlinGithubRepositoriesViewModel by viewModel()
    private val githubKotlinAdapter = GithubKotlinReposAdapter()

    override fun onCreateViewBinding(inflater: LayoutInflater):
            FragmentKotlinGithubRepositoriesBinding =
        FragmentKotlinGithubRepositoriesBinding.inflate(inflater)

    override fun configureView() {
        setupAdapter()
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.kotlinRepos.observe(owner) {
            lifecycleScope.launch {
                it.collectLatest {
                    githubKotlinAdapter.submitData(it)
                }
            }
        }
    }

    private fun setupAdapter() {
        githubKotlinAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading
            ) {
                showLoading()
            } else {
                hideLoading()

                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }

                errorState?.let {
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.rvGithubRepositories.adapter = githubKotlinAdapter
        binding.rvGithubRepositories.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
    }
}