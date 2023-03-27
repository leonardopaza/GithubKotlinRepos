package com.lp.data_local.utils

import android.content.Context
import androidx.room.Room
import com.lp.data_local.database.AppDatabase

object DatabaseFactory {
    fun createRoomDatabase(context: Context, databaseName: String) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            databaseName
        ).build()
    }
}