package com.example.mvvm.presentation.ui.browse

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.database.models.UserClass
import com.example.mvvm.di.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val getUser:LiveData<List<UserClass>> get() =
        userRepository.getUser.flowOn(Dispatchers.Main)
            .asLiveData(context = viewModelScope.coroutineContext)

    fun insert(user:UserClass){
        viewModelScope.launch {
            userRepository.insert(user)
        }
    }
}