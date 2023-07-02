package com.example.githubapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.data.models.GetUserInfo
import com.example.githubapp.data.models.RepositoryResponseData
import com.example.githubapp.data.models.ResultData
import com.example.githubapp.data.repository_impl.GetUserInfoUserImpl
import com.example.githubapp.domain.usecase.impl.UseCaseGetRepositoriesImpl
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(private val repo: GetUserInfoUserImpl) : ViewModel() {

    private val _getRepositorySuccessLiveData = MutableLiveData<MutableList<GetUserInfo>?>()
    val getRepositorySuccessLiveData: LiveData<MutableList<GetUserInfo>?> get() = _getRepositorySuccessLiveData

    private val _getRepositoryMessageLiveData = MutableLiveData<String>()
    val getRepositoryMessageLiveData: LiveData<String> get() = _getRepositoryMessageLiveData

    private val _getRepositoryErrorLiveData = MutableLiveData<Throwable>()
    val getRepositoryErrorLiveData: LiveData<Throwable> get() = _getRepositoryErrorLiveData

    private val _getRepositoryLoadingLiveData = MutableLiveData<Boolean>()
    val getRepositoryLoadingLiveData: LiveData<Boolean> get() = _getRepositoryLoadingLiveData


    suspend fun getRepositories(token: String) {
        val useCase = UseCaseGetRepositoriesImpl(repo)
        useCase.execute(token).onEach {
            when (it) {

                is ResultData.Success -> {
                    _getRepositorySuccessLiveData.value = it.data
                }

                is ResultData.Message -> {
                    _getRepositoryMessageLiveData.value = it.message
                }

                is ResultData.Error -> {
                    _getRepositoryErrorLiveData.value = it.error
                }

                else -> {}

            }
        }.launchIn(viewModelScope)
    }


}