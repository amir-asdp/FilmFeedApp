package com.example.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.data.constant.Constants.DataSourceLocal
import com.example.data.model.local.MovieBriefEntity

@Database(entities = [MovieBriefEntity::class], version = 1, exportSchema = false)
abstract class LocalDataSource : RoomDatabase() {

    abstract fun movieBriefEntityDao(): MovieBriefEntityDao

    companion object {
        fun getInstance(context: Context): LocalDataSource {
            return databaseBuilder(
                context.applicationContext,
                LocalDataSource::class.java, DataSourceLocal.DB_FILE_NAME
            ).fallbackToDestructiveMigration()
                .build()
        }
    }

}