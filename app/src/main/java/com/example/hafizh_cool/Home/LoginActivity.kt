package com.example.hafizh_cool.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hafizh_cool.BaseActivity
import com.example.hafizh_cool.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fitur: Pindah ke halaman Input Gmail (Soal a1)
        binding.buttonRegisterGmail.setOnClickListener {
            startActivity(Intent(this, GmailInputActivity::class.java))
        }

        binding.buttonMasuk.setOnClickListener {
            val inputUser = binding.editTextTextEmailAddress.text.toString()
            val inputPass = binding.editTextTextPassword.text.toString()

            // Ambil data dari SharedPreferences (Data Registrasi Soal a2)
            val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            val savedUser = sharedPref.getString("saved_username", null)
            val savedPass = sharedPref.getString("saved_password", null)

            // Logika Login (Soal a3)
            val isRule1 = inputUser == inputPass && inputUser.isNotEmpty()
            val isRule2 = inputUser == savedUser && inputPass == savedPass && savedUser != null

            if (isRule1 || isRule2) {
                // Berhasil Login
                val sessionPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                sessionPref.edit().putBoolean("isLoggedIn", true).apply()

                Toast.makeText(this, "Selamat Datang di Binadesa", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, BaseActivity::class.java))
                finish()
            } else {
                // Gagal: Tampilkan MaterialAlertDialog (Soal a3)
                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau Password salah!")
                    .setPositiveButton("Coba Lagi", null)
                    .show()

                // Opsional: Tetap tampilkan error di field
                binding.inputLayoutEmail.error = "Periksa kembali"
                binding.inputLayoutPassword.error = "Periksa kembali"
            }
        }
    }
}