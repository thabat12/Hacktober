package com.example.hacktober.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.hacktober.R
import com.example.hacktober.firebase.FirestoreClass
import com.example.hacktober.models.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )
        continue_setup.setOnClickListener {
            registerUser()
            startActivity(Intent(this@SignupActivity, PinActivity::class.java))
            finish()

        }
    }

    private fun registerUser(){
        val email: String = username.text.toString().trim { it <= ' ' }
        val password: String = password.text.toString().trim { it <= ' ' }
        val passwordConfirm: String = confirm_password.text.toString().trim { it <= ' ' }

        if (! password.equals(passwordConfirm)) { return }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        val registeredEmail = firebaseUser.email!!
                        val user = User(firebaseUser.uid, email, password)
                        FirestoreClass().registerUser(this@SignupActivity, user)
                    } else {
                        Toast.makeText(
                            this@SignupActivity,
                            "Registration failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })


    }

}