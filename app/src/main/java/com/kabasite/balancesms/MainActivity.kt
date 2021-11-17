package com.kabasite.balancesms

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun isPermissionGranted(context: Context, permission: String) : Boolean {
    return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
}

fun requestPermission(activity: Activity?, permission: String, requestCode: Int){
    ActivityCompat.requestPermissions(activity!!, arrayOf(permission), requestCode)
}

class MainActivity : AppCompatActivity() {
//    private lateinit var btnSend: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        btnSend = findViewById(R.id.btnSendId)
//        btnSend.setOnClickListener {
//            if(!isPermissionGranted(this, android.Manifest.permission.SEND_SMS)){
//                requestPermission(this,android.Manifest.permission.SEND_SMS, 42069)
//            }
//            else{
//                sendSms()
//            }
//        }
        if(!isPermissionGranted(this, android.Manifest.permission.SEND_SMS)){
            requestPermission(this,android.Manifest.permission.SEND_SMS, 42069)
        }
        else{
            sendSms()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 42069){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                sendSms()
            }
            else{
                Toast.makeText(this, "Need sms sending persmission", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun sendSms(){
        Toast.makeText(this, "Sending SMS...", Toast.LENGTH_SHORT).show()
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage("199", null, "Bal", null, null)
//        class timer : CountDownTimer(2000, 1000){
//            override fun onTick(p0: Long) {
//
//            }
//
//            override fun onFinish() {
//                finish()
//            }
//
//        }
//        var timer1 = timer()
//        timer1.start()
        finish()
    }

}


