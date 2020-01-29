package com.ez.dotarate.model

data class UpcomingGame(
    val begin_at: String,
    val name: String,
    val number_of_games: Int,
    val league: League,
    val opponents: ArrayList<Opponent>?
)

data class Opponent(
    val opponent: OpponentX
)

data class OpponentX(
    val acronym: String?,
    val image_url: String,
    val name: String
)

data class League(
    val id: Int,
    // Имя турнира. Например: "DreamLeague"
    val name: String,
    val image_url: String
)