package com.redeyesncode.thinklogical.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.redeyesncode.thinklogical.databinding.ActivitySignupBinding
import com.redeyesncode.thinklogical.main.DashboardActivity

class SignupActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicks()
    }

    private fun initClicks() {
        binding.btnSignup.setOnClickListener {
            //Go to the dashboard activity.
            var dashBoardIntent = Intent(this@SignupActivity,DashboardActivity::class.java)
            startActivity(dashBoardIntent)
            finish()
        }
        binding.btnLogin.setOnClickListener {
            onBackPressed()
        }
    }
}