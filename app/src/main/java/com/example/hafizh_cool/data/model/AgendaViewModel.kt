package com.example.hafizh_cool.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hafizh_cool.data.dao.AgendaDao
import com.example.hafizh_cool.data.api.entity.AgendaLocal
import kotlinx.coroutines.launch

class AgendaViewModel(private val agendaDao: AgendaDao) : ViewModel() {

    fun tambahAgendaBaru(judul: String, tanggal: String, lokasi: String, keterangan: String) {
        viewModelScope.launch {
            val agendaBaru = AgendaLocal(
                judul = judul,
                tanggal = tanggal,
                lokasi = lokasi,
                keterangan = keterangan
            )
            agendaDao.insertAgenda(agendaBaru)
        }
    }
}