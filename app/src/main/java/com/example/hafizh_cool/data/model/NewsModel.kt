package com.example.hafizh_cool.data.model

import com.google.gson.annotations.SerializedName

// Kelas pembungkus utama untuk menangkap array bernama "results"
data class SpaceNewsResponse(
    val results: List<NewsModel>
)

data class NewsModel(
    val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("summary") val summary: String,
    @SerializedName("news_site") val source: String // Mengambil nama situs berita asli sebagai sumber
)