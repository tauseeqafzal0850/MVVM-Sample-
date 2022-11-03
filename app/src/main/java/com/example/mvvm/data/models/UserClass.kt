package com.example.mvvm.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserClass(
    val name: String,
    val designation: String,
    val age: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)