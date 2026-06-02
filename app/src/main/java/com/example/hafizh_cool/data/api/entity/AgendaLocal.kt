package com.example.hafizh_cool.data.api.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AgendaLocal")
data class AgendaLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val judul: String,
    val tanggal: String,
    val lokasi: String,
    val keterangan: String
)