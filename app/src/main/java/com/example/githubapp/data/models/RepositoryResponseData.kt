package com.example.githubapp.data.models

data class RepositoryResponseData(
    val name: String,
    val language: String,
    val description: String,
    val owner: ProfileData
)

data class ProfileData(
    val avatar_url: String,
    val login: String,
)

