package com.example.hafizh_cool.Home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hafizh_cool.BaseActivity
import com.example.hafizh_cool.R
import com.example.hafizh_cool.Home.tutorial.TutorialMessageActivity // Pastikan import ini sesuai package-mu
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        // 1. Ambil data session
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

        lifecycleScope.launch {
            delay(2000)

            if (isLoggedIn) {
                // Jika user sudah login, langsung masuk Dashboard (BaseActivity)
                startActivity(Intent(this@SplashActivity, BaseActivity::class.java))
            } else {
                // JIKA BELUM LOGIN, arahkan ke Onboarding/Tutorial Screen terlebih dahulu
                startActivity(Intent(this@SplashActivity, TutorialMessageActivity::class.java))
            }
            finish()
        }
    }
}