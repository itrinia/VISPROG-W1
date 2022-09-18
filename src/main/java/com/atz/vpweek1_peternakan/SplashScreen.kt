package com.atz.vpweek1_peternakan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
            supportActionBar?.hide()
            Handler().postDelayed({

                val splashh = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(splashh)
                finish()
            },3000)
        }
    }
