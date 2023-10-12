package com.pran.weatherforecaster.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pran.weatherforecaster.data.model.local.FavoriteCityEntity

@Dao
interface LocationDao {
    @Query("SELECT * FROM favorite_city")
    fun getAll(): List<FavoriteCityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: FavoriteCityEntity)

    @Update
    fun update(city: FavoriteCityEntity)

    @Delete
    fun delete(city: FavoriteCityEntity)
}
