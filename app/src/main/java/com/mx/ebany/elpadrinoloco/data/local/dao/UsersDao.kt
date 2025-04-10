package com.mx.ebany.elpadrinoloco.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mx.ebany.elpadrinoloco.data.local.entities.UsersEntity

@Dao
interface UsersDao {

    //Querys basicos para la tabla de usuarios

    @Query("SELECT * FROM users WHERE idUser = :idUser")//Query para buscar un usuario por su id
    suspend fun findUserById(idUser: Int): UsersEntity

    @Query("SELECT * FROM users")//Query para obtener todos los usuarios
    suspend fun getAllUsers(): List<UsersEntity>

    @Query("DELETE FROM users")//Query para eliminar todos los usuarios
    suspend fun deleteAllUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)//Query para insertar un usuario
    suspend fun insert(users: UsersEntity)

    @Update//Query para actualizar un usuario
    suspend fun update(users: UsersEntity)
}