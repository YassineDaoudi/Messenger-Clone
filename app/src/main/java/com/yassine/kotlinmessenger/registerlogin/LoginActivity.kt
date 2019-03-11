package com.yassine.kotlinmessenger.registerlogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.yassine.kotlinmessenger.R
import com.yassine.kotlinmessenger.messages.LatestMessagesActivities
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {

            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()

            Log.d("LoginActivity", "email: $email")
            Log.d("LoginActivity", "password: $password")


            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {

                   if (!it.isSuccessful) return@addOnCompleteListener

                    Log.d("Main", "User successfully logedin: ${it.result!!.user.uid}")

                    val intent = Intent(this,LatestMessagesActivities::class.java)
                    startActivity(intent)

                }
                .addOnFailureListener {
                    Log.d("Main","Failed to login the user: ${it.message}")
                }
        }

            back_to_register_text_view.setOnClickListener {
                finish()
            }
    }
}