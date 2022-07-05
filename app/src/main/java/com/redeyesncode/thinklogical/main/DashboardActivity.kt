package com.redeyesncode.thinklogical.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.redeyesncode.thinklogical.databinding.ActivityDashboardBinding


class DashboardActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNav()
    }

    private fun initNav() {
        val navController: NavController =
            Navigation.findNavController(this, com.redeyesncode.thinklogical.R.id.activity_main_nav_host_fragment)
        val bottomNavigationView = binding.bottomNavigationbar
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }
}