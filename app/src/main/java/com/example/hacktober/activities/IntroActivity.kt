package com.example.hacktober.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.hacktober.R
import com.example.hacktober.firebase.FirestoreClass
import com.example.hacktober.models.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )

        intro_to_register.setOnClickListener {
            startActivity(Intent(this@IntroActivity, SignupActivity::class.java))
        }

        log_in.setOnClickListener {
            val email = intro_email.text.toString()
            val password = intro_password.text.toString()
            signInRegisteredUser(email, password)
        }

    }

    fun signInRegisteredUser(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.e("Task", "is successful")
                    FirestoreClass().signInUser(this@IntroActivity)
                } else {
                    Toast.makeText(
                        this@IntroActivity,
                        task.exception!!.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    fun signInSuccess(user: User) {
        startActivity(Intent(this@IntroActivity, MainActivity::class.java))
    }
}