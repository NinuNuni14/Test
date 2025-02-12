package com.example.test.model

import java.io.Serializable

data class Joke(val id: Int?, val type: String?, val setup: String?, val punchline: String?) : Serializable
