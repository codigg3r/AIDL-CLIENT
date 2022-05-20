package com.provisionpay.aidlclient

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.provisionpay.softpos.IDeviceHealth

class MainActivity : AppCompatActivity() {

    private var mService: IDeviceHealth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<android.widget.Button>(R.id.button)
        val imageView = findViewById<android.widget.ImageView>(R.id.imageView)
        button.setOnClickListener {
            try {
                Toast.makeText(
                    this, "" + mService?.echo(),
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
        Intent().apply {
            setPackage("com.provisionpay.softpos.igor")
            action = "DeviceHealthService"
            bindService(this, serviceConnection, BIND_AUTO_CREATE)
        }
    }


    val serviceConnection = object : ServiceConnection {

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            mService = IDeviceHealth.Stub.asInterface(p1)
            runOnUiThread {
                Toast.makeText(
                    this@MainActivity,
                    "Device Health -> ${mService?.echo()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            runOnUiThread {
                Toast.makeText(this@MainActivity, "connection failed $p0", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    override fun onStop() {
        super.onStop()
        unbindService(serviceConnection)
    }

}