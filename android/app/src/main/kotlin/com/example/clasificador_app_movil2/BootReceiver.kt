package com.example.clasificador_app_movil2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            val serviceIntent = Intent(context, VolumeService::class.java)
            ContextCompat.startForegroundService(context, serviceIntent)
        }
    }
}
