package com.example.mvvm.data.implementations

import com.example.mvvm.core.Resource
import com.example.mvvm.data.responses.NewsResponseDto
import com.example.mvvm.di.domain.repository.MoviesRepository
import com.example.mvvm.di.domain.remote.ApiService
import com.example.mvvm.presentation.utils.Utility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MoviesRepository {

    /*
    Fetch Popular Movies
     */
    override fun getPopularNews(

    ): Flow<Resource<NewsResponseDto>> =
        flow {
            emit(
                Resource.Loading(
                    data = NewsResponseDto()
                )
            )
            try {
                val apiCall = apiService.getPopularNews()
                if (!apiCall.body()!!.articles.isNullOrEmpty()) {
                    emit(Resource.Success(apiCall.body()))
                } else {
                    emit(
                        Resource.Error(
                            message = apiCall.message(),
                            data = NewsResponseDto()
                        )
                    )
                }

            } catch (e: HttpException) {
                val errorInfo = Utility.parseError(e.response())
                emit(
                    Resource.Error(
                        message = errorInfo!!,
                        data = NewsResponseDto()
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Couldn't reach server, check your internet connection.",
                        data = NewsResponseDto()
                    )
                )
            }
        }
}