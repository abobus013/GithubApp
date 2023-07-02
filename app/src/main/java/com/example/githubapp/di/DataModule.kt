package com.example.githubapp.di

import com.example.githubapp.data.repository_impl.GetRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single {
        GetRepositoryImpl(api = get())
    }
}