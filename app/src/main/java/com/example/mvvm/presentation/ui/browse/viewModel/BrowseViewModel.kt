package com.example.mvvm.presentation.ui.browse.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.core.Resource
import com.example.mvvm.data.implementations.DatabaseRepositoryImpl
import com.example.mvvm.data.models.UserClass
import com.example.mvvm.di.responses.DatabaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepositoryImpl
) : ViewModel() {

    private val _getUsersList = MutableLiveData<DatabaseResponseState>()
    val getUsersList: LiveData<DatabaseResponseState> = _getUsersList

    private val _saveUser = MutableLiveData<DatabaseResponseState>()
    val saveUser: LiveData<DatabaseResponseState> = _saveUser

    fun getUsersList(
    ) {
        viewModelScope.launch {

            databaseRepository.getUsersList().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _getUsersList.postValue(
                            DatabaseResponseState(
                                data = result.data ?: DatabaseResponse(),
                                message = result.data!!.message ?: String(),
                                isLoading = false
                            )
                        )

                    }
                    is Resource.Error -> _getUsersList.postValue(
                        DatabaseResponseState(
                            data = result.data ?: DatabaseResponse(),
                            message = result.data!!.message ?: String(),
                            isLoading = false
                        )
                    )
                    is Resource.Loading -> _getUsersList.postValue(
                        DatabaseResponseState(
                            data = result.data ?: DatabaseResponse(),
                            message = result.data!!.message ?: String(),
                            isLoading = true
                        )
                    )
                }
            }.launchIn(this)

        }
    }


    fun saveUser(
        userClass: UserClass
    ) {
        viewModelScope.launch {

            databaseRepository.addUser(userClass =userClass ).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _saveUser.postValue(
                            DatabaseResponseState(
                                data = result.data ?: DatabaseResponse(),
                                message = result.data!!.message ?: String(),
                                isLoading = false
                            )
                        )

                    }
                    is Resource.Error -> _saveUser.postValue(
                        DatabaseResponseState(
                            data = result.data ?: DatabaseResponse(),
                            message = result.data!!.message ?: String(),
                            isLoading = false
                        )
                    )
                    is Resource.Loading -> _saveUser.postValue(
                        DatabaseResponseState(
                            data = result.data ?: DatabaseResponse(),
                            message = result.data!!.message ?: String(),
                            isLoading = true
                        )
                    )
                }
            }.launchIn(this)

        }
    }

    data class DatabaseResponseState(
        val data: DatabaseResponse = DatabaseResponse(),
        val message: String? = "",
        val isLoading: Boolean = false,
    )
}