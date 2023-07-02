package com.example.githubapp.data.models

import com.google.gson.annotations.SerializedName

data class GetAccessResponseData(

    @SerializedName("access_token")
    val accessToken: String,
    val authorization: String,
    val state: Boolean
)
