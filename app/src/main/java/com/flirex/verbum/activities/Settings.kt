package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Settings : Activity() {
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    private var level: String? = "0"
    private var levelCheck: Int = 0
    private var score: String? = "0"
    private var scoreCheck: Int = 0
    private var bnLow: Button? = null
    private var bnMiddle: Button? = null
    private var bnHigh: Button? = null
    private var bnLow2: Button? = null
    private var bnMiddle2: Button? = null
    private var bnHigh2: Button? = null
    private var bnFour: Button? = null
    private var bnSix: Button? = null
    private var bnEigth: Button? = null
    private var bnFour2: Button? = null
    private var bnSix2: Button? = null
    private var bnEigth2: Button? = null
    var scoreCheckWork: String? = ""
    var wordToLearnProfessionLow: String? = ""
    var wordToLearnProfessionMiddle: String? = ""
    var wordToLearnProfessionHigh: String? = ""
    var learnedWords: String? = ""
    var wordToLearn:String? = ""
    var levelCheckWork:String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        bnLow = findViewById(R.id.bnLow)
        bnMiddle = findViewById(R.id.bnMiddle)
        bnHigh = findViewById(R.id.bnHigh)
        bnLow2 = findViewById(R.id.bnLow2)
        bnMiddle2 = findViewById(R.id.bnMiddle2)
        bnHigh2 = findViewById(R.id.bnHigh2)
        bnFour = findViewById(R.id.bnFour)
        bnSix = findViewById(R.id.bnSix)
        bnEigth = findViewById(R.id.bnEight)
        bnFour2 = findViewById(R.id.bnFour2)
        bnSix2 = findViewById(R.id.bnSix2)
        bnEigth2 = findViewById(R.id.bnEight2)
    }
    override fun onStart() {
        super.onStart()
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        bnLow2?.setVisibility(View.GONE)
        bnMiddle2?.setVisibility(View.GONE)
        bnHigh2?.setVisibility(View.GONE)
        bnFour2?.setVisibility(View.GONE)
        bnSix2?.setVisibility(View.GONE)
        bnEigth2?.setVisibility(View.GONE)
        db.collection("users").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("test", "$document")
                    try {
                        level = document.getString("level")
                        Log.d("test", "$level")
                        score = document.getString("score")
                        Log.d("test", "$score")
                        if (level == "1"){
                            bnLow2?.setVisibility(View.VISIBLE)
                            bnLow?.setVisibility(View.GONE)
                            levelCheck = 1
                        }
                        if (level == "2") {
                            bnMiddle2?.setVisibility(View.VISIBLE)
                            bnMiddle?.setVisibility(View.GONE)
                            levelCheck = 2
                        }
                        if (level == "3"){
                            bnHigh2?.setVisibility(View.VISIBLE)
                            bnHigh?.setVisibility(View.GONE)
                            levelCheck = 3
                        }
                        if (score == "4"){
                            bnFour2?.setVisibility(View.VISIBLE)
                            bnFour?.setVisibility(View.GONE)
                            scoreCheck = 4
                        }
                        if (score == "6") {
                            bnSix2?.setVisibility(View.VISIBLE)
                            bnSix?.setVisibility(View.GONE)
                            scoreCheck = 6
                        }
                        if (score == "8"){
                            bnEigth2?.setVisibility(View.VISIBLE)
                            bnEigth?.setVisibility(View.GONE)
                            scoreCheck = 8
                        }
                    } catch (e: Exception) {
                        Log.d("test", "$e")
                    }
                }
            }

    }
    fun onClickGoProgress(view: View){
        var goProgress = Intent(this, Progress::class.java)
        startActivity(goProgress)
    }
    fun onClickGoTasks(view: View){
        var goTasks = Intent(this, Tasks::class.java)
        startActivity(goTasks)
    }
    fun onClickLow(view: View){
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        db.collection("users").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                Log.d("test", "$document")
                scoreCheckWork = document.getString("score")
                wordToLearnProfessionLow = document.getString("wordToLearnProfessionLow")
                wordToLearnProfessionMiddle = document.getString("wordToLearnProfessionMiddle")
                wordToLearnProfessionHigh = document.getString("wordToLearnProfessionHigh")
                learnedWords = document.getString("learnedWords")
                wordToLearn = document.getString("wordToLearn")
                val userDoc = com.flirex.verbum.modules.User(
                    uid = currentUser?.uid,
                    email = currentUser?.email,
                    name = currentUser?.displayName,
                    score = scoreCheckWork.toString(),
                    level = "1",
                    wordToLearnProfessionLow = wordToLearnProfessionLow.toString(),
                    wordToLearnProfessionMiddle = wordToLearnProfessionMiddle.toString(),
                    wordToLearnProfessionHigh = wordToLearnProfessionHigh.toString(),
                    learnedWords = learnedWords.toString(),
                    wordToLearn = wordToLearn.toString(),
                    activeLesson = ""
                )
                val washingtonRef = db.collection("users").document(currentUser!!.uid)
                washingtonRef
                    .set(userDoc)
            }
        levelCheck = 1
        bnLow2?.setVisibility(View.VISIBLE)
        bnLow?.setVisibility(View.GONE)
        bnHigh?.setVisibility(View.VISIBLE)
        bnMiddle?.setVisibility(View.VISIBLE)
    }
    fun onClickMiddle(view: View){
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        db.collection("users").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                Log.d("test", "$document")
                scoreCheckWork = document.getString("score")
                wordToLearnProfessionLow = document.getString("wordToLearnProfessionLow")
                wordToLearnProfessionMiddle = document.getString("wordToLearnProfessionMiddle")
                wordToLearnProfessionHigh = document.getString("wordToLearnProfessionHigh")
                learnedWords = document.getString("learnedWords")
                wordToLearn = document.getString("wordToLearn")
                val userDoc = com.flirex.verbum.modules.User(
                    uid = currentUser?.uid,
                    email = currentUser?.email,
                    name = currentUser?.displayName,
                    score = scoreCheckWork.toString(),
                    level = "2",
                    wordToLearnProfessionLow = wordToLearnProfessionLow.toString(),
                    wordToLearnProfessionMiddle = wordToLearnProfessionMiddle.toString(),
                    wordToLearnProfessionHigh = wordToLearnProfessionHigh.toString(),
                    learnedWords = learnedWords.toString(),
                    wordToLearn = wordToLearn.toString(),
                    activeLesson = ""
                )
                val washingtonRef = db.collection("users").document(currentUser!!.uid)
                washingtonRef
                    .set(userDoc)
            }
        levelCheck = 2
        bnMiddle2?.setVisibility(View.VISIBLE)
        bnMiddle?.setVisibility(View.GONE)
        bnLow?.setVisibility(View.VISIBLE)
        bnHigh?.setVisibility(View.VISIBLE)
    }
    fun onClickHigh(view: View){
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        db.collection("users").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                Log.d("test", "$document")
                scoreCheckWork = document.getString("score")
                wordToLearnProfessionLow = document.getString("wordToLearnProfessionLow")
                wordToLearnProfessionMiddle = document.getString("wordToLearnProfessionMiddle")
                wordToLearnProfessionHigh = document.getString("wordToLearnProfessionHigh")
                learnedWords = document.getString("learnedWords")
                wordToLearn = document.getString("wordToLearn")
                val userDoc = com.flirex.verbum.modules.User(
                    uid = currentUser?.uid,
                    email = currentUser?.email,
                    name = currentUser?.displayName,
                    score = scoreCheckWork.toString(),
                    level = "3",
                    wordToLearnProfessionLow = wordToLearnProfessionLow.toString(),
                    wordToLearnProfessionMiddle = wordToLearnProfessionMiddle.toString(),
                    wordToLearnProfessionHigh = wordToLearnProfessionHigh.toString(),
                    learnedWords = learnedWords.toString(),
                    wordToLearn = wordToLearn.toString(),
                    activeLesson = ""
                )
                val washingtonRef = db.collection("users").document(currentUser!!.uid)
                washingtonRef
                    .set(userDoc)
            }
        levelCheck = 3
        bnHigh2?.setVisibility(View.VISIBLE)
        bnHigh?.setVisibility(View.GONE)
        bnLow?.setVisibility(View.VISIBLE)
        bnMiddle?.setVisibility(View.VISIBLE)
    }
    fun onClickbnFour(view: View){
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        db.collection("users").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                Log.d("test", "$document")
                levelCheckWork = document.getString("level")
                scoreCheckWork = document.getString("score")
                wordToLearnProfessionLow = document.getString("wordToLearnProfessionLow")
                wordToLearnProfessionMiddle = document.getString("wordToLearnProfessionMiddle")
                wordToLearnProfessionHigh = document.getString("wordToLearnProfessionHigh")
                learnedWords = document.getString("learnedWords")
                wordToLearn = document.getString("wordToLearn")
                if (wordToLearn == "4" && scoreCheckWork == "8") wordToLearn = "2"
                if (wordToLearn == "3" && scoreCheckWork == "6") wordToLearn = "2"
                if (wordToLearn == "8" && scoreCheckWork == "8") wordToLearn = "4"
                if (wordToLearn == "6" && scoreCheckWork == "6") wordToLearn = "4"
                val userDoc = com.flirex.verbum.modules.User(
                    uid = currentUser?.uid,
                    email = currentUser?.email,
                    name = currentUser?.displayName,
                    score = "4",
                    level = levelCheckWork.toString(),
                    wordToLearnProfessionLow = wordToLearnProfessionLow.toString(),
                    wordToLearnProfessionMiddle = wordToLearnProfessionMiddle.toString(),
                    wordToLearnProfessionHigh = wordToLearnProfessionHigh.toString(),
                    learnedWords = learnedWords.toString(),
                    wordToLearn = wordToLearn.toString(),
                    activeLesson = ""
                )
                val washingtonRef = db.collection("users").document(currentUser!!.uid)
                washingtonRef
                    .set(userDoc)
            }
        scoreCheck = 4
        bnFour2?.setVisibility(View.VISIBLE)
        bnFour?.setVisibility(View.GONE)
        bnSix?.setVisibility(View.VISIBLE)
        bnEigth?.setVisibility(View.VISIBLE)
    }
    fun onClickbnSix(view: View){
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        db.collection("users").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                Log.d("test", "$document")
                levelCheckWork = document.getString("level")
                scoreCheckWork = document.getString("score")
                wordToLearnProfessionLow = document.getString("wordToLearnProfessionLow")
                wordToLearnProfessionMiddle = document.getString("wordToLearnProfessionMiddle")
                wordToLearnProfessionHigh = document.getString("wordToLearnProfessionHigh")
                learnedWords = document.getString("learnedWords")
                wordToLearn = document.getString("wordToLearn")
                if (wordToLearn == "2" && scoreCheckWork == "4") wordToLearn = "3"
                if (wordToLearn == "4" && scoreCheckWork == "8") wordToLearn = "3"
                if (wordToLearn == "4" && scoreCheckWork == "4") wordToLearn = "6"
                if (wordToLearn == "8" && scoreCheckWork == "8") wordToLearn = "6"
                val userDoc = com.flirex.verbum.modules.User(
                    uid = currentUser?.uid,
                    email = currentUser?.email,
                    name = currentUser?.displayName,
                    score = "6",
                    level = levelCheckWork.toString(),
                    wordToLearnProfessionLow = wordToLearnProfessionLow.toString(),
                    wordToLearnProfessionMiddle = wordToLearnProfessionMiddle.toString(),
                    wordToLearnProfessionHigh = wordToLearnProfessionHigh.toString(),
                    learnedWords = learnedWords.toString(),
                    wordToLearn = wordToLearn.toString(),
                    activeLesson = ""
                )
                val washingtonRef = db.collection("users").document(currentUser!!.uid)
                washingtonRef
                    .set(userDoc)
            }
        scoreCheck = 6
        bnSix2?.setVisibility(View.VISIBLE)
        bnSix?.setVisibility(View.GONE)
        bnFour?.setVisibility(View.VISIBLE)
        bnEigth?.setVisibility(View.VISIBLE)
    }
    fun onClickbnEight(view: View){
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        db.collection("users").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                Log.d("test", "$document")
                levelCheckWork = document.getString("level")
                scoreCheckWork = document.getString("score")
                wordToLearnProfessionLow = document.getString("wordToLearnProfessionLow")
                wordToLearnProfessionMiddle = document.getString("wordToLearnProfessionMiddle")
                wordToLearnProfessionHigh = document.getString("wordToLearnProfessionHigh")
                learnedWords = document.getString("learnedWords")
                wordToLearn = document.getString("wordToLearn")
                if (wordToLearn == "2" && scoreCheckWork == "4") wordToLearn = "4"
                if (wordToLearn == "3" && scoreCheckWork == "6") wordToLearn = "4"
                if (wordToLearn == "4" && scoreCheckWork == "4") wordToLearn = "8"
                if (wordToLearn == "6" && scoreCheckWork == "6") wordToLearn = "8"
                val userDoc = com.flirex.verbum.modules.User(
                    uid = currentUser?.uid,
                    email = currentUser?.email,
                    name = currentUser?.displayName,
                    score = "8",
                    level = levelCheckWork.toString(),
                    wordToLearnProfessionLow = wordToLearnProfessionLow.toString(),
                    wordToLearnProfessionMiddle = wordToLearnProfessionMiddle.toString(),
                    wordToLearnProfessionHigh = wordToLearnProfessionHigh.toString(),
                    learnedWords = learnedWords.toString(),
                    wordToLearn = wordToLearn.toString(),
                    activeLesson = ""
                )
                val washingtonRef = db.collection("users").document(currentUser!!.uid)
                washingtonRef
                    .set(userDoc)
            }
        scoreCheck = 8
        bnEigth2?.setVisibility(View.VISIBLE)
        bnEigth?.setVisibility(View.GONE)
        bnSix?.setVisibility(View.VISIBLE)
        bnFour?.setVisibility(View.VISIBLE)
    }
    /*fun onClickSignOut(view: View){
        FirebaseAuth.getInstance().signOut()
        var goProgress = Intent(this, SignUpActivity::class.java)
        startActivity(goProgress)
    }
    */
}