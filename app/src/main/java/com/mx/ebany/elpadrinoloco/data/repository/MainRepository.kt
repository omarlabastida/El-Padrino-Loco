package com.mx.ebany.elpadrinoloco.data.repository

import com.mx.ebany.elpadrinoloco.data.local.entities.UsersEntity


interface MainRepository  {
    suspend fun saveUser(data: UsersEntity)
    suspend fun getUsers():List<UsersEntity>
}