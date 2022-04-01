package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.flirex.verbum.R

class Enter : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)
    }
    fun onClickGoVhod(view: View){
        val goVhod = Intent(this, Vhod::class.java)
        startActivity(goVhod)
    }
    fun onClickGoRegist(view: View){
        var goRegist = Intent(this, Regist::class.java)
        startActivity(goRegist)
    }
}