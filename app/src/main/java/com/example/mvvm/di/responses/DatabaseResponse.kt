package com.example.mvvm.di.responses

import com.example.mvvm.data.models.UserClass

data class DatabaseResponse(val message:String?="",val data:List<UserClass>?=null)