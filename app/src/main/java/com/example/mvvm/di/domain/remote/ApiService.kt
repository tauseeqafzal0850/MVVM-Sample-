package com.example.mvvm.di.domain.remote

import com.example.mvvm.data.responses.NewsResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("in.json")
    suspend fun getPopularNews():Response<NewsResponseDto>
}