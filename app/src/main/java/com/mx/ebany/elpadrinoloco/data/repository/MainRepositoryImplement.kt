package com.mx.ebany.elpadrinoloco.data.repository

import com.mx.ebany.elpadrinoloco.data.local.database.AppDataBase
import com.mx.ebany.elpadrinoloco.data.local.entities.UsersEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImplement @Inject constructor(
    private val dataBase: AppDataBase
): MainRepository {
    override suspend fun saveUser(data: UsersEntity) {
        withContext(Dispatchers.IO) {
            dataBase.usersDao().insert(data)
            return@withContext true
        }
    }

    override suspend fun getUsers():List<UsersEntity> =
        withContext(Dispatchers.IO) {
            val data = dataBase.usersDao().getAllUsers()
            return@withContext data
        }

}