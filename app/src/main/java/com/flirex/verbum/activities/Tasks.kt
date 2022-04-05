package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.flirex.verbum.R
import com.flirex.verbum.modules.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Tasks : Activity() {
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    var tvNewWords: TextView? = null
    var kolvo:String? = ""
    var scoreCheck:String? = ""
    var priziv:String = ""
    var imStartDayLesson:ImageView? = null
    var level:String? = ""
    var wordToLearn:String? = ""
    var learnedWordsCheck:String? = ""
    var scoreCheckWork:String? = ""
    var wordToLearnProfessionLow:String? = ""
    var wordToLearnProfessionMiddle:String? = ""
    var wordToLearnProfessionHigh:String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        tvNewWords = findViewById(R.id.tvNewWords)
        imStartDayLesson = findViewById(R.id.imStartDayLesson)
        try {
            auth = Firebase.auth
            val currentUser = auth.currentUser
            db.collection("users").document(currentUser!!.uid)
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "$document")
                    kolvo = document.getString("wordToLearn")
                    scoreCheck = document.getString("score")
                    Log.d("test", "bebra $kolvo")
                    if (kolvo == "6") {
                        priziv = "Выучить 3 новых слов"
                    }
                    if (kolvo == "3") {
                        priziv = "Выучить 3 новых слова"
                    }
                    if (kolvo == "0") {
                        priziv = "Вы выполнили дневной план"
                        imStartDayLesson!!.setClickable(false)
                        tvNewWords!!.setClickable(false)
                    }
                    if (kolvo == "2") {
                        priziv = "Выучить 2 новых слова"
                    }
                    if (kolvo == "4" && scoreCheck == "4") {
                        priziv = "Выучить 2 новых слова"
                    }
                    if (kolvo == "8") {
                        priziv = "Выучить 4 новых слова"
                    }
                    if (kolvo == "4" && scoreCheck == "8"){
                        priziv = "Выучить 4 новых слова"
                    }
                    tvNewWords?.setText(priziv)
                }
        }catch (o:Exception){
            Log.d("test","o$o")
        }


    }
    fun onClickGoProgress(view: View){
        val goProgress = Intent(this, Progress::class.java)
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
}