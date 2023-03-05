package com.example.broadcast.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirPlaneReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val airplaneMode = intent?.getBooleanExtra("state", false) ?: return
        if (airplaneMode) Toast.makeText(context, "Modo avião ativado", Toast.LENGTH_LONG).show()
        else Toast.makeText(context, "Modo avião desativado", Toast.LENGTH_LONG).show()
    }
}