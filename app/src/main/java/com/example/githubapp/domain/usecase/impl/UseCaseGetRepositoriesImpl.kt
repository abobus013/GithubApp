package com.example.githubapp.domain.usecase.impl

import com.example.githubapp.data.models.GetUserInfo
import com.example.githubapp.data.models.RepositoryResponseData
import com.example.githubapp.data.models.ResultData
import com.example.githubapp.domain.GetUserInfoRepository
import com.example.githubapp.domain.usecase.UseCaseGetRepositories
import kotlinx.coroutines.flow.Flow

class UseCaseGetRepositoriesImpl(private val repo: GetUserInfoRepository): UseCaseGetRepositories {

    override suspend fun execute(token: String): Flow<ResultData<MutableList<GetUserInfo>>> {
        return repo.getUserInfoData(token)
    }

}