package com.example.githubapp.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.githubapp.MainActivity
import com.example.githubapp.R
import com.example.githubapp.data.models.RepositoryPayloadData
import com.example.githubapp.databinding.FragmentProfileBinding
import com.example.githubapp.presentation.MainViewModel
import com.example.githubapp.presentation.adapter.GithubAdapter
import com.example.githubapp.utils.ServiceInterceptor
import com.example.githubapp.utils.toast
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var adapter: GithubAdapter
    private val viewModel: MainViewModel by viewModel { parametersOf(getOkHttpClient()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        (requireActivity() as MainActivity).showBottomNavigation()

        adapter = GithubAdapter()
        binding.recyclerView.adapter = adapter

        val repositoriesDataList = mutableListOf<RepositoryPayloadData>()
        adapter.models = repositoriesDataList

        initObserversGetRepository()
    }

    private fun initObserversGetRepository() {
        viewModel.getRepositoryLoadingLiveData.observe(requireActivity()) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
        }

        viewModel.getRepositorySuccessLiveData.observe(requireActivity()) {
            adapter.models = it!!
        }

        viewModel.getRepositoryMessageLiveData.observe(requireActivity()) {
            toast(it)
        }

        viewModel.getRepositoryErrorLiveData.observe(requireActivity()) {
            toast(it.message!!)
        }
    }

    private fun getOkHttpClient(): OkHttpClient {
        val interceptor = ServiceInterceptor()
        interceptor.token = "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"

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}