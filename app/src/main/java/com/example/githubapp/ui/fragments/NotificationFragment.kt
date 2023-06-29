package com.example.githubapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.githubapp.ui.MainActivity
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment(R.layout.fragment_notification) {
    private lateinit var binding: FragmentNotificationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotificationBinding.bind(view)

        (requireActivity() as MainActivity).showBottomNavigation()

    }


}