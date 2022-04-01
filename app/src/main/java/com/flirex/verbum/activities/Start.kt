package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.flirex.verbum.R

class Start : Activity() {
    var tvVerbum: TextView? = null
    var tvBY: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        tvVerbum = findViewById(R.id.tvVerbum)
        tvBY = findViewById(R.id.tvBY)
        val intent = Intent(this, LoadingActivity::class.java)
        val handler = android.os.Handler()
        handler.postDelayed({ startActivity(intent) }, 2000)
    }
}