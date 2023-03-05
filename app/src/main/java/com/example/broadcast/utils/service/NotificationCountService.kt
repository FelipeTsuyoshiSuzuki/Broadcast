package com.example.broadcast.utils.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.broadcast.R
import com.example.broadcast.ui.MainActivity
import com.example.broadcast.utils.receiver.CounterNotificationReceiver

class NotificationCountService(
    private val context: Context
) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(counter: Int) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            INCREMENT_NOTIFICATION_REQUEST_CODE,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val incrementIntent = PendingIntent.getBroadcast(
            context,
            INCREMENT_REQUEST_CODE,
            Intent(context, CounterNotificationReceiver::class.java),
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val notification = NotificationCompat.Builder(context, INCREMENT_NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_increment_notification)
            .setContentTitle("Increment counter")
            .setContentText("The count is $counter")
            .setContentIntent(activityPendingIntent)
            .addAction(
                R.drawable.ic_increment_notification,
                "increment",
                incrementIntent
            )
            .build()

        notificationManager.notify(
            INCREMENT_NOTIFICATION_ID,
            notification
        )
    }

    companion object {
        const val INCREMENT_NOTIFICATION_CHANNEL_ID = "counter_channel"
        const val INCREMENT_NOTIFICATION_REQUEST_CODE = 126
        const val INCREMENT_REQUEST_CODE = 2
        const val INCREMENT_NOTIFICATION_ID = 3
    }

}