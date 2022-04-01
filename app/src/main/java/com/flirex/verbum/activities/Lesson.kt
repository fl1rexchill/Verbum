package com.flirex.verbum.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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
    var bnStartLesson: Button?=null
    var tvTextStartLesson: TextView?=null
    var tvDefinitionNameLesson: TextView?=null
    var definitionListCheck = 0
    var definition:String? = ""
    var name:String?=""
    var definition1:String? = ""
    var definition2:String? = ""
    var definition3:String? = ""
    var definitiontipe:String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        bnStartLesson = findViewById(R.id.bnStartLesson)
        tvTextStartLesson = findViewById(R.id.tvTextStartLesson)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = Firebase.firestore
        db.collection("active").document(currentUser!!.uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("test", "$document")
                    try {
                        definition1 = document.getString("definition1")
                        definition2 = document.getString("definition2")
                        definition3 = document.getString("definition3")
                        definitiontipe = document.getString("definitiontipe")
                        Log.d("test","$definition1")
                        Log.d("test","$definition2")
                        Log.d("test","$definition3")
                        Log.d("test","$definitiontipe")
                    } catch (e: Exception) {
                        Log.d("test", "$e")
                    }
                }
            }
    }
    fun onClickStart(view:View){
        bnStartLesson?.setVisibility(View.GONE)
        tvTextStartLesson?.setVisibility(View.GONE)
        nextWord()
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