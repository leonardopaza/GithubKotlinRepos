package com.lp.github_kotlin_repos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lp.domain.model.GithubKotlinReposModel
import com.lp.github_kotlin_repos.R
import com.lp.github_kotlin_repos.databinding.ItemGithubRepositoryBinding
import java.text.NumberFormat
import java.util.*

class GithubKotlinReposAdapter :
    PagingDataAdapter<GithubKotlinReposModel, GithubKotlinReposAdapter.ViewHolder>(
        DiffUtilCallBack()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemGithubRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindItem(it)
        }
    }

    inner class ViewHolder(
        private val binding: ItemGithubRepositoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(githubKotlinReposModel: GithubKotlinReposModel) {
            if (githubKotlinReposModel.authorPictureUrl.isNullOrBlank().not()) {
                Glide
                    .with(binding.ivAuthorPicture)
                    .load(githubKotlinReposModel.authorPictureUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_github_logo)
                    .into(binding.ivAuthorPicture)
            } else {
                Glide.with(binding.ivAuthorPicture).clear(binding.ivAuthorPicture)
                binding.ivAuthorPicture.setImageResource(R.drawable.ic_github_logo)
            }

            binding.tvAuthorName.text = githubKotlinReposModel.authorName
            binding.tvRepositoryName.text = githubKotlinReposModel.repositoryName

            val numberFormat = NumberFormat.getInstance(Locale("pt", "BR"))
            if (githubKotlinReposModel.forksQuantity == null) githubKotlinReposModel.forksQuantity = 0
            if (githubKotlinReposModel.starsQuantity == null) githubKotlinReposModel.starsQuantity = 0

            binding.tvForksQuantity.text = numberFormat.format(githubKotlinReposModel.forksQuantity)
            binding.tvStarsQuantity.text = numberFormat.format(githubKotlinReposModel.starsQuantity)
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<GithubKotlinReposModel>() {
        override fun areItemsTheSame(
            oldItem: GithubKotlinReposModel,
            newItem: GithubKotlinReposModel
        ): Boolean {
            return oldItem.authorName == newItem.authorName
        }

        override fun areContentsTheSame(
            oldItem: GithubKotlinReposModel,
            newItem: GithubKotlinReposModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}