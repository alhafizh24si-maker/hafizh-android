package com.example.hafizh_cool.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hafizh_cool.data.api.entity.AgendaLocal
import com.example.hafizh_cool.databinding.ItemAgendaLocalBinding

class AgendaLocalAdapter(private val listAgenda: List<AgendaLocal>) :
    RecyclerView.Adapter<AgendaLocalAdapter.AgendaViewHolder>() {

    class AgendaViewHolder(val binding: ItemAgendaLocalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaViewHolder {
        val binding = ItemAgendaLocalBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AgendaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgendaViewHolder, position: Int) {
        val agenda = listAgenda[position]
        holder.binding.tvJudulAgenda.text = agenda.judul
        holder.binding.tvTanggalAgenda.text = "Tanggal: ${agenda.tanggal}"
        holder.binding.tvLokasiAgenda.text = "Lokasi: ${agenda.lokasi}"
        holder.binding.tvKeteranganAgenda.text = agenda.keterangan
    }

    override fun getItemCount(): Int = listAgenda.size
}