package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.flirex.verbum.R
import com.flirex.verbum.modules.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DayLesson : Activity() {
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
    var wordToLearnProfessionLow: String? = ""
    var wordToLearnProfessionMiddle: String? = ""
    var wordToLearnProfessionHigh: String? = ""
    var wordToDeleteProfessionLow: String = ""
    var wordToDeleteProfessionMiddle: String = ""
    var wordToDeleteProfessionHigh: String =""
    lateinit var wordToLearnProfessionLowList: List<String>
    lateinit var wordToLearnProfessionMiddleList: List<String>
    lateinit var wordToLearnProfessionHighList: List<String>
    lateinit var wordToDeleteList: List<String>
    var wordToLearn: String? = ""
    var wordToLearnScore: String? = ""
    var wordToLearnCheck: String? = ""
    var learnedWordsCheck: String? = ""
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
        start()
    }



    fun start(){
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        db.collection("users").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                Log.d("test", "$document")
                level = document.getString("level")
                score = document.getString("score")
                wordToLearnProfessionLow = document.getString("wordToLearnProfessionLow")
                wordToLearnProfessionMiddle = document.getString("wordToLearnProfessionMiddle")
                wordToLearnProfessionHigh = document.getString("wordToLearnProfessionHigh")
                wordToLearnProfessionLowList = wordToLearnProfessionLow!!.split(" ")
                wordToLearnProfessionMiddleList = wordToLearnProfessionMiddle!!.split(" ")
                wordToLearnProfessionHighList = wordToLearnProfessionHigh!!.split(" ")
                Log.d("test", "bebra $wordToLearnProfessionLowList")
                wordToLearn = document.getString("wordToLearn")
                if (level == "1") {
                    levelCheck = 1
                }
                if (level == "2") {
                    levelCheck = 2
                }
                if (level == "3") {
                    levelCheck = 3
                }
                if (score == "4"){
                    scoreCheck = 4
                }
                if (score == "6"){
                    scoreCheck = 6
                }
                if (score == "8"){
                    scoreCheck = 8
                }
            }
        addWordToLesson()
    }
    fun onClickInowTest(view: View) {
        if (levelCheck == 1) {
            val rndName = (0..wordToLearnProfessionLowList.size).random()
            name = wordToLearnProfessionLowList[rndName]
            val rndTipe = (0..0).random()
            tipe = definitionTipe[rndTipe]
            addWordToLesson()
        }
        if (levelCheck == 2) {
            val rndName = (0..wordToLearnProfessionMiddleList.size).random()
            name = wordToLearnProfessionMiddleList[rndName]
            val rndTipe = (0..0).random()
            tipe = definitionTipe[rndTipe]
            addWordToLesson()
        }
        if (levelCheck == 3) {
            val rndName = (0..wordToLearnProfessionHighList.size).random()
            name = wordToLearnProfessionHighList[rndName]
            val rndTipe = (0..0).random()
            tipe = definitionTipe[rndTipe]
            addWordToLesson()
        }
    }

    fun onClickIwantToLearn(view: View) {
        if (kolvoWordsCheck == (scoreCheck/2+1)) {
            definitionTipeList.add(tipe)
            definitionList.add(name)
            startLesson()
            kolvoWordsCheck = 0
            /*var goLesson = Intent(this, Lesson::class.java)
            startActivity(goLesson)*/
        }else {
            if (levelCheck == 1) {
                wordToDeleteProfessionLow = wordToDeleteProfessionLow + name + " "
                definitionList.add(name)
                val rndName = (0..wordToLearnProfessionLowList.size).random()
                name = wordToLearnProfessionLowList[rndName]
                definitionTipeList.add(tipe)
                val rndTipe = (0..0).random()
                tipe = definitionTipe[rndTipe]
                addWordToLesson()
                kolvoWordsCheck += 1
            }
            if (levelCheck == 2) {
                wordToDeleteProfessionMiddle = wordToDeleteProfessionMiddle + name + " "
                definitionList.add(name)
                val rndName = (0..wordToLearnProfessionMiddleList.size).random()
                name = wordToLearnProfessionMiddleList[rndName]
                definitionTipeList.add(tipe)
                val rndTipe = (0..0).random()
                tipe = definitionTipe[rndTipe]
                addWordToLesson()
                kolvoWordsCheck += 1
            }
            if (levelCheck == 3) {
                wordToDeleteProfessionHigh = wordToDeleteProfessionHigh + name + " "
                definitionList.add(name)
                val rndName = (0..wordToLearnProfessionHighList.size).random()
                name = wordToLearnProfessionHighList[rndName]
                definitionTipeList.add(tipe)
                val rndTipe = (0..0).random()
                tipe = definitionTipe[rndTipe]
                addWordToLesson()
                kolvoWordsCheck += 1
            }
        }
    }
    fun addWordToLesson() {
        //Log.d("test", "bebra2 $wordToLearnProfessionLowList")
        if (kolvoWordsCheck == 0){
            tvDefinitionNameTest?.setText("")
            tvDefinitionTextTest?.setText("Слова, которые Вы знаете, будут добавленные в словарь")
            bnIwantToLearn?.setText("Продолжить")
            bnInowTest?.setVisibility(View.GONE)
            kolvoWordsCheck += 1
        }else {
            bnIwantToLearn?.setText("Хочу выучить")
            bnInowTest?.setVisibility(View.VISIBLE)
            if (levelCheck == 1) {
                //Log.d("test","name = $name")
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
            if (levelCheck == 2) {
                //Log.d("test","name = $name")
                db.collection(tipe).document("middle")
                    .get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            definition = document.getString(name)
                            tvDefinitionNameTest?.setText(name)
                            tvDefinitionTextTest?.setText(definition)
                        }
                    }
            }
            if (levelCheck == 3) {
                //Log.d("test","name = $name")
                db.collection(tipe).document("high")
                    .get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            definition = document.getString(name)
                            tvDefinitionNameTest?.setText(name)
                            tvDefinitionTextTest?.setText(definition)
                        }
                    }
            }
        }
    }
    fun startLesson() {
        if (definitionList[0] == ""){
            definitionList.removeAt(0)
        }
        if(levelCheck == 1) {
            wordToDeleteProfessionLow = wordToDeleteProfessionLow + name + " "
            Log.d("test", "wordToDeleteProfessionLow $wordToDeleteProfessionLow")

        }
        if(levelCheck == 2) {
            wordToDeleteProfessionMiddle = wordToDeleteProfessionMiddle + name + " "
            Log.d("test", "wordToDeleteProfessionMiddle $wordToDeleteProfessionMiddle")
        }
        if(levelCheck == 3) {
            wordToDeleteProfessionHigh = wordToDeleteProfessionHigh + name + " "
            Log.d("test", "wordToDeleteProfessionHigh $wordToDeleteProfessionHigh")
        }
        definitionTipeList.removeAt(0)
        definitionTipeList.removeAt(0)
        definitionList.removeAt(0)
        Log.d("test", "definitionList $definitionList")
        //Log.d("test", "definitionName $definitionName")
        Log.d("test", "definitionTipe $definitionTipeList")
        bnStartDayLesson?.setVisibility(View.VISIBLE)
        tvStartDayLesson?.setVisibility(View.VISIBLE)
        tvDefinitionNameTest?.setVisibility(View.GONE)
        tvDefinitionTextTest?.setVisibility(View.GONE)
        bnInowTest?.setVisibility(View.GONE)
        bnIwantToLearn?.setVisibility(View.GONE)
    }
    fun onClickStartDayLesson(view: View) {
        tvDefinitionTextTest?.setVisibility(View.VISIBLE)
        tvDefinitionNameTest?.setVisibility(View.VISIBLE)
        tvStartDayLesson?.setVisibility(View.GONE)
        bnStartDayLesson?.setVisibility(View.GONE)
        bnNextDefinitionDayLesson?.setVisibility(View.VISIBLE)
        tvDefinitionNameTest?.setText(definitionList[randomDefinition])
        if(levelCheck == 1) {
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
        }
        if(levelCheck == 2) {
            db.collection(tipe).document("middle")
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
        }
        if(levelCheck == 3) {
            db.collection(tipe).document("high")
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
        }
        kolvoWordsCheck += 1
    }
    fun onClickNextWordDayLesson(view: View){
        if(kolvoWordsCheck == (scoreCheck/2)){
            startRememberPart()
        }
        if (randomDefinition == 1)randomDefinition = 0
        kolvoWordsCheck+=1
        tvDefinitionNameTest?.setText(definitionList[randomDefinition])
        if (levelCheck == 1) {
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
        if (levelCheck == 2) {
            db.collection(definitionTipeList[randomDefinition]).document("middle")
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
        if (levelCheck == 3) {
            db.collection(definitionTipeList[randomDefinition]).document("high")
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
    }
    fun startRememberPart(){
        editTextTextDefinition?.setVisibility(View.VISIBLE)
        bnNextDefinitionDayLesson?.setVisibility(View.GONE)
        tvDefinitionTextTest?.setVisibility(View.GONE)
        bnCheckAnswerDayLesson?.setVisibility(View.VISIBLE)
        tvDefinitionTextTest?.setVisibility(View.GONE)
        tvDefinitionTextRemember?.setVisibility(View.VISIBLE)
        tvDefinitionNameTest?.setVisibility(View.GONE)
        if (levelCheck == 1) {
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
        if (levelCheck == 2) {
            db.collection(definitionTipeList[randomDefinitionCheck]).document("middle")
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
        if (levelCheck == 3) {
            db.collection(definitionTipeList[randomDefinitionCheck]).document("high")
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
    }
    fun onClickCheckAnswer(view: View){
        if (lessonRememberCheck == scoreCheck/2-1) {
            if(levelCheck == 1) {
                wordToDeleteList = wordToDeleteProfessionLow!!.split(" ")
                Log.d("test", "wordToDeleteList$wordToDeleteList")
                wordToLearnProfessionLow = wordToLearnProfessionLow!!.replace(wordToDeleteList[1], "")
                wordToLearnProfessionLow = wordToLearnProfessionLow!!.replace(wordToDeleteList[2], "")
                wordToLearnProfessionLow = wordToLearnProfessionLow!!.replace(wordToDeleteList[3], "")
                wordToLearnProfessionLow = wordToLearnProfessionLow!!.replace("  ", " ")
                Log.d("test", "wordToLearnProfessionLow$wordToLearnProfessionLow")
                val currentUser = auth.currentUser
                db.collection("users").document(currentUser!!.uid)
                    .get()
                    .addOnSuccessListener { document ->
                        Log.d("test", "$document")
                        level = document.getString("level")
                        wordToLearnScore = document.getString("score")
                        wordToLearn = document.getString("wordToLearn")
                        learnedWordsCheck = document.getString("learnedWords")
                        wordToLearnProfessionLow = wordToLearnProfessionLow!!.trim()
                        learnedWordsCheck = learnedWordsCheck + wordToDeleteProfessionLow
                        if (wordToLearn == "3") {
                            wordToLearnCheck = "0"
                        }
                        if (wordToLearn == "6") {
                            wordToLearnCheck = "3"
                        }
                        if (wordToLearn == "2") {
                            wordToLearnCheck = "0"
                        }
                        if (wordToLearn == "4" && wordToLearnScore == "4") {
                            wordToLearnCheck = "2"
                        }
                        if (wordToLearn == "4" && wordToLearnScore == "8") {
                            wordToLearnCheck = "0"
                        }
                        if (wordToLearn == "8") {
                            wordToLearnCheck = "4"
                        }
                        learnedWordsCheck = learnedWordsCheck!!.trim()
                        val userDoc = User(
                            uid = currentUser.uid,
                            email = currentUser.email,
                            name = currentUser.displayName,
                            score = wordToLearnScore.toString(),
                            level = level.toString(),
                            wordToLearnProfessionLow = wordToLearnProfessionLow.toString(),
                            wordToLearnProfessionMiddle = wordToLearnProfessionMiddle.toString(),
                            wordToLearnProfessionHigh = wordToLearnProfessionHigh.toString(),
                            learnedWords = learnedWordsCheck.toString(),
                            wordToLearn = wordToLearnCheck.toString()
                        )
                        val washingtonRef = db.collection("users").document(currentUser!!.uid)
                        washingtonRef
                            .set(userDoc)
                    }
                var goTasks = Intent(this, Tasks::class.java)
                startActivity(goTasks)
            }
            if(levelCheck == 2) {
                wordToDeleteList = wordToDeleteProfessionMiddle!!.split(" ")
                Log.d("test", "wordToDeleteList$wordToDeleteList")
                wordToLearnProfessionMiddle =
                    wordToLearnProfessionMiddle!!.replace(wordToDeleteList[1], "")
                wordToLearnProfessionMiddle =
                    wordToLearnProfessionMiddle!!.replace(wordToDeleteList[2], "")
                wordToLearnProfessionMiddle =
                    wordToLearnProfessionMiddle!!.replace(wordToDeleteList[3], "")
                wordToLearnProfessionMiddle = wordToLearnProfessionMiddle!!.replace("  ", " ")
                Log.d("test", "wordToLearnProfessionMiddle$wordToLearnProfessionMiddle")
                val currentUser = auth.currentUser
                db.collection("users").document(currentUser!!.uid)
                    .get()
                    .addOnSuccessListener { document ->
                        Log.d("test", "$document")
                        level = document.getString("level")
                        wordToLearnScore = document.getString("score")
                        wordToLearn = document.getString("wordToLearn")
                        learnedWordsCheck = document.getString("learnedWords")
                        wordToLearnProfessionMiddle = wordToLearnProfessionMiddle!!.trim()
                        learnedWordsCheck = learnedWordsCheck + wordToDeleteProfessionMiddle
                        if (wordToLearn == "3") {
                            wordToLearnCheck = "0"
                        }
                        if (wordToLearn == "6") {
                            wordToLearnCheck = "3"
                        }
                        if (wordToLearn == "2") {
                            wordToLearnCheck = "0"
                        }
                        if (wordToLearn == "4" && wordToLearnScore == "4") {
                            wordToLearnCheck = "2"
                        }
                        if (wordToLearn == "4" && wordToLearnScore == "8") {
                            wordToLearnCheck = "0"
                        }
                        if (wordToLearn == "8") {
                            wordToLearnCheck = "4"
                        }
                        learnedWordsCheck = learnedWordsCheck!!.trim()
                        val userDoc = User(
                            uid = currentUser.uid,
                            email = currentUser.email,
                            name = currentUser.displayName,
                            score = wordToLearnScore.toString(),
                            level = level.toString(),
                            wordToLearnProfessionLow = wordToLearnProfessionLow.toString(),
                            wordToLearnProfessionMiddle = wordToLearnProfessionMiddle.toString(),
                            wordToLearnProfessionHigh = wordToLearnProfessionHigh.toString(),
                            learnedWords = learnedWordsCheck.toString(),
                            wordToLearn = wordToLearnCheck.toString()
                        )
                        val washingtonRef = db.collection("users").document(currentUser!!.uid)
                        washingtonRef
                            .set(userDoc)
                    }
                var goTasks = Intent(this, Tasks::class.java)
                startActivity(goTasks)
            }
            if(levelCheck == 3) {
                wordToDeleteList = wordToDeleteProfessionHigh!!.split(" ")
                Log.d("test", "wordToDeleteList$wordToDeleteList")
                wordToLearnProfessionHigh =
                    wordToLearnProfessionHigh!!.replace(wordToDeleteList[1], "")
                wordToLearnProfessionHigh =
                    wordToLearnProfessionHigh!!.replace(wordToDeleteList[2], "")
                wordToLearnProfessionHigh =
                    wordToLearnProfessionHigh!!.replace(wordToDeleteList[3], "")
                wordToLearnProfessionHigh = wordToLearnProfessionHigh!!.replace("  ", " ")
                Log.d("test", "wordToLearnProfessionHigh$wordToLearnProfessionHigh")
                val currentUser = auth.currentUser
                db.collection("users").document(currentUser!!.uid)
                    .get()
                    .addOnSuccessListener { document ->
                        Log.d("test", "$document")
                        level = document.getString("level")
                        wordToLearnScore = document.getString("score")
                        wordToLearn = document.getString("wordToLearn")
                        learnedWordsCheck = document.getString("learnedWords")
                        wordToLearnProfessionHigh = wordToLearnProfessionHigh!!.trim()
                        learnedWordsCheck = learnedWordsCheck + wordToDeleteProfessionHigh
                        if (wordToLearn == "3") {
                            wordToLearnCheck = "0"
                        }
                        if (wordToLearn == "6") {
                            wordToLearnCheck = "3"
                        }
                        if (wordToLearn == "2") {
                            wordToLearnCheck = "0"
                        }
                        if (wordToLearn == "4" && wordToLearnScore == "4") {
                            wordToLearnCheck = "2"
                        }
                        if (wordToLearn == "4" && wordToLearnScore == "8") {
                            wordToLearnCheck = "0"
                        }
                        if (wordToLearn == "8") {
                            wordToLearnCheck = "4"
                        }
                        learnedWordsCheck = learnedWordsCheck!!.trim()
                        val userDoc = User(
                            uid = currentUser.uid,
                            email = currentUser.email,
                            name = currentUser.displayName,
                            score = wordToLearnScore.toString(),
                            level = level.toString(),
                            wordToLearnProfessionLow = wordToLearnProfessionLow.toString(),
                            wordToLearnProfessionMiddle = wordToLearnProfessionMiddle.toString(),
                            wordToLearnProfessionHigh = wordToLearnProfessionHigh.toString(),
                            learnedWords = learnedWordsCheck.toString(),
                            wordToLearn = wordToLearnCheck.toString()
                        )
                        val washingtonRef = db.collection("users").document(currentUser!!.uid)
                        washingtonRef
                            .set(userDoc)
                    }
                var goTasks = Intent(this, Tasks::class.java)
                startActivity(goTasks)
            }
        }else {
            var answer: String = editTextTextDefinition?.getText().toString()
            Log.d("test", "$answer")
            Log.d("test", currentDefinition)
            if (randomDefinitionCheck == 1) randomDefinitionCheck = 0
            if (answer == currentDefinition) {
                editTextTextDefinition?.setText("")
                lessonRememberCheck += 1
                if (levelCheck == 1) {
                    db.collection(definitionTipeList[randomDefinitionCheck]).document("low")
                        .get()
                        .addOnSuccessListener { document ->
                            Log.d("test", "e $document")
                            if (document != null) {
                                definition =
                                    document.getString(definitionList[randomDefinitionCheck])
                                Log.d("test", "safasdf $definition")
                                tvDefinitionTextRemember?.setText(definition)
                                currentDefinition = definitionList[randomDefinitionCheck]
                                randomDefinitionCheck = 2
                            }
                        }
                }
                if (levelCheck == 2) {
                    db.collection(definitionTipeList[randomDefinitionCheck]).document("middle")
                        .get()
                        .addOnSuccessListener { document ->
                            Log.d("test", "e $document")
                            if (document != null) {
                                definition =
                                    document.getString(definitionList[randomDefinitionCheck])
                                Log.d("test", "safasdf $definition")
                                tvDefinitionTextRemember?.setText(definition)
                                currentDefinition = definitionList[randomDefinitionCheck]
                                randomDefinitionCheck = 2
                            }
                        }
                }
                if (levelCheck == 3) {
                    db.collection(definitionTipeList[randomDefinitionCheck]).document("high")
                        .get()
                        .addOnSuccessListener { document ->
                            Log.d("test", "e $document")
                            if (document != null) {
                                definition =
                                    document.getString(definitionList[randomDefinitionCheck])
                                Log.d("test", "safasdf $definition")
                                tvDefinitionTextRemember?.setText(definition)
                                currentDefinition = definitionList[randomDefinitionCheck]
                                randomDefinitionCheck = 2
                            }
                        }
                }
            }else{
                Toast.makeText(this, "Слово введено неверно", Toast.LENGTH_SHORT).show()
            }
        }
    }
}