package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import com.flirex.verbum.R


class Vhod : Activity() {
    var tvRegist: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vhod)
        tvRegist = findViewById(R.id.tvRegist)
        val content = SpannableString("Регистрация")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        tvRegist?.setText(content)
    }
    fun onClickGoRegist(view: View){
        var goRegist = Intent(this, Regist::class.java)
        startActivity(goRegist)
    }
    fun onClickGoTasks(view: View){
        var goTasks = Intent(this, Tasks::class.java)
        startActivity(goTasks)
    }
}