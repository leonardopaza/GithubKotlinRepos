package com.lp.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_kotlin_repos")
data class GithubKotlinReposEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val authorName: String,
    val authorPictureUrl: String,
    val repositoryName: String,
    val forksQuantity: Long,
    val starsQuantity: Long,
    val page: Int
)
