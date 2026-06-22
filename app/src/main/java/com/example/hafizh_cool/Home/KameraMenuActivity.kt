package com.example.hafizh_cool.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hafizh_cool.Home.fitur_kamera.KameraTabsAdapter
import com.example.hafizh_cool.databinding.ActivityKameraMenuBinding
import com.google.android.material.tabs.TabLayoutMediator

class KameraMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKameraMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKameraMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar & Tombol Back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        // Setup Adapter ViewPager2
        val adapter = KameraTabsAdapter(this)
        binding.viewPager.adapter = adapter

        // Hubungkan TabLayout dengan ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Ambil Foto"
                1 -> "Scan QR"
                2 -> "Buat QR"
                else -> ""
            }
        }.attach()
    }
}