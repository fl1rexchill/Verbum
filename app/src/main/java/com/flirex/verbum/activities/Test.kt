package com.flirex.verbum.activities

import android.app.Activity
import android.content.Intent

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.flirex.verbum.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Test : Activity() {
    private lateinit var auth: FirebaseAuth

    val db = Firebase.firestore
    var bnNextDefinition: Button? = null
    var tvStartTest: TextView? = null
    var tvDefinitionName: TextView? = null
    var tvSecondDefinitionsName: TextView? = null
    var bnStartTest: Button? = null
    var bnInow: Button? = null
    var bnIdnNow: Button? = null
    var bnAfterTestGoTasks: Button? = null
    var number: Int = 0
    var testResult: Int = 0
    val definitionsName = listOf("Мюли", "Оферта", "Аллегория", "Аннотация", "Пеладофобия", "Импонировать", "Онтология", "Вербальный", "Тьютор", "Миазм")
    val definition = listOf("Это туфли с открытой пяткой, которые держатся на ноге за счёт передней части",
        "Предложение заключить сделку",
        "Иносказание, переносное толкование прямого смысла",
        "Очень сжатое изложение научного или литературного произведения",
        "Боязнь облысения",
        "Производить положительное впечатление, внушать уважение, нравиться",
        "Индивидуальное развитие отдельно взятого живого существа от его зарождения до окончания жизни",
        "Словесный, устный",
        "Индивидуальный наставник при дистанционном или самостоятельном обучении",
        "Ядовитые испарения, вонь, смрад, запах гнили…"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        bnNextDefinition = findViewById(R.id.bnNextDefinition)
        bnNextDefinition?.setVisibility(View.GONE)
        tvDefinitionName = findViewById(R.id.tvDefinitionsName)
        tvDefinitionName?.setVisibility(View.GONE)
        bnAfterTestGoTasks = findViewById(R.id.bnAfterTestGoTasks)
        bnAfterTestGoTasks?.setVisibility(View.GONE)
        tvSecondDefinitionsName = findViewById(R.id.tvSecondDefinitionsName)
        tvSecondDefinitionsName?.setVisibility(View.GONE)
        bnInow = findViewById(R.id.bnInow)
        bnIdnNow = findViewById(R.id.bnIdnNow)
        bnInow?.setVisibility(View.GONE)
        bnIdnNow?.setVisibility(View.GONE)
    }
    fun onClickStartTest(view: View){
        tvStartTest = findViewById(R.id.tvStartTest)
        bnStartTest = findViewById(R.id.bnStartTest)
        bnStartTest?.setVisibility(View.GONE)
        tvStartTest?.setText("Сейчас Вам будут показаны 10 слов. Для того чтобы ответить необходимо нажать на одну из кнопок: " +
                "зелёную или красную, которые соответственно обозначают знаете вы слово или нет")
        val handler = android.os.Handler()
        handler.postDelayed({ tvStartTest?.setVisibility(View.GONE)
                            StartTest()}, 10000)
    }
    fun StartTest(){
        tvDefinitionName?.setVisibility(View.VISIBLE)
        bnInow?.setVisibility(View.VISIBLE)
        bnIdnNow?.setVisibility(View.VISIBLE)
        tvDefinitionName?.setText(definitionsName[number])
    }
    fun onClickInow(view:View){
        tvDefinitionName?.setVisibility(View.GONE)
        tvSecondDefinitionsName?.setText(definitionsName[number])
        tvSecondDefinitionsName?.setVisibility(View.VISIBLE)
        tvStartTest?.setText(definition[number])
        number+=1
        tvStartTest?.setVisibility(View.VISIBLE)
        bnIdnNow?.setVisibility(View.GONE)
        bnInow?.setVisibility(View.GONE)
        bnNextDefinition?.setVisibility(View.VISIBLE)
        testResult+=1
    }
    fun onClickIdnNow(view:View){
        tvDefinitionName?.setVisibility(View.GONE)
        tvSecondDefinitionsName?.setText(definitionsName[number])
        tvSecondDefinitionsName?.setVisibility(View.VISIBLE)
        tvStartTest?.setText(definition[number])
        number+=1
        tvStartTest?.setVisibility(View.VISIBLE)
        bnIdnNow?.setVisibility(View.GONE)
        bnInow?.setVisibility(View.GONE)
        bnNextDefinition?.setVisibility(View.VISIBLE)
    }
    fun onClickNextDefinition(view:View){
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (number == 10) {
            tvSecondDefinitionsName?.setVisibility(View.GONE)
            bnNextDefinition?.setVisibility(View.GONE)
            bnIdnNow?.setVisibility(View.GONE)
            bnInow?.setVisibility(View.GONE)
            tvDefinitionName?.setVisibility(View.GONE)
            bnAfterTestGoTasks?.setVisibility(View.VISIBLE)
            if (testResult <= 3) {
                tvStartTest?.setText("Поздравляю! Вы завршили прохождение теста. Ваш уровень: низкий")

            } else if (3 < testResult && 7 > testResult) {
                tvStartTest?.setText("Поздравляю! Вы завршили прохождение теста. Ваш уровень: средний")
                val userDoc = com.flirex.verbum.modules.User(
                    uid = currentUser?.uid,
                    email = currentUser?.email,
                    name = currentUser?.displayName,
                    score = "6",
                    level = "2",
                    wordToLearnProfessionLow = "Агроном Аналитик Архивариус Архитектор Астроном Бухгалтер",
                    wordToLearnProfessionMiddle = "Актуарий Аудитор Брокер Верстальщик Визажист Геофизик",
                    wordToLearnProfessionHigh = "Байер Брейдер Геодезист Имиджмейкер Копирайтер Лоббист",
                    learnedWords = "",
                    wordToLearn = "6"
                )
                val washingtonRef = db.collection("users").document(currentUser!!.uid)
                washingtonRef
                    .set(userDoc)
            } else {
                tvStartTest?.setText("Поздравляю! Вы завршили прохождение теста. Ваш уровень: высокий")
                val userDoc = com.flirex.verbum.modules.User(
                    uid = currentUser?.uid,
                    email = currentUser?.email,
                    name = currentUser?.displayName,
                    score = "6",
                    level = "3",
                    wordToLearnProfessionLow = "Агроном Аналитик Архивариус Архитектор Астроном Бухгалтер",
                    wordToLearnProfessionMiddle = "Актуарий Аудитор Брокер Верстальщик Визажист Геофизик",
                    wordToLearnProfessionHigh = "Байер Брейдер Геодезист Имиджмейкер Копирайтер Лоббист",
                    learnedWords = "",
                    wordToLearn = "6"
                )
                val washingtonRef = db.collection("users").document(currentUser!!.uid)
                washingtonRef
                    .set(userDoc)
            }


        }else {
            bnNextDefinition?.setVisibility(View.GONE)
            tvSecondDefinitionsName?.setVisibility(View.GONE)
            tvStartTest?.setVisibility(View.GONE)
            bnInow?.setVisibility(View.VISIBLE)
            bnIdnNow?.setVisibility(View.VISIBLE)
            tvDefinitionName?.setText(definitionsName[number])
            tvDefinitionName?.setVisibility(View.VISIBLE)
        }

    }
    fun onClickAfterTestGoTasks(view:View){
        var goTasks = Intent(this, Tasks::class.java)
        startActivity(goTasks)
    }
}