package com.example.githubapp.data.github

import com.example.githubapp.data.github.models.RemoteGithubUser
import retrofit2.http.GET

interface GithubApi {
    @GET("user")
    suspend fun getCurrentUser(
    ): RemoteGithubUser
}