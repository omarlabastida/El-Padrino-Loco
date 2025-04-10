package com.mx.ebany.elpadrinoloco.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "users"
)

data class UsersEntity (
    @PrimaryKey(autoGenerate = true) val idUser: Int = 0,
    val name: String = "",
)