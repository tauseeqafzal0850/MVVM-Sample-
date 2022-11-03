package com.example.mvvm.presentation.ui.browse.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.data.models.UserClass
import com.example.mvvm.di.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var userData: MutableLiveData<List<UserClass>> = MutableLiveData()

    fun observeData():MutableLiveData<List<UserClass>>
    {
        return userData
    }

     suspend fun loadData()
    {
        val list=userRepository.getUserList()
        userData.postValue(list)
    }
    suspend fun insertUser(userClass: UserClass)
    {
        userRepository.saveUser(userClass)
    }

}