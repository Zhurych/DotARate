package com.ez.dotarate.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
* Аннотацией Entity нам необходимо пометить объект, который мы хотим хранить в базе данных
* Этот класс будет использован для создания таблицы в базе
* В качестве имени таблицы будет использовано имя класса
* А поля таблицы будут созданы в соответствии с полями класса
*/
@Entity
data class UserId internal constructor(
    @field:PrimaryKey
    val id: Long
)