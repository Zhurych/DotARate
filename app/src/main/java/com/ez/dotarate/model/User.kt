package com.ez.dotarate.model

data class User(
    val response: Response
)

data class Response(
    val players: List<Players>
)

data class Players(
    val avatarfull: String,
    val personaname: String,
    val profileurl: String,
    val steamid: String
)