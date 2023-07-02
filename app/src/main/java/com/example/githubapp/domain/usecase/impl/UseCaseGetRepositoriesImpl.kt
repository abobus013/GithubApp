package com.example.githubapp.domain.usecase.impl

import com.example.githubapp.data.models.RepositoryPayloadData
import com.example.githubapp.data.models.ResultData
import com.example.githubapp.domain.GetRepository
import com.example.githubapp.domain.usecase.UseCaseGetRepositories
import kotlinx.coroutines.flow.Flow

class UseCaseGetRepositoriesImpl(private val repo: GetRepository): UseCaseGetRepositories {

    override suspend fun execute(token: String): Flow<ResultData<MutableList<RepositoryPayloadData>>> {
        return repo.getRepositoryData(token)
    }

}