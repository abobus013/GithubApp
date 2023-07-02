package com.example.githubapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.githubapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavBar = binding.bottomNavBar
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        setupWithNavController(bottomNavBar, navHost.navController)

    }

    fun showBottomNavigation() {
        binding.bottomNavBar.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        binding.bottomNavBar.visibility = View.GONE
    }

}