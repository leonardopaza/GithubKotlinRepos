package com.lp.github_kotlin_repos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lp.domain.model.GithubKotlinReposModel
import com.lp.feature.utils.ItemClickListener
import com.lp.feature.utils.ItemLongClickListener
import com.lp.github_kotlin_repos.databinding.ItemGithubRepositoryBinding

class GithubKotlinReposAdapter :
    PagingDataAdapter<GithubKotlinReposModel, GithubKotlinReposAdapter.ViewHolder>(
        DiffUtilCallBack()
    ) {

    private var itemClickListener: ItemClickListener? = null
    private var itemLongClickListener: ItemLongClickListener? = null

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
            binding.tvAuthorName.text = githubKotlinReposModel.authorName

            binding.root.setOnClickListener {
                itemClickListener?.onItemClick(it, position)
            }

            binding.root.setOnLongClickListener {
                itemLongClickListener?.onItemLongClick(it, position)
                return@setOnLongClickListener true
            }
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

    fun itemClickListener(ic: ItemClickListener) {
        this.itemClickListener = ic
    }

    fun itemLongClickListener(ic: ItemLongClickListener) {
        this.itemLongClickListener = ic
    }
}