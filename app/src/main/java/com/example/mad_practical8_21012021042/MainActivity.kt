package com.example.mad_practical8_21012021042

import android.app.PendingIntent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
fun setalarm(militime:Long,action:String){
    val intentalarm = Intent(applicationContext,AlarmBroadcastReciever::class.java)
    intentalarm.putExtra(AlarmBroadcastReciever.ALARMKEY)
    val pendingalarm= PendingIntent.getBroadcast(applicationContext,2345,intentalarm,PendingIntent.FLAG_UPDATE_CURRENT)
    val manager = getSystemService(ALARM_SERVICE)

    if(action==)

