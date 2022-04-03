package com.flirex.verbum.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Lesson : Activity() {
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    val definitionTipe = mutableListOf("profession","theatre")
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
    private var score: String? = "0"
    private var scoreCheck: Int = 0
    var randomDefinition: Int = 1
    var editTextTextDefinition: EditText? = null
    var bnCheckAnswerDayLesson: Button? = null
    var bnNextDefinitionDayLesson: Button? = null
    var tvDefinitionTextRemember: TextView? = null
    var randomDefinitionCheck: Int = 1
    var currentDefinition: String = ""
    var lessonRememberCheck: Int = 0
    var wordToLearnLow: String? = ""
    var wordToLearnMiddle: String? = ""
    var wordToLearnHigh: String? = ""
    var wordToDeleteLow: String = ""
    var wordToDeleteMiddle: String = ""
    var wordToDeleteHigh: String =""
    lateinit var wordToLearnLowList: List<String>
    lateinit var wordToLearnMiddleList: List<String>
    lateinit var wordToLearnHighList: List<String>
    lateinit var wordToDeleteList: List<String>
    var wordToLearn: String? = ""
    var wordToLearnScore: String? = ""
    var wordToLearnCheck: String? = ""
    var learnedWordsCheck: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
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
    }
    fun onClickStartLesson(view:View){
        nextWord()
    }
    fun onClickNextWordLesson(view: View){

    }
    fun onClickCheckAnswer(view: View){

    }
    fun onClickInowTest(view: View){

    }
    fun onClickIwantToLearn(view: View){

    }
    fun nextWord(){
        /*db.collection(definitiontipe.toString()).document(definitionlevel.toString())
            .get()
            .addOnSuccessListener { document ->
                Log.d("test", "e $document")
                if (document != null) {
                    name = definitionList[definitionListCheck]
                    definition = document.getString(name.toString())
                    Log.d("test", "safasdf $definition")
                }
            }*/
    }
}