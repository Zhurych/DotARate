package com.ez.dotarate.model

data class User(
    val response: Response
)

data class Response(
    val players: List<Player>
)

data class Player(
    val avatarfull: String,
    val personaname: String,
    val profileurl: String,
    val steamid: String
)