package com.example.hafizh_cool.data.api

import com.example.hafizh_cool.data.model.SpaceNewsResponse
import retrofit2.http.GET

interface NewsApiService {
    @GET("v4/articles/?limit=10") // Kita batasi hanya memuat 10 berita teratas
    suspend fun getNews(): SpaceNewsResponse
}