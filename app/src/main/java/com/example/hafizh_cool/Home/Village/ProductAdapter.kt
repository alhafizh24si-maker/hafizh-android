package com.example.hafizh_cool.Home.Village

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hafizh_cool.databinding.ItemProductBinding

class ProductAdapter(
    private val newsList: List<NewsModel>,
    private val onItemClick: (NewsModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = newsList[position]
        with(holder.binding) {
            // tvProductName sekarang menampilkan Judul Berita
            tvProductName.text = item.title
            // tvProductPrice sekarang menampilkan Tanggal Berita
            tvProductPrice.text = item.date

            // Memuat gambar berita menggunakan Glide
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .into(imgProduct)

            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = newsList.size
}