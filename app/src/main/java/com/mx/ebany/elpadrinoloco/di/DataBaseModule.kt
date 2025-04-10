package com.mx.ebany.elpadrinoloco.di

import android.content.Context
import androidx.room.Room
import com.mx.ebany.elpadrinoloco.data.local.database.AppDataBase
import com.mx.ebany.elpadrinoloco.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            Constants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

}