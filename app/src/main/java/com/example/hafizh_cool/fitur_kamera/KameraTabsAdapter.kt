package com.example.hafizh_cool.Home.fitur_kamera

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.binadesa.Home.fitur_kamera.FotoGaleriFragment
import com.example.binadesa.Home.fitur_kamera.ScanQrFragment

class KameraTabsAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FotoGaleriFragment()
            1 -> ScanQrFragment()
            2 -> BuatQrFragment()
            else -> FotoGaleriFragment()
        }
    }
}