package com.example.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.constant.Constants.DataSourceLocal.TableMovie
import com.example.data.model.local.MovieBriefEntity

@Dao
interface MovieBriefEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieBriefEntity>)

    @Query("SELECT * FROM ${TableMovie.TABLE_NAME}")
    fun getAllMovies(): PagingSource<Int, MovieBriefEntity>

    @Query("DELETE FROM ${TableMovie.TABLE_NAME}")
    suspend fun clearAll()
}