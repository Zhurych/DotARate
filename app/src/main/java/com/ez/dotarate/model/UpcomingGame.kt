package com.ez.dotarate.model

data class UpcomingGame(
    val begin_at: String,
    val name: String,
    val number_of_games: Int,
    val opponents: ArrayList<Opponent>
)

data class Opponent(
    val acronym: String?,
    val image_url: String,
    val name: String
)
