package com.example.mvvm.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.mvvm.data.database.models.UserClass
import com.example.mvvm.di.domain.local.UserDao

@Database(entities = [UserClass::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao():UserDao

}