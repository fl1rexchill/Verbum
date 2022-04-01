package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.flirex.verbum.R

class Progress : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
    }
    fun onClickGoTasks(view: View){
        var goTasks = Intent(this, Tasks::class.java)
        startActivity(goTasks)
    }
    fun onClickGoSettings(view: View){
        var goSettings = Intent(this, Settings::class.java)
        startActivity(goSettings)
    }
}