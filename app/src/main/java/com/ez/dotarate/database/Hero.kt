package com.ez.dotarate.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Аннотацией Entity нам необходимо пометить объект, который мы хотим хранить в базе данных
 * Этот класс будет использован для создания таблицы в базе
 * В качестве имени таблицы будет использовано имя класса
 * А поля таблицы будут созданы в соответствии с полями класса
 */

@Entity(tableName = "heroes")
data class Hero(
    @field:PrimaryKey
    val hero_id: Int,
    val games: Int,
    val last_played: Int,
    val win: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Hero

        if (hero_id != other.hero_id) return false
        if (games != other.games) return false
        if (last_played != other.last_played) return false
        if (win != other.win) return false

        return true
    }

    override fun hashCode(): Int {
        var result = hero_id
        result = 31 * result + games
        result = 31 * result + last_played
        result = 31 * result + win
        return result
    }
}