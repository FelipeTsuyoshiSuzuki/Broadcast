package com.example.broadcast

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.broadcast.utils.service.NotificationCountService.Companion.INCREMENT_NOTIFICATION_CHANNEL_ID

class Aplication : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                INCREMENT_NOTIFICATION_CHANNEL_ID,
                "Counter",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Usado para a notificação de incrementar contador"

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}