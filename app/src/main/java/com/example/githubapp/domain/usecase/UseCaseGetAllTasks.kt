package com.example.githubapp.domain.usecase

import com.example.githubapp.data.models.RepositoryPayloadData
import com.example.githubapp.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface UseCaseGetRepositories {

    suspend fun execute(token: String): Flow<ResultData<MutableList<RepositoryPayloadData>>>
}