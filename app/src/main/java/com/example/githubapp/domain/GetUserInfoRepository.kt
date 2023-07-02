package com.example.githubapp.domain

import com.example.githubapp.data.models.GetUserInfo
import com.example.githubapp.data.models.RepositoryResponseData
import com.example.githubapp.data.models.ResultData
import kotlinx.coroutines.flow.Flow

interface GetUserInfoRepository {

    suspend fun getUserInfoData(token: String): Flow<ResultData<MutableList<GetUserInfo>>>
}