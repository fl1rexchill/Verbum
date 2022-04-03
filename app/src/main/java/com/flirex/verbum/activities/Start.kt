package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Start : Activity() {
    var tvVerbum: TextView? = null
    var tvBY: TextView? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        tvVerbum = findViewById(R.id.tvVerbum)
        tvBY = findViewById(R.id.tvBY)
        val db = Firebase.firestore
        auth = Firebase.auth
        val currentUser = auth.currentUser
        db.collection("users").document(currentUser!!.uid)
            .get()
        val intent = Intent(this, LoadingActivity::class.java)
        val handler = android.os.Handler()
        handler.postDelayed({ startActivity(intent) }, 2000)
    }
}