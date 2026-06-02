package com.example.hafizh_cool.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hafizh_cool.data.model.BeritaLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface BeritaDao {

    // 💡 TAMBAHKAN QUERY INI (Sesuaikan nama tabel jika berbeda, misal: "berita_local" atau "BeritaLocal")
    @Query("SELECT * FROM BeritaLocal ORDER BY id DESC")
    fun getAllBeritaLocal(): Flow<List<BeritaLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBerita(berita: BeritaLocal)
}