package com.example.pls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.activity_info.backBtn
import kotlinx.android.synthetic.main.activity_update_password.*

class InfoActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        init()

        saveinfoBtn.setOnClickListener {

            val n: String = inputName.text.toString()
            val p: String = inputPhone.text.toString()

            if (TextUtils.isEmpty(n)) {
                Toast.makeText(this, "enter a name", Toast.LENGTH_LONG).show()
            } else {
                contactInfo(n, p)
            }

        }

    }

    private fun init() {

        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().getReference("UserInfo")

        addUserInfoChangeListener()

    }

    private fun contactInfo(name: String, phone: String?) {
        val userInfo = UserInfo(name, phone)
        db.child(auth.currentUser?.uid!!).setValue(userInfo)
    }

    private fun addUserInfoChangeListener() {

        db.child(auth.currentUser?.uid!!)
            .addValueEventListener(object : ValueEventListener {

                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(snap: DataSnapshot) {

                    val userInfo: UserInfo = snap.getValue(UserInfo::class.java) ?: return

                    showName.text = userInfo.name
                    showPhone.text = userInfo.mobile ?: ""

                    inputName.setText("")
                    inputPhone.setText("")


                }



            })
        backBtn.setOnClickListener {
            super.onBackPressed()
    }

}
}