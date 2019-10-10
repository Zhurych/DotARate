package com.ez.dotarate.model

data class User(
    val userResponse: UserResponse
)

data class UserResponse(
    val userPlayers: List<UserPlayer>
)

data class UserPlayer(
    val avatarfull: String,
    val personaname: String,
    val profileurl: String,
    val steamid: String
)