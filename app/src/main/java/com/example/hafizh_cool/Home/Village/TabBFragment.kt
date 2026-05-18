package com.example.hafizh_cool.Home.Village

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fzhmobile.Home.pertemuan_10.VillageModel
import com.example.hafizh_cool.R

class TabBFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menggunakan layout item_village yang sama karena strukturnya identik
        return inflater.inflate(R.layout.item_village, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Buat data model khusus Desa Teratai
        val desaTeratai = VillageModel(
            name = "Desa Teratai Indah 007",
            kecamatan = "Kecamatan Mulyo",
            kabupaten = "Kabupaten Semarang",
            provinsi = "Provinsi Jawa Tengah",
            email = "teratai@desa.id",
            telepon = "0877123456",
            visi = "Menjadi desa wisata yang asri dan berbasis digital.",
            misi = "Mengembangkan UMKM desa melalui inovasi teknologi.",
            alamat = "Jl. Teratai Raya No. 7, RT 05/RW 01"
        )

        // 2. Hubungkan data model ke ID komponen
        view.findViewById<TextView>(R.id.tvVillageName).text = desaTeratai.name
        view.findViewById<TextView>(R.id.tvKecamatan).text = desaTeratai.kecamatan
        view.findViewById<TextView>(R.id.tvKabupaten).text = desaTeratai.kabupaten
        view.findViewById<TextView>(R.id.tvProvinsi).text = desaTeratai.provinsi
        view.findViewById<TextView>(R.id.tvEmail).text = desaTeratai.email
        view.findViewById<TextView>(R.id.tvTelepon).text = desaTeratai.telepon
        view.findViewById<TextView>(R.id.tvVisi).text = desaTeratai.visi
        view.findViewById<TextView>(R.id.tvMisi).text = desaTeratai.misi
        view.findViewById<TextView>(R.id.tvAlamat).text = desaTeratai.alamat
    }
}