package com.example.test.model

data class DisneyCharacter(
    val info: Info,
    val data: List<Character>
)

data class Info(
    val count: Int,
    val totalPages: Int,
    val previousPage: String?,
    val nextPage: String?
)

data class Character(
    val _id: Int?=0,
    val films: List<String>?=null,
    val shortFilms: List<String>?=null,
    val tvShows: List<String>?=null,
    val videoGames: List<String>?=null,
    val parkAttractions: List<String>?=null,
    val allies: List<String>?=null,
    val enemies: List<String>?=null,
    val sourceUrl: String?=null,
    val name: String?=null,
    val imageUrl: String?=null,
    val createdAt: String?=null,
    val updatedAt: String?=null,
    val url: String?=null,
    val __v: Int?=0
)
