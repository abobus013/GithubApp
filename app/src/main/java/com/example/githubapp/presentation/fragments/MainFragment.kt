package com.example.githubapp.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.githubapp.MainActivity
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        (requireActivity() as MainActivity).showBottomNavigation()

    }
}