package com.example.githubapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.githubapp.ui.MainActivity
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        (requireActivity() as MainActivity).showBottomNavigation()

//        val bottomNav = binding.bottomNavBar
//        val activityNavHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
//        navController = activityNavHostFragment.navController
//
//        bottomNav.setupWithNavController(navController)
    }

}