package com.ez.dotarate.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Аннотацией Entity нам необходимо пометить объект, который мы хотим хранить в базе данных
 * Этот класс будет использован для создания таблицы в базе
 * В качестве имени таблицы будет использовано имя класса
 * А поля таблицы будут созданы в соответствии с полями класса
 */
@Entity(tableName = "games")
data class Game internal constructor(
    @field:PrimaryKey
    val match_id: Long,
    val assists: Int,
    val deaths: Int,
    val duration: Int,
    val game_mode: Int,
    val hero_id: Int,
    val kills: Int,
    val leaver_status: Int,
    val lobby_type: Int,
    val party_size: Int,
    val player_slot: Int,
    val radiant_win: Boolean,
    val skill: Int,
    val start_time: Long,
    val version: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Game

        if (assists != other.assists) return false
        if (deaths != other.deaths) return false
        if (duration != other.duration) return false
        if (game_mode != other.game_mode) return false
        if (hero_id != other.hero_id) return false
        if (kills != other.kills) return false
        if (leaver_status != other.leaver_status) return false
        if (lobby_type != other.lobby_type) return false
        if (match_id != other.match_id) return false
        if (party_size != other.party_size) return false
        if (player_slot != other.player_slot) return false
        if (radiant_win != other.radiant_win) return false
        if (skill != other.skill) return false
        if (start_time != other.start_time) return false
        if (version != other.version) return false

        return true
    }

    override fun hashCode(): Int {
        var result = assists
        result = 31 * result + deaths
        result = 31 * result + duration
        result = 31 * result + game_mode
        result = 31 * result + hero_id
        result = 31 * result + kills
        result = 31 * result + leaver_status
        result = 31 * result + lobby_type
        result = 31 * result + match_id.hashCode()
        result = 31 * result + party_size
        result = 31 * result + player_slot
        result = 31 * result + radiant_win.hashCode()
        result = 31 * result + skill
        result = 31 * result + start_time.hashCode()
        result = 31 * result + version
        return result
    }
}