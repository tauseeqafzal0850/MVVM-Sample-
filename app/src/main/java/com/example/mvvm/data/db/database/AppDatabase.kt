package com.example.mvvm.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm.data.models.UserClass
import com.example.mvvm.data.db.DAO.UserDao

@Database(entities = [UserClass::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
}