package com.example.hafizh_cool.Home

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hafizh_cool.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Tangkap email dari Intent (Soal a2)
        val emailFromGmail = intent.getStringExtra("EXTRA_EMAIL")
        binding.editTextEmail.setText(emailFromGmail)

        // Buat email tidak bisa diubah (Soal a2)
        binding.editTextEmail.isEnabled = false

        binding.buttonDaftar.setOnClickListener {
            val nama = binding.editTextNama.text.toString()
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            // 2. Validasi Registrasi (Soal a2)
            when {
                nama.isEmpty() || username.isEmpty() || password.isEmpty() -> {
                    Toast.makeText(this, "Semua field wajib diisi!", Toast.LENGTH_SHORT).show()
                }
                password.length < 6 -> {
                    binding.inputLayoutPassword.error = "Password minimal 6 karakter"
                }
                username.contains(" ") -> {
                    binding.inputLayoutUsername.error = "Username tidak boleh mengandung spasi"
                }
                else -> {
                    // 3. Simpan ke SharedPreferences (Soal a2)
                    val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putString("saved_username", username)
                    editor.putString("saved_password", password)
                    editor.putString("saved_nama", nama)
                    editor.apply()

                    // Tampilkan pesan berhasil
                    Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_LONG).show()

                    // Kembali ke halaman Login
                    finish()
                }
            }
        }
    }
}