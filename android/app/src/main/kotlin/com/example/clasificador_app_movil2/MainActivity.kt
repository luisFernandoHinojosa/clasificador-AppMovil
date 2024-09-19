package com.example.clasificador_app_movil2

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import io.flutter.embedding.android.FlutterActivity

class MainActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Iniciar el servicio cuando la aplicación se inicia
        val intent = Intent(this, VolumeService::class.java)
        ContextCompat.startForegroundService(this, intent)
    }
}
