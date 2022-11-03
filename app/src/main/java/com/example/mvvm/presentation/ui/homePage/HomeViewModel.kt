package com.example.mvvm.presentation.ui.homePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.core.Resource
import com.example.mvvm.data.responses.GetMoviesResponseDto
import com.example.mvvm.data.implementations.MoviesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepositoryImpl
) : ViewModel() {

    private val _getPopularMovies = MutableLiveData<PopularMoviesState>()
    val getPopularMovies: LiveData<PopularMoviesState> = _getPopularMovies

    fun getPopularMovies(
        api_key: String, language: String, page: Int
    ) {
        viewModelScope.launch {

            moviesRepository.getPopularMovies(api_key, language, page).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _getPopularMovies.postValue(
                            PopularMoviesState(
                                data = result.data ?: GetMoviesResponseDto(),
                                message = result.message ?: String(),
                                isLoading = false
                            )
                        )

                    }
                    is Resource.Error -> _getPopularMovies.postValue(
                        PopularMoviesState(
                            data = result.data ?: GetMoviesResponseDto(),
                            message = result.message ?: String(),
                            isLoading = false
                        )
                    )
                    is Resource.Loading -> _getPopularMovies.postValue(
                        PopularMoviesState(
                            data = result.data ?: GetMoviesResponseDto(),
                            message = result.message ?: String(),
                            isLoading = true
                        )
                    )
                }
            }.launchIn(this)

        }
    }

    data class PopularMoviesState(
        val data: GetMoviesResponseDto = GetMoviesResponseDto(),
        val message: String? = "",
        val isLoading: Boolean = false,
    )
}