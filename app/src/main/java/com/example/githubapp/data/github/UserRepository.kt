package com.example.githubapp.data.github


import com.example.githubapp.data.github.models.RemoteGithubUser
import com.example.githubapp.data.network.Networking

class UserRepository {
    suspend fun getUserInformation(): RemoteGithubUser {
        return Networking.githubApi.getCurrentUser()
    }
}