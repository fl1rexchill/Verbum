package com.flirex.verbum.modules

// there must be empty constructor
data class active(
    val uid: String? = null,
    val email: String? = null,
    val name: String? = null,
    var definition1: String = "",
    var definition2: String = "",
    var definition3: String = "",
    var definitiontipe: String = "",
    var definitionlevel: String = ""
)