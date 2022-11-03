package com.example.mvvm.data.database.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "userTable")
data class UserClass(@PrimaryKey(autoGenerate = true)
                    var userID: Int = 0,
                     @ColumnInfo(name = "fullName")
                    val userName: String,
                     @ColumnInfo(name = "Designation")
                    val userDesignation: String)