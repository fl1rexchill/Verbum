package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Progress : Activity() {
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    lateinit var definitionsList: List<String>
    var learnedWords:String? = ""
    var definitions:String? = ""
    var tvDefinitionsListProgress: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
    }

    override fun onStart() {
        super.onStart()
        tvDefinitionsListProgress = findViewById(R.id.tvDefinitionsListProgress)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        definitions = ""
        db.collection("users").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                Log.d("test", "$document")
                learnedWords = document.getString("learnedWords")
                definitionsList = learnedWords!!.split(" ")
                createDefinitionList()
            }
    }
    fun createDefinitionList(){
        for (i in definitionsList){
            definitions = definitions + i + "\n"
        }
        if(definitions == ""){
            tvDefinitionsListProgress?.setText("Вы ещё не выучили ни одного слова")
        }else{
            tvDefinitionsListProgress?.setText(definitions)
        }

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