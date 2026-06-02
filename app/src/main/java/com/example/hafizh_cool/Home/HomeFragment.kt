package com.example.hafizh_cool.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hafizh_cool.Home.LoginActivity
import com.example.hafizh_cool.Home.Village.VillageActivity
import com.example.hafizh_cool.Home.news.NewsAdapter
import com.example.hafizh_cool.data.api.NewsApiClient
import com.example.hafizh_cool.databinding.FragmentHomeBinding
import com.example.hafizh_cool.ui.BeritaActivity
import com.example.hafizh_cool.ui.AgendaActivity // 💡 Import AgendaActivity Baru
import com.google.android.material.chip.Chip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        loadNewsData()

        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(requireContext(), "Kategori: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

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

        binding.btnMoreMenu.setOnClickListener {
            moveActivity(MoreMenuActivity::class.java)
        }

        binding.btnProfilDesa.setOnClickListener {
            moveActivity(VillageActivity::class.java)
        }

        // Navigasi tombol ke BeritaActivity
        binding.btnBeritaLocal.setOnClickListener {
            moveActivity(BeritaActivity::class.java)
        }

        // 💡 AKSI UTAMA BARU: Navigasi tombol ke AgendaActivity
        binding.btnAgendaLocal.setOnClickListener {
            moveActivity(AgendaActivity::class.java)
        }

        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun loadNewsData() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            try {
                val responseContainer = NewsApiClient.apiService.getNews()
                val listBeritaAsli = responseContainer.results

                withContext(Dispatchers.Main) {
                    if (listBeritaAsli.isNotEmpty()) {
                        val newsAdapter = NewsAdapter(listBeritaAsli)
                        binding.rvNews.layoutManager = LinearLayoutManager(requireContext())
                        binding.rvNews.adapter = newsAdapter
                    } else {
                        Toast.makeText(requireContext(), "Tidak ada berita tersedia", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    android.util.Log.e("RETROFIT_BUG", "Penyebab: ${e.message}", e)
                }
            }
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