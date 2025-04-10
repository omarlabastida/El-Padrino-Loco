package com.mx.ebany.elpadrinoloco.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mx.ebany.elpadrinoloco.data.local.dao.UsersDao
import com.mx.ebany.elpadrinoloco.data.local.entities.UsersEntity
import com.mx.ebany.elpadrinoloco.utils.Constants

@Database(entities = [
    UsersEntity::class
                     ], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun usersDao(): UsersDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    Constants.DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}