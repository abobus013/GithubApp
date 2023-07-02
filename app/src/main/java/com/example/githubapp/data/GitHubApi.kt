package com.example.githubapp.data

import com.example.githubapp.data.models.GetAccessResponseData
import com.example.githubapp.data.models.RepositoryPayloadData
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface GitHubApi {

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("https://github.com/login/oauth/access_token")
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
    ): Response<GetAccessResponseData>


    @GET("/user")
    suspend fun getUserRepositories(token: String): Response<GenericResponseData<MutableList<RepositoryPayloadData>>>

}