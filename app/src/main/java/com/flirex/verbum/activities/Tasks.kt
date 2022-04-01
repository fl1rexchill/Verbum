package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Tasks : Activity() {
    var tvNewWords: TextView? = null
    var kolvo:Int = 6
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        tvNewWords = findViewById(R.id.tvNewWords)
        var priziv: String = "Выучить $kolvo новых слов"
        tvNewWords?.setText(priziv)
    }
    fun onClickGoProgress(view: View){
        var goProgress = Intent(this, Progress::class.java)
        startActivity(goProgress)
    }
    fun onClickGoSettings(view: View){
        var goSettings = Intent(this, Settings::class.java)
        startActivity(goSettings)
    }
    fun onClickStartDayLesson(view: View){
        var goDayLesson = Intent(this, DayLesson::class.java).apply {
            intent.putExtra("kolvoToDo",kolvo)
        }
        startActivity(goDayLesson)

    }
    fun onClickStartLesson(view: View){
        var goDayLesson = Intent(this, DayLesson::class.java)
        startActivity(goDayLesson)
    }
}