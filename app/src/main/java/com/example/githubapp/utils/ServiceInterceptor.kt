package com.example.githubapp.utils

import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor : Interceptor {
    var token: String = ""

    fun setCustomToken(newToken: String) {
        token = newToken
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (request.header("No-Authentication") == null) {
            if (!token.isNullOrEmpty()) {
                val finalToken = "Bearer $token"
                request = request.newBuilder()
                    .addHeader("Authorization", finalToken)
                    .build()
            }
        }

        return chain.proceed(request)
    }
}
