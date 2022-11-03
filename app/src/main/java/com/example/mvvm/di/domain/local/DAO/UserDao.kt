package com.example.mvvm.di.domain.local.DAO


import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.mvvm.data.database.models.UserClass
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserClass)

    @Query("SELECT * FROM userTable ORDER BY userID ASC")
    fun getUser(): Flow<List<UserClass>>
}