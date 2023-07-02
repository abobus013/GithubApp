package com.example.githubapp.data.models

import com.google.gson.annotations.SerializedName

data class LoginPayloadData (

    @SerializedName("client_id")
    val clientId: String,
    @SerializedName("client_secret")
    val clientSecret: String,
    val code: String
        )