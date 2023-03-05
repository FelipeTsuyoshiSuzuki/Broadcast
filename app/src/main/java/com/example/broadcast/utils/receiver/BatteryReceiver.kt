package com.example.broadcast.utils.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BatteryReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_POWER_CONNECTED) Toast.makeText(
            context,
            "Esta Carregando",
            Toast.LENGTH_LONG
        ).show()
        else if (intent?.action == Intent.ACTION_POWER_DISCONNECTED) Toast.makeText(
            context,
            "Não esta carregando",
            Toast.LENGTH_LONG
        ).show()
    }
}