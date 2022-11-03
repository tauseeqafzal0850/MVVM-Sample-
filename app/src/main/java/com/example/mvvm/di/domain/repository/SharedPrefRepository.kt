package com.example.mvvm.di.domain.repository

interface SharedPrefRepository {
    fun setLoggedIn(value:Boolean)
    fun getLoggedIn():Boolean
}