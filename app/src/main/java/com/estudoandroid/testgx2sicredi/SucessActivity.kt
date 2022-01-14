package com.estudoandroid.testgx2sicredi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SucessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sucess)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            finish()
        }, 2000)
    }
}