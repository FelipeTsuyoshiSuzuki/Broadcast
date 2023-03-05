package com.example.broadcast.ui

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.broadcast.databinding.FragmentMainBinding
import com.example.broadcast.utils.Counter
import com.example.broadcast.utils.receiver.AirPlaneReceiver
import com.example.broadcast.utils.receiver.BatteryReceiver
import com.example.broadcast.utils.service.NotificationCountService

class MainFragment : Fragment() {
    lateinit var airplaneReceiver: AirPlaneReceiver
    lateinit var batteryReceiver: BatteryReceiver
    lateinit var binding: FragmentMainBinding
    val viewModel = MainViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        airplaneReceiver = AirPlaneReceiver()
        batteryReceiver = BatteryReceiver()
        val service = NotificationCountService(requireContext())

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            requireContext().registerReceiver(airplaneReceiver, it)
        }
        IntentFilter(Intent.ACTION_POWER_CONNECTED).also {
            requireContext().registerReceiver(batteryReceiver, it)
        }
        IntentFilter(Intent.ACTION_POWER_DISCONNECTED).also {
            requireContext().registerReceiver(batteryReceiver, it)
        }
        binding.materialButton.setOnClickListener {
            service.showNotification(Counter.value)
        }
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(airplaneReceiver)
        requireContext().unregisterReceiver(batteryReceiver)
    }
}