package com.example.githubapp.data

data class GenericResponseData<T>(
    val success: Boolean,
    val code: Int,
    val message: String,
    val payload: T
)