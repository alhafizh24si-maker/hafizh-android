package com.example.hafizh_cool.Home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hafizh_cool.databinding.ActivityGmailInputBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GmailInputActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGmailInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGmailInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLanjut.setOnClickListener {
            val email = binding.editTextEmail.text.toString()

            when {
                email.isEmpty() -> {
                    // Validasi: Tidak boleh kosong
                    binding.inputLayoutGmail.error = "Email wajib diisi"
                }
                !email.endsWith("@gmail.com") -> {
                    // Validasi: Harus @gmail.com (Tampilkan MaterialAlertDialog)
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Format Salah")
                        .setMessage("Silakan gunakan email dengan domain @gmail.com")
                        .setPositiveButton("OK", null)
                        .show()
                }
                else -> {
                    // Jika valid: Pindah ke Registrasi dan bawa emailnya
                    val intent = Intent(this, RegisterActivity::class.java)
                    intent.putExtra("EXTRA_EMAIL", email)
                    startActivity(intent)
                }
            }
        }
    }
}