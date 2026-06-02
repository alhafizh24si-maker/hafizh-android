package com.example.hafizh_cool.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hafizh_cool.data.dao.BeritaDao
import kotlinx.coroutines.launch

class BeritaViewModel(private val beritaDao: BeritaDao) : ViewModel() {

    fun tambahBeritaBaru(judul: String, isi: String, urlGambar: String) {
        viewModelScope.launch {
            // Membuat objek dari BeritaLocal, variabelnya sekarang sudah pas dan cocok!
            val beritaBaru = BeritaLocal(
                judul = judul,
                isi = isi,
                urlGambar = urlGambar
            )
            beritaDao.insertBerita(beritaBaru)
        }
    }
}