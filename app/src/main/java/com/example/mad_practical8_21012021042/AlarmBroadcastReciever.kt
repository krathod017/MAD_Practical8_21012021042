package com.example.mad_practical8_21012021042

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmBroadcastReciever : BroadcastReceiver() {
    companion object {
        val ALARMKEY = "Alarm"
        val ALARM_STOP = "Alarmstop"
        val ALARM_START = "Alarmstart"
    }

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val data = intent.getStringExtra(ALARMKEY)
        if (data == ALARM_START || data == ALARM_STOP) {
            val intentService = Intent(context, AlarmService::class.java)
            intentService.putExtra(ALARMKEY,intent.getStringExtra(ALARMKEY))
            if (data == ALARM_START) {
                context.startService(intentService)
            } else if (data == ALARM_STOP) {
                context.stopService(intentService)
            }
        }
    }
}