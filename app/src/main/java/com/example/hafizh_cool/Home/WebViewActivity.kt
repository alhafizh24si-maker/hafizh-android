package com.example.hafizh_cool.Home

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.hafizh_cool.data.utils.NotificationHelper
import com.example.hafizh_cool.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Portal Bina Desa"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Setup WebView
        binding.webView.apply {
            settings.javaScriptEnabled = true

            // 🔔 Modifikasi WebViewClient untuk mendeteksi halaman selesai dimuat
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    // Membuat intent agar jika notifikasi diklik, user tetap di halaman WebView ini
                    val intentNotif = Intent(this@WebViewActivity, WebViewActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP // Mencegah activity menumpuk berulang kali
                    }

                    // Memicu Local Notification native Android
                    NotificationHelper.showNotification(
                        context = this@WebViewActivity,
                        title = "Portal Desa Terhubung!",
                        message = "Anda sedang mengakses portal resmi layanan digital Bina Desa.",
                        intent = intentNotif
                    )
                }
            }

            // Memuat URL Portal Bina Desa Anda
            loadUrl("https://portal-guest.alwaysdata.net/login")
        }

        // Sinkronisasi Scroll WebView dengan Toolbar
        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.appBar.setExpanded(false, true) // Sembunyikan toolbar
            } else if (scrollY < oldScrollY) {
                binding.appBar.setExpanded(true, true)  // Tampilkan toolbar
            }
        }
    }

    // Navigasi tombol kembali pada toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    // Kontrol Back Button Hardware
    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack() // Kembali ke halaman web sebelumnya
        } else {
            super.onBackPressed() // Keluar dari Activity
        }
    }
}