package com.example.mad_practical8_21012021042

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.WindowCompat
import com.example.mad_practical8_21012021042.AlarmBroadcastReciever.Companion.ALARM_START
import com.example.mad_practical8_21012021042.AlarmBroadcastReciever.Companion.ALARM_STOP
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window,false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.cardListAlarm.visibility= View.GONE
        binding.btnCreateAlarm.setOnClickListener{
            showTimerDialog()
        }
        binding.btnCancelAlarm.setOnClickListener{
            setAlarm(-1, ALARM_STOP)
            binding.cardListAlarm.visibility= View.GONE
        }
        setContentView(R.layout.activity_main)
    }
    private fun showTimerDialog(){
        val cal : Calendar = Calendar.getInstance()
        val hour : Int = cal.get(Calendar.HOUR_OF_DAY)
        val minutes : Int = cal.get(Calendar.MINUTE)
        val picker = TimePickerDialog(
            this,
            {tp, sHour , sMinute -> sendDialogDataToActivity(sHour,sMinute)},
            hour,
            minutes,
            false
        )
        picker.show()
    }
    private fun sendDialogDataToActivity(hour : Int , minute : Int){
        val alarmCalendar = Calendar.getInstance()
        val year: Int = alarmCalendar.get(Calendar.YEAR)
        val month: Int = alarmCalendar.get(Calendar.MONTH)
        val day: Int = alarmCalendar.get(Calendar.DATE)
        alarmCalendar.set(year,month,day,hour,minute,0)
        binding.textAlarmTime.text = SimpleDateFormat("hh:mm ss a").format(alarmCalendar.time)
        binding.cardListAlarm.visibility = View.VISIBLE
        setAlarm(alarmCalendar.timeInMillis, ALARM_START)
    }
}

private fun setAlarm(millisTime:Long,str:String) {
    val intent = Intent(this, AlarmBroadcastReciever::class.java)
    intent.putExtra(AlarmBroadcastReciever.ALARMKEY)
    val applicationContext= null
    val PendingIntent = PendingIntent.getBroadcast(
        applicationContext,
        234324243,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )
    val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
    if (str == ALARM_START) {
        val pendingIntent = null
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            millisTime,
            pendingIntent
        )
        Toast.makeText(this,ALARM_START,Toast.LENGTH_SHORT).show()
    }
    else if(str == ALARM_STOP){
        alarmManager.cancel(PendingIntent)
        sendBroadcast(intent);
        Toast.makeText(this, ALARM_STOP,Toast.LENGTH_SHORT).show()
    }
}



