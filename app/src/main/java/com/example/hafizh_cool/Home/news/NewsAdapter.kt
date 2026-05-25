package com.example.hafizh_cool.Home.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hafizh_cool.data.model.NewsModel
import com.example.hafizh_cool.databinding.ItemNewsBinding

class NewsAdapter(private val newsList: List<NewsModel>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    // ViewHolder sebagai pengikat komponen XML item_news
    inner class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        // Menginflate layout item satuan berita
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = newsList[position]

        // Memasukkan data ke dalam widget text layout item
        holder.binding.tvNewsTitle.text = item.title
        holder.binding.tvNewsSummary.text = item.summary
        holder.binding.tvNewsSource.text = "Sumber: ${item.source}"
    }

    // CRITICAL BUGFIX: Pastikan bagian ini mengembalikan ukuran list sesungguhnya (Bukan 0!)
    override fun getItemCount(): Int {
        return newsList.size
    }
}