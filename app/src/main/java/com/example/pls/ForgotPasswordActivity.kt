package com.example.pls

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_forgot_password.*
class ForgotPasswordActivity : AppCompatActivity()
{

    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        auth = FirebaseAuth.getInstance()

        backBtn.setOnClickListener {
            finish()
        }

        resetPassword.setOnClickListener {

            val email: String = email.text.toString()

            if (TextUtils.isEmpty(email)) {

                Toast.makeText(this, "enter a valid email address", Toast.LENGTH_LONG).show()

            } else {

                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "password reset link sent", Toast.LENGTH_LONG)
                                .show()
                        } else {
                            Toast.makeText(this, "email not found in database", Toast.LENGTH_LONG)
                                .show()
                        }
                    })

            }

        }

    }


}