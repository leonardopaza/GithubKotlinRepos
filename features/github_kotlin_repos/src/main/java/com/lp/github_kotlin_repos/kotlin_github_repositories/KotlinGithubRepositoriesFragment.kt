package com.lp.github_kotlin_repos.kotlin_github_repositories

import android.view.LayoutInflater
import com.lp.feature.core.BaseFragmentBinding
import com.lp.github_kotlin_repos.databinding.FragmentKotlinGithubRepositoriesBinding

class KotlinGithubRepositoriesFragment : BaseFragmentBinding<FragmentKotlinGithubRepositoriesBinding>() {

    override fun onCreateViewBinding(inflater: LayoutInflater):
            FragmentKotlinGithubRepositoriesBinding =
        FragmentKotlinGithubRepositoriesBinding.inflate(inflater)
}