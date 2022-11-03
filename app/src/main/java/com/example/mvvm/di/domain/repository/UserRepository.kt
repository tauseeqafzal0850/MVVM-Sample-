package com.example.mvvm.di.domain.repository

import androidx.annotation.WorkerThread
import com.example.mvvm.data.database.models.UserClass
import com.example.mvvm.di.domain.local.DAO.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor (private val userDao: UserDao) {
   val getUser: Flow<List<UserClass>> = userDao.getUser()

   @WorkerThread
   suspend fun insert(user:UserClass) = withContext(Dispatchers.IO){
      userDao.insert(user)
   }
}