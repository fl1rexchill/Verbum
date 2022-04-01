package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DayLesson : Activity() {
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    val definitionTipe = mutableListOf("profession","theatre")
    var definitionName = mutableListOf("Агроном","Аналитик","Архивариус","Архитектор","Астроном","Бухгалтер")
    var listSizeDefinition:Int = 5
    var tipe:String = ""
    var name:String = ""
    var definitionList = mutableListOf("")
    var definitionTipeList = mutableListOf("")
    var definition:String? = ""
    var kolvoWordsCheck:Int = 0
    var tvDefinitionNameTest: TextView? = null
    var tvDefinitionTextTest: TextView? = null
    var bnStartDayLesson: TextView? = null
    var tvStartDayLesson: TextView? = null
    var bnInowTest: Button? = null
    var bnIwantToLearn: Button? = null
    private var level: String? = "0"
    private var levelCheck: Int = 0
    var randomDefinition: Int = 1
    var editTextTextDefinition: EditText? = null
    var bnCheckAnswerDayLesson: Button? = null
    var bnNextDefinitionDayLesson: Button? = null
    var tvDefinitionTextRemember: TextView? = null
    var randomDefinitionCheck: Int = 1
    var currentDefinition: String = ""
    var lessonRememberCheck: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_lesson)
        tvDefinitionNameTest = findViewById(R.id.tvDefinitionNameTest)
        tvDefinitionTextTest = findViewById(R.id.tvDefinitionTextTest)
        bnInowTest = findViewById(R.id.bnInowTest)
        bnIwantToLearn = findViewById(R.id.bnIwantToLearn)
        bnStartDayLesson = findViewById(R.id.bnStartDayLesson)
        bnStartDayLesson?.setVisibility(View.GONE)
        tvStartDayLesson = findViewById(R.id.tvStartDayLesson)
        tvStartDayLesson?.setVisibility(View.GONE)
        bnCheckAnswerDayLesson= findViewById(R.id.bnCheckAnswerDayLesson)
        bnCheckAnswerDayLesson?.setVisibility(View.GONE)
        editTextTextDefinition = findViewById(R.id.editTextTextDefinition)
        editTextTextDefinition?.setVisibility(View.GONE)
        bnNextDefinitionDayLesson = findViewById(R.id.bnNextDefinitionDayLesson)
        bnNextDefinitionDayLesson?.setVisibility(View.GONE)
        tvDefinitionTextRemember = findViewById(R.id.tvDefinitionTextRemember)
        tvDefinitionTextRemember?.setVisibility(View.GONE)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        db.collection("users").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("test", "$document")
                    try {
                        level = document.getString("level")
                        Log.d("test", "$level")
                        if (level == "1") {
                            levelCheck = 1
                        }
                        if (level == "2") {
                            levelCheck = 2
                        }
                        if (level == "3") {
                            levelCheck = 3
                        }
                    } catch (e: Exception) {
                        Log.d("test", "$e")
                    }
                }
            }
        addWordToLesson()
    }

    fun onClickInowTest(view: View) {
        addWordToLesson()
    }


    fun onClickIwantToLearn(view: View) {
        kolvoWordsCheck += 1
        if (kolvoWordsCheck == 3) {
            startLesson()
            kolvoWordsCheck = 0
            /*var goLesson = Intent(this, Lesson::class.java)
            startActivity(goLesson)*/
        }else {
            addWordToLesson()
        }
    }

    fun addWordToLesson() {
        val rndTipe = (0..0).random()
        val rndName = (0..listSizeDefinition).random()
        tipe = definitionTipe[0]
        definitionTipeList.add(tipe)
        name = definitionName[rndName]
        listSizeDefinition += (-1)
        definitionName.remove(name)
        definitionList.add(name)
        db.collection(tipe).document("low")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    definition = document.getString(name)
                    tvDefinitionNameTest?.setText(name)
                    tvDefinitionTextTest?.setText(definition)
                }
            }
    }
    fun startLesson() {
        definitionTipeList.removeAt(0)
        definitionList.removeAt(0)
        Log.d("test", "definitionList $definitionList")
        Log.d("test", "definitionName $definitionName")
        Log.d("test", "definitionTipe $definitionTipeList")
        bnStartDayLesson?.setVisibility(View.VISIBLE)
        tvStartDayLesson?.setVisibility(View.VISIBLE)
        tvDefinitionNameTest?.setVisibility(View.GONE)
        tvDefinitionTextTest?.setVisibility(View.GONE)
        bnInowTest?.setVisibility(View.GONE)
        bnIwantToLearn?.setVisibility(View.GONE)
    }
    fun onClickStartDayLesson(view: View){
        try {
            tvDefinitionTextTest?.setVisibility(View.VISIBLE)
            tvDefinitionNameTest?.setVisibility(View.VISIBLE)
            tvStartDayLesson?.setVisibility(View.GONE)
            bnStartDayLesson?.setVisibility(View.GONE)
            bnNextDefinitionDayLesson?.setVisibility(View.VISIBLE)
            tvDefinitionNameTest?.setText(definitionList[randomDefinition])
            db.collection(tipe).document("low")
                .get()
                .addOnSuccessListener { document ->
                    Log.d("test", "e $document")
                    if (document != null) {
                        definition = document.getString(definitionList[randomDefinition])
                        Log.d("test", "safasdf $definition")
                        tvDefinitionTextTest?.setText(definition)
                        tvDefinitionNameTest?.setText(definitionList[randomDefinition])
                    }
                }
            kolvoWordsCheck += 1
        }catch(k: Exception) {
            Log.d("test", "$k")
        }
    }
    fun onClickNextWordDayLesson(view: View){
        if(kolvoWordsCheck == 3){
            startRememberPart()
        }
        if (randomDefinition == 1)randomDefinition = 0
        kolvoWordsCheck+=1
        tvDefinitionNameTest?.setText(definitionList[randomDefinition])
        db.collection(definitionTipeList[randomDefinition]).document("low")
            .get()
            .addOnSuccessListener { document ->
                Log.d("test", "e $document")
                if (document != null) {
                    definition = document.getString(definitionList[randomDefinition])
                    Log.d("test", "safasdf $definition")
                    tvDefinitionTextTest?.setText(definition)
                    randomDefinition = 2
                }
            }

    }
    fun startRememberPart(){
        editTextTextDefinition?.setVisibility(View.VISIBLE)
        bnNextDefinitionDayLesson?.setVisibility(View.GONE)
        tvDefinitionTextTest?.setVisibility(View.GONE)
        bnCheckAnswerDayLesson?.setVisibility(View.VISIBLE)
        tvDefinitionTextTest?.setVisibility(View.GONE)
        tvDefinitionTextRemember?.setVisibility(View.VISIBLE)
        tvDefinitionNameTest?.setVisibility(View.GONE)
        db.collection(definitionTipeList[randomDefinitionCheck]).document("low")
            .get()
            .addOnSuccessListener { document ->
                Log.d("test", "e $document")
                if (document != null) {
                    definition = document.getString(definitionList[randomDefinitionCheck])
                    Log.d("test", "safasdf $definition")
                    tvDefinitionTextRemember?.setText(definition)
                    currentDefinition = definitionList[randomDefinitionCheck]
                }
            }
    }
    fun onClickCheckAnswer(view: View){
        if (lessonRememberCheck == 2){
            var goTasks = Intent(this, Tasks::class.java)
            startActivity(goTasks)
        }else {
            var answer: String = editTextTextDefinition?.getText().toString()
            Log.d("test", "$answer")
            Log.d("test", currentDefinition)
            if (randomDefinitionCheck == 1) randomDefinitionCheck = 0
            if (answer == currentDefinition) {
                editTextTextDefinition?.setText("")
                lessonRememberCheck += 1
                db.collection(definitionTipeList[randomDefinitionCheck]).document("low")
                    .get()
                    .addOnSuccessListener { document ->
                        Log.d("test", "e $document")
                        if (document != null) {
                            definition = document.getString(definitionList[randomDefinitionCheck])
                            Log.d("test", "safasdf $definition")
                            tvDefinitionTextRemember?.setText(definition)
                            currentDefinition = definitionList[randomDefinitionCheck]
                            randomDefinitionCheck = 2
                        }
                    }
            }
        }
    }

}