package com.lp.github_kotlin_repos.kotlin_github_repositories

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.lp.feature.core.BaseFragmentBinding
import com.lp.github_kotlin_repos.databinding.FragmentKotlinGithubRepositoriesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class KotlinGithubRepositoriesFragment : BaseFragmentBinding<FragmentKotlinGithubRepositoriesBinding>() {
    private val viewModel: KotlinGithubRepositoriesViewModel by viewModel()

    override fun onCreateViewBinding(inflater: LayoutInflater):
            FragmentKotlinGithubRepositoriesBinding =
        FragmentKotlinGithubRepositoriesBinding.inflate(inflater)

    override fun configureView() {
        binding.btnTest.setOnClickListener {
            viewModel.getKotlinRepositories()
        }
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.kotlinRepos.observe(owner) {
            Toast.makeText(requireContext(), it.first().starsQuantity.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}