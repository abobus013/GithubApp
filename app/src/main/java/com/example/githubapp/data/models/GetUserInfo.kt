package com.example.githubapp.data.models

data class GetUserInfo(
    val login: String,
    val name: String,
    val avatar_url: String,
    val bio: String,
    val followers: Int,
    val following: Int
)
