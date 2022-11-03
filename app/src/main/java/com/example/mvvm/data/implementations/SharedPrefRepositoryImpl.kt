package com.example.mvvm.data.implementations

import android.content.Context
import com.example.mvvm.di.domain.repository.SharedPrefRepository
import com.example.mvvm.presentation.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPrefRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : SharedPrefRepository {
    private val prefs = context.getSharedPreferences("moviesPref", Context.MODE_PRIVATE)
    override fun setLoggedIn(value: Boolean) {
        prefs.edit().putBoolean(Constants.LOGGED_IN,value)
    }

    override fun getLoggedIn(): Boolean {
        return prefs.getBoolean(Constants.LOGGED_IN, false)
    }
}