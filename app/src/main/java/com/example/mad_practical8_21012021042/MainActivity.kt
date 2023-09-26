package com.example.mad_practical8_21012021042

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
fun setalarm(militime:Long,action:String) {
    val intentalarm = Intent(application, AlarmBroadcastReciever::class.java)
    intentalarm.putExtra(AlarmBroadcastReciever.ALARMKEY)
    val pendingalarm = PendingIntent.getBroadcast(applicationContext, 2345, intentalarm, PendingIntent.FLAG_UPDATE_CURRENT)
    val manager = getSystemService(ALARM_SERVICE)as AlarmManager

    if (action ==AlarmBroadcastReciever.ALARM_START){
        manager.setExact(AlarmManager.RTC_WAKEUP,militime,pendingIntent)

    }
}

