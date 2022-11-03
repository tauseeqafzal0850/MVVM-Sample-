package com.example.mvvm.di.domain.repository

import com.example.mvvm.core.Resource
import com.example.mvvm.data.models.UserClass
import com.example.mvvm.di.responses.DatabaseResponse
import com.example.mvvm.di.responses.NewsResponseDto
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    fun addUser(userClass: UserClass):Flow<Resource<DatabaseResponse>>
    fun getUsersList():Flow<Resource<DatabaseResponse>>
}