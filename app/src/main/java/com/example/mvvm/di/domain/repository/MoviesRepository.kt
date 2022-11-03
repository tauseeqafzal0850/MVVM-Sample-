package com.example.mvvm.di.domain.repository

import com.example.mvvm.core.Resource
import com.example.mvvm.data.responses.NewsResponseDto
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getPopularNews(): Flow<Resource<NewsResponseDto>>
}