package com.example.hafizh_cool.Home.Village

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fzhmobile.Home.Village.TabCFragment

class VillageAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabAFragment() // Memanggil Fragment desa Flamboyan milikmu
            1 -> TabBFragment() // Memanggil Fragment desa Teratai milikmu
            2 -> TabCFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}