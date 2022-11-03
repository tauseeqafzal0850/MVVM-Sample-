package com.example.mvvm.data.db.DAO


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvm.data.models.UserClass

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserClass)
    @Delete
    suspend fun deleteUser(user: UserClass)

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<UserClass>

    @Query("SELECT * from user where id = :id")
    fun getUserById(id: Int): LiveData<UserClass>
}