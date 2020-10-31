package com.example.hacktober.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.hacktober.R
import kotlinx.android.synthetic.main.activity_pin.*

// https://github.com/aritraroy/PinLockView

class PinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )
        skip_pin.setOnClickListener {
            startActivity(
                Intent(this@PinActivity, MainActivity::class.java)
            )
        }
    }
}