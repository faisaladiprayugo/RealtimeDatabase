package com.android.realtimedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("USERS")

        btn_submit.setOnClickListener{saveData()}
        btn_show.setOnClickListener {
            val i = Intent(this, ShowActivity::class.java)
            startActivity(i)
        }
    }
    private fun saveData(){

        val nama = edt_nama.text.toString()
        val email = edt_email.text.toString()

        val userId = ref.push().key.toString()
        val user = Users(userId, nama, email)

        ref.child(userId).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            edt_nama.setText("")
            edt_email.setText("")

            val i = Intent(this, ShowActivity::class.java)
            startActivity(i)
        }
    }
}