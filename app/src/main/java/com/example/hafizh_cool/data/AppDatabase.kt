package com.example.hafizh_cool.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hafizh_cool.data.api.entity.NoteEntity
import com.example.hafizh_cool.data.dao.AgendaDao
import com.example.hafizh_cool.data.dao.NoteDao
import com.example.hafizh_cool.data.dao.BeritaDao
import com.example.hafizh_cool.data.api.entity.AgendaLocal
// 💡 BARIS PERBAIKAN: Import kelas BeritaLocal agar dikenali oleh database
import com.example.hafizh_cool.data.model.BeritaLocal

@Database(
    entities = [NoteEntity::class, BeritaLocal::class, AgendaLocal::class], // 💡 Tambahkan AgendaLocal
    version = 3, // 💡 Naikkan versi ke 3 karena ada tabel baru
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun beritaDao(): BeritaDao

    abstract fun agendaDao(): AgendaDao // 💡 Tambahkan ini

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // Menghapus database lama jika versi naik agar tidak crash
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}