package com.example.hafizh_cool.Home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hafizh_cool.BaseActivity
import com.example.hafizh_cool.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        // 1. Ambil data session (HANYA MEMBACA, jangan menulis/put di sini!)
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

        lifecycleScope.launch {
            delay(2000)

            if (isLoggedIn) {
                // Jika data di HP bilang TRUE, masuk Dashboard
                startActivity(Intent(this@SplashActivity, BaseActivity::class.java))
            } else {
                // Jika data di HP bilang FALSE, masuk Login
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
            finish()
        }
    }
}