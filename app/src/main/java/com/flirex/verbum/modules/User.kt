package com.flirex.verbum.modules

// there must be empty constructor
data class User(
    val uid: String? = null,
    val email: String? = null,
    val name: String? = null,
    val score: String = "",
    var level: String = "",
    var wordToLearnProfessionLow: String = "",
    var wordToLearnProfessionMiddle: String = "",
    var wordToLearnProfessionHigh: String = "",
    var learnedWords: String = "",
    var wordToLearn: String = ""
)