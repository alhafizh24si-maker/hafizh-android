package com.example.hafizh_cool.Home.Village

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fzhmobile.Home.pertemuan_10.VillageModel
import com.example.hafizh_cool.R

class TabAFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 🌟 KUNCI UTAMA: Inflate layout item_village yang sudah kita perbaiki!
        return inflater.inflate(R.layout.item_village, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Buat data model khusus Desa Flamboyan
        val desaFlamboyan = VillageModel(
            name = "Desa Flamboyan Asri 009",
            kecamatan = "Kecamatan Mawar",
            kabupaten = "Kabupaten Subulussalam",
            provinsi = "Provinsi Kepulauan Riau",
            email = "email@desa.id",
            telepon = "0812345678",
            visi = "Isi Visi Desa Flamboyan yang maju dan sejahtera.",
            misi = "Isi Misi Desa Flamboyan meningkatkan pelayanan masyarakat.",
            alamat = "Jl. Flamboyan No. 9, RT 01/RW 02"
        )

        // 2. Hubungkan data model ke ID komponen yang ada di item_village.xml
        view.findViewById<TextView>(R.id.tvVillageName).text = desaFlamboyan.name
        view.findViewById<TextView>(R.id.tvKecamatan).text = desaFlamboyan.kecamatan
        view.findViewById<TextView>(R.id.tvKabupaten).text = desaFlamboyan.kabupaten
        view.findViewById<TextView>(R.id.tvProvinsi).text = desaFlamboyan.provinsi
        view.findViewById<TextView>(R.id.tvEmail).text = desaFlamboyan.email
        view.findViewById<TextView>(R.id.tvTelepon).text = desaFlamboyan.telepon
        view.findViewById<TextView>(R.id.tvVisi).text = desaFlamboyan.visi
        view.findViewById<TextView>(R.id.tvMisi).text = desaFlamboyan.misi
        view.findViewById<TextView>(R.id.tvAlamat).text = desaFlamboyan.alamat
    }
}