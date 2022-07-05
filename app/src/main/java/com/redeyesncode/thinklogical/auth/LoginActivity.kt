package com.redeyesncode.thinklogical.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.redeyesncode.thinklogical.databinding.ActivityLoginBinding
import com.redeyesncode.thinklogical.main.DashboardActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicks()
    }

    private fun initClicks() {
        binding.btnBack.setOnClickListener { onBackPressed() }
        binding.btnLogin.setOnClickListener {
            // Go to the home screen.
            var dashBoardIntent = Intent(this@LoginActivity, DashboardActivity::class.java)
            startActivity(dashBoardIntent)
            finish()
        }
        binding.btnSignup.setOnClickListener {
            // Go to the signup screen
            var intentSignUp = Intent(this@LoginActivity,SignupActivity::class.java)
            startActivity(intentSignUp)
        }
    }
}