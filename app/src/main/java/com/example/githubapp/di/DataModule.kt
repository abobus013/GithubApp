package com.example.githubapp.di

import com.example.githubapp.data.repository_impl.GetUserInfoUserImpl
import org.koin.dsl.module

val dataModule = module {
    single {
        GetUserInfoUserImpl(api = get())
    }
}