package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import com.flirex.verbum.R

class Regist : Activity() {
    var tvVhod: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)
        tvVhod = findViewById(R.id.tvVhod)
        val content = SpannableString("Вход")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        tvVhod?.setText(content)
    }
    fun onClickGoVhod(view: View){
        var goVhod = Intent(this, Vhod::class.java)
        startActivity(goVhod)
    }
}