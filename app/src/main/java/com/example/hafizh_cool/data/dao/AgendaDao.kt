package com.example.hafizh_cool.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hafizh_cool.data.api.entity.AgendaLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendaDao {

    @Query("SELECT * FROM AgendaLocal ORDER BY id DESC")
    fun getAllAgendaLocal(): Flow<List<AgendaLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgenda(agenda: AgendaLocal)
}