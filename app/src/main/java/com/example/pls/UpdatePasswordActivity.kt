package com.example.pls

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_update_password.*

class UpdatePasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_password)

        auth = FirebaseAuth.getInstance()

        backBtn.setOnClickListener {
            finish()
        }

        changePassword.setOnClickListener {

            val password: String = password.text.toString()

            if (TextUtils.isEmpty(password)) {

                Toast.makeText(this, "fill it", Toast.LENGTH_LONG).show()

            } else {

                auth.currentUser?.updatePassword(password)
                    ?.addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(this, "nice", Toast.LENGTH_LONG)
                                .show()
                            finish()

                        } else {

                            Toast.makeText(this, "password change failed", Toast.LENGTH_LONG)
                                .show()

                        }
                    })

            }

        }

    }


}