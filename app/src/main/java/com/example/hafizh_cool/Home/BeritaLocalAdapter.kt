package com.example.hafizh_cool.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hafizh_cool.data.model.BeritaLocal
import com.example.hafizh_cool.databinding.ItemBeritaLocalBinding

class BeritaLocalAdapter(private val listBerita: List<BeritaLocal>) :
    RecyclerView.Adapter<BeritaLocalAdapter.BeritaViewHolder>() {

    class BeritaViewHolder(val binding: ItemBeritaLocalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val binding = ItemBeritaLocalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BeritaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita = listBerita[position]
        holder.binding.tvJudulBerita.text = berita.judul
        holder.binding.tvIsiBerita.text = berita.isi

        // Validasi gambar: Jika url tidak kosong, muat menggunakan Glide
        if (berita.urlGambar.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(berita.urlGambar)
                .centerCrop()
                .placeholder(android.R.color.darker_gray)
                .into(holder.binding.imgBerita)
        }
    }

    override fun getItemCount(): Int = listBerita.size
}