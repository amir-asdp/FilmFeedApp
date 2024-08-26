package com.example.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.constant.Constants.DataSourceLocal.TableMovie

@Entity(tableName = TableMovie.TABLE_NAME)
data class MovieBriefEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterPhotoPath: String
)