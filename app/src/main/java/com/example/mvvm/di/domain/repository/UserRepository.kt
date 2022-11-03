package com.example.mvvm.di.domain.repository

import androidx.room.RoomDatabase
import com.example.mvvm.data.models.UserClass
import com.example.mvvm.data.db.DAO.UserDao
import com.example.mvvm.data.db.database.AppDatabase
import javax.inject.Inject

class UserRepository @Inject constructor (private val userDatabase: AppDatabase) {

  suspend fun getUserList():List<UserClass>{
      return userDatabase.userDao().getAllUsers()
   }
   suspend fun saveUser(userClass: UserClass)
   {
      userDatabase.userDao().addUser(userClass)
   }
}