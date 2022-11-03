package com.example.mvvm.di.domain.remote

import com.example.mvvm.data.responses.GetMoviesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key:String,@Query("language") language:String,
        @Query("page") page:Int):Response<GetMoviesResponseDto>
}