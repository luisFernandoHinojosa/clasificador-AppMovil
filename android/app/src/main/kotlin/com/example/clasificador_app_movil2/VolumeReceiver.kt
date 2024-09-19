package com.example.clasificador_app_movil2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import androidx.core.content.ContextCompat
import android.util.Log


class VolumeReceiver : BroadcastReceiver() {
    private var pressCount = 0
    private val pressThreshold = 3
    private var lastPressTime: Long = 0

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.media.VOLUME_CHANGED_ACTION") {
            val currentTime = System.currentTimeMillis()
            val timeDiff = currentTime - lastPressTime
    
            if (timeDiff < 1000) {
                pressCount++
            } else {
                pressCount = 0
            }
    
            lastPressTime = currentTime
    
            if (pressCount >= pressThreshold) {
                Log.d("VolumeReceiver", "Activadoo")
                val serviceIntent = Intent(context, VolumeService::class.java)
                ContextCompat.startForegroundService(context, serviceIntent)
            }           
        }
    }
}
