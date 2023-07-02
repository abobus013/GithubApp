package com.example.githubapp.data.repository_impl

import com.example.githubapp.data.GitHubApi
import com.example.githubapp.data.models.ResultData
import com.example.githubapp.domain.GetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetRepositoryImpl(private val api: GitHubApi) : GetRepository {

    override suspend fun getRepositoryData(token: String)= flow {
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