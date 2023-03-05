package com.example.broadcast.utils.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.broadcast.utils.Counter
import com.example.broadcast.utils.service.NotificationCountService

class CounterNotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val service = NotificationCountService(context)
        service.showNotification(Counter.value++)
    }
}