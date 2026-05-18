package com.example.fzhmobile.Home.Village

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hafizh_cool.Home.Village.NewsModel
import com.example.hafizh_cool.Home.Village.ProductAdapter
import com.example.hafizh_cool.databinding.FragmentTabCBinding

class TabCFragment : Fragment() {

    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    // 📰 DAFTAR DATA BERITA DESA
    private val newsList = listOf(
        NewsModel(
            "Gotong Royong Bersama Membersihkan Saluran Irigasi Desa",
            "15 Mei 2026",
            "https://picsum.photos/seed/village1/400/300"
        ),
        NewsModel("Penyaluran Bantuan BLT Tahap Dua Berjalan Lancar", "12 Mei 2026", "https://picsum.photos/seed/village2/400/300"),
        NewsModel("Pembangunan Jembatan Penghubung Dusun Resmi Dimulai", "10 Mei 2026", "https://picsum.photos/seed/village3/400/300"),
        NewsModel("Posyandu Balita dan Lansia Dusun Mawar Bulan Ini", "08 Mei 2026", "https://picsum.photos/seed/village4/400/300"),
        NewsModel("Pelatihan Digitalisasi UMKM untuk Pemuda Desa", "05 Mei 2026", "https://picsum.photos/seed/village5/400/300"),
        NewsModel("Desa Kita Raih Penghargaan Desa Terbersih Tingkat Kabupaten", "01 Mei 2026", "https://picsum.photos/seed/village6/400/300"),
        NewsModel("Rapat Koordinasi Persiapan Menyambut HUT RI Tingkat Desa", "28 April 2026", "https://picsum.photos/seed/village7/400/300"),
        NewsModel("Kunjungan Dinas Pertanian Guna Edukasi Pupuk Organik", "25 April 2026", "https://picsum.photos/seed/village8/400/300"),
        NewsModel("Kelompok Tani Desa Sukses Panen Raya Padi Organik", "20 April 2026", "https://picsum.photos/seed/village9/400/300"),
        NewsModel("Penyuluhan Kesehatan Mengenai Pencegahan Demam Berdarah", "15 April 2026", "https://picsum.photos/seed/village10/400/300")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengubungkan data berita ke Adapter
        val adapter = ProductAdapter(newsList) { selectedNews ->
            Toast.makeText(requireContext(), "Membuka: ${selectedNews.title}", Toast.LENGTH_SHORT)
                .show()
        }

        binding.rvProducts.apply {
            // Menggunakan Grid Mode dengan 2 Kolom (Bisa diganti LinearLayoutManager jika ingin memanjang ke bawah)
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}