package com.example.githubapp.di

import com.example.githubapp.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(repo = get())
    }
}
