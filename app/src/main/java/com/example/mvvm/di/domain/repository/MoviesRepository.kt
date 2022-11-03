package com.example.mvvm.di.domain.repository

import com.example.mvvm.core.Resource
import com.example.mvvm.data.responses.GetMoviesResponseDto
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getPopularMovies(api_key:String,language:String,page:Int): Flow<Resource<GetMoviesResponseDto>>
}