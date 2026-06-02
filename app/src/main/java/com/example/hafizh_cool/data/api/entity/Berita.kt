package com.example.hafizh_cool.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BeritaLocal")
data class BeritaLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judul: String,
    val isi: String,
    val urlGambar: String
)