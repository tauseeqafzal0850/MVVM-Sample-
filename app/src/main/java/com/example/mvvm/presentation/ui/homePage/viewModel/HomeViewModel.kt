package com.example.mvvm.presentation.ui.homePage.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.core.Resource
import com.example.mvvm.data.implementations.MoviesRepositoryImpl
import com.example.mvvm.data.responses.NewsResponseDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepositoryImpl
) : ViewModel() {

    private val _getPopularNews = MutableLiveData<PopularNewsState>()
    val getPopularNews: LiveData<PopularNewsState> = _getPopularNews

    fun getPopularNews(
    ) {
        viewModelScope.launch {

            moviesRepository.getPopularNews().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _getPopularNews.postValue(
                            PopularNewsState(
                                data = result.data ?: NewsResponseDto(),
                                message = result.message ?: String(),
                                isLoading = false
                            )
                        )

                    }
                    is Resource.Error -> _getPopularNews.postValue(
                        PopularNewsState(
                            data = result.data ?: NewsResponseDto(),
                            message = result.message ?: String(),
                            isLoading = false
                        )
                    )
                    is Resource.Loading -> _getPopularNews.postValue(
                        PopularNewsState(
                            data = result.data ?: NewsResponseDto(),
                            message = result.message ?: String(),
                            isLoading = true
                        )
                    )
                }
            }.launchIn(this)

        }
    }

    data class PopularNewsState(
        val data: NewsResponseDto = NewsResponseDto(),
        val message: String? = "",
        val isLoading: Boolean = false,
    )
}