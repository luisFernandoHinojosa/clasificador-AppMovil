package com.example.clasificador_app_movil2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import android.util.Log

import android.os.VibrationEffect
import android.os.Vibrator


class VolumeService : Service() {
    private lateinit var volumeReceiver: VolumeReceiver

    override fun onCreate() {
        super.onCreate()
////////////////////////////////////////////
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))  // Vibra por 500ms
        } else {
            vibrator.vibrate(500)
        }

        volumeReceiver = VolumeReceiver()
        val filter = IntentFilter("android.media.VOLUME_CHANGED_ACTION")
        registerReceiver(volumeReceiver, filter)

        createNotificationChannel()
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Servicio de Volumen")
            .setContentText("Escuchando cambios en los botones de volumen.")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }
    

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(volumeReceiver)
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Canal del Servicio de Volumen"
            val descriptionText = "Canal para el servicio de volumen"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }
    // private fun createNotification(): Notification {
    //     return NotificationCompat.Builder(this, CHANNEL_ID)
    //         .setContentTitle("Servicio de Volumen")
    //         .setContentText("Escuchando cambios en los botones de volumen.")
    //         .setSmallIcon(android.R.drawable.ic_dialog_info)
    //         .build()
    // }

    companion object {
        const val CHANNEL_ID = "VolumeServiceChannel"
        const val NOTIFICATION_ID = 1
    }
}