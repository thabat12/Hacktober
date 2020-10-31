package com.example.hacktober.firebase

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import com.example.hacktober.activities.IntroActivity
import com.example.hacktober.activities.SignupActivity
import com.example.hacktober.activities.SplashActivity
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

    fun signInUser(activity: Activity) {

        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                Log.e(activity.javaClass.simpleName, document.toString())
                val loggedInUser = document.toObject(User::class.java)!!
                when (activity) {
                    is IntroActivity -> {
                        activity.signInSuccess(loggedInUser)
                    }
                    is SplashActivity -> {

                    }
                }
            }
            .addOnFailureListener { e ->
                when (activity) {
                    is IntroActivity -> {
                        Log.e(activity.javaClass.simpleName, "Error getting in logged in user details")
                    }
                    is SplashActivity -> {
                        Log.e(activity.javaClass.simpleName, "Error getting in logged in user details")
                    }
                }

            }
    }

    fun getCurrentUserID(): String {

        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }

}