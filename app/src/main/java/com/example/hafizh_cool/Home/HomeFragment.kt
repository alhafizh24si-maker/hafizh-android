package com.example.hafizh_cool.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.hafizh_cool.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = (requireActivity() as AppCompatActivity)
        activity.setSupportActionBar(binding.toolbar)

        // Menyesuaikan ID Button dengan XML yang kamu berikan
        binding.btnRumus.setOnClickListener {
            moveActivity(kalkulator::class.java)
        }

        binding.btnCustom1.setOnClickListener {
            moveActivity(CustomOneActivity::class.java)
        }

        binding.btnCustom2.setOnClickListener {
            moveActivity(CustomTwoActivity::class.java)
        }

        binding.btnWebView.setOnClickListener {
            moveActivity(WebViewActivity::class.java)
        }

        // Logika Logout
        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun moveActivity(cls: Class<*>) {
        val intent = Intent(requireContext(), cls)
        startActivity(intent)
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Logout")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { _, _ ->
                // Clear Session
                val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                sharedPref.edit().clear().apply()

                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}