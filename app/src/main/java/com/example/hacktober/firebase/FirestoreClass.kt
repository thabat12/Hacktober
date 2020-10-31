package com.example.hacktober.firebase

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import com.example.hacktober.activities.IntroActivity
import com.example.hacktober.activities.SignupActivity
import com.example.hacktober.models.User
import com.example.hacktober.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()
    lateinit var userObject : User

    @SuppressLint("LongLogTag")
    fun registerUser(activity: Activity, userInfo: User) {

        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                Log.e("User Registration Success:", userInfo.toString())
            }
            .addOnFailureListener { e ->
                Log.e("User Registration Failed:", userInfo.toString())
            }
    }

    private fun getCurrentUserID(): String {

        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

    fun signInUser(activity: Activity) {

        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
            }
            .addOnFailureListener { e ->

            }
    }

}