package com.example.mvvm.data.implementations

import com.example.mvvm.core.Resource
import com.example.mvvm.di.domain.repository.MoviesRepository
import com.example.mvvm.data.responses.GetMoviesResponseDto
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
    override fun getPopularMovies(
        api_key: String,
        language: String,
        page: Int
    ): Flow<Resource<GetMoviesResponseDto>> =
        flow {
            emit(
                Resource.Loading(
                    data = GetMoviesResponseDto()
                )
            )
            try {
                val apiCall = apiService.getPopularMovies(api_key, language, page)
                if (!apiCall.body()!!.results.isNullOrEmpty()) {
                    emit(Resource.Success(apiCall.body()))
                } else {
                    emit(
                        Resource.Error(
                            message = apiCall.message(),
                            data = GetMoviesResponseDto()
                        )
                    )
                }

            } catch (e: HttpException) {
                val errorInfo = Utility.parseError(e.response())
                emit(
                    Resource.Error(
                        message = errorInfo!!,
                        data = GetMoviesResponseDto()
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Couldn't reach server, check your internet connection.",
                        data = GetMoviesResponseDto()
                    )
                )
            }
        }
}