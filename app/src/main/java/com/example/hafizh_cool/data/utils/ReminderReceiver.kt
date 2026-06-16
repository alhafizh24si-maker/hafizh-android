package com.example.hafizh_cool.data.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.hafizh_cool.ui.AgendaActivity

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        /**
        Aksi yang dilakukan saat reminder berhasil dipanggil.
        Dalam contoh ini ialah menampilkan NotificationHelper
         **/

        val title           = intent.getStringExtra("title") ?: "Pengingat Desa"
        val message         = intent.getStringExtra("message") ?: "Waktunya mengecek agenda desa!"
        val targetClassName = intent.getStringExtra("target_activity")

        val targetIntent = if (!targetClassName.isNullOrEmpty()) {
            val clazz = Class.forName(targetClassName)
            Intent(context, clazz).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        } else {
            // Mengarah secara tepat ke AgendaActivity di package ui Anda
            Intent(context, AgendaActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }

        NotificationHelper.showNotification(
            context = context,
            title = title,
            message = message,
            intent = targetIntent
        )
    }
}