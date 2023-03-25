package com.lp.github_kotlin_repos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lp.domain.model.GithubKotlinReposModel
import com.lp.feature.utils.ItemClickListener
import com.lp.feature.utils.ItemLongClickListener
import com.lp.github_kotlin_repos.R

class GithubKotlinReposAdapter(private val dataSet: List<GithubKotlinReposModel>) :
    RecyclerView.Adapter<GithubKotlinReposAdapter.ViewHolder>() {
    private var itemClickListener: ItemClickListener? = null
    private var itemLongClickListener: ItemLongClickListener? = null

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvAuthorName: TextView = v.findViewById(R.id.tvAuthorName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_github_repository, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvAuthorName.text = dataSet[position].authorName

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(it, position)
        }

        holder.itemView.setOnLongClickListener {
            itemLongClickListener?.onItemLongClick(it, position)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount() = dataSet.size

    fun getDataSet(): List<GithubKotlinReposModel> {
        return dataSet
    }

    fun getItem(position: Int) = dataSet[position]

    fun itemClickListener(ic: ItemClickListener) {
        this.itemClickListener = ic
    }

    fun itemLongClickListener(ic: ItemLongClickListener) {
        this.itemLongClickListener = ic
    }
}