package com.example.githubapp.ui.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.githubapp.ui.MainActivity
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentProfileBinding
import com.example.githubapp.ui.user_info.UserInfoViewModel
import com.example.githubapp.utils.launchAndCollectIn
import com.example.githubapp.utils.resetNavGraph
import com.example.githubapp.utils.toast

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: UserInfoViewModel by viewModels()

    private val logoutResponse = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            viewModel.webLogoutComplete()
        } else {
            // логаут отменен
            // делаем complete тк github не редиректит после логаута и пользователь закрывает CCT
            viewModel.webLogoutComplete()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        (requireActivity() as MainActivity).showBottomNavigation()

//        binding.corruptAccessToken.setOnClickListener {
            viewModel.corruptAccessToken()
//        }
//        binding.getUserInfo.setOnClickListener {
           viewModel.loadUserInfo()
//        }
//        binding.logout.setOnClickListener {
            viewModel.logout()
//        }

//        viewModel.loadingFlow.launchAndCollectIn(viewLifecycleOwner) { isLoading ->
//            binding.progressBar.isVisible = isLoading
//            binding.getUserInfo.isEnabled = !isLoading
//            binding.userInfo.isVisible = !isLoading
//        }

        viewModel.userInfoFlow.launchAndCollectIn(viewLifecycleOwner) { userInfo ->
            binding.userInfo.text = userInfo?.login
        }

        viewModel.toastFlow.launchAndCollectIn(viewLifecycleOwner) {
            toast(it)
        }

        viewModel.logoutPageFlow.launchAndCollectIn(viewLifecycleOwner) {
            logoutResponse.launch(it)
        }

        viewModel.logoutCompletedFlow.launchAndCollectIn(viewLifecycleOwner) {
            findNavController().resetNavGraph(R.navigation.nav_graph)
        }

    }


}