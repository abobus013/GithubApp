package com.example.githubapp.data.repository_impl

import com.example.githubapp.data.GitHubApi
import com.example.githubapp.data.models.ResultData
import com.example.githubapp.domain.GetUserInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetUserInfoUserImpl(private val api: GitHubApi) : GetUserInfoRepository {

    override suspend fun getUserInfoData(token: String)= flow {
        val response = api.getUserRepositories( token)
        val result = response.body()!!.payload

        if (response.isSuccessful) {
            emit(ResultData.Success(result))
        } else {
            emit(ResultData.Message(response.message()))
        }
    }.catch {
        emit(ResultData.Error(it))
    }.flowOn(Dispatchers.IO)

}