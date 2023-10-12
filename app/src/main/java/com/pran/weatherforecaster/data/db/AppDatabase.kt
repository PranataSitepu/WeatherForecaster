package com.pran.weatherforecaster.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pran.weatherforecaster.data.model.local.FavoriteCityEntity

@Database(
    entities = [FavoriteCityEntity::class],
    version = AppDatabase.DB_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "weatherapp_db"
        const val DB_VERSION = 1
    }

    abstract fun locationDao(): LocationDao

}
