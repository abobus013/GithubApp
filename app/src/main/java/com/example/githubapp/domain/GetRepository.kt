package com.example.githubapp.domain

import com.example.githubapp.data.models.RepositoryPayloadData
import com.example.githubapp.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetRepository {

    suspend fun getRepositoryData(token: String): Flow<ResultData<MutableList<RepositoryPayloadData>>>
}