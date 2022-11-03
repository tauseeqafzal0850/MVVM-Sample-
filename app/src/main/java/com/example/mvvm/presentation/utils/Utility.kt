package com.example.mvvm.presentation.utils

import android.util.Log
import org.json.JSONObject
import retrofit2.Response

object Utility {
    fun parseError(response: Response<*>?): String? {
        var errorMsg: String? = null
        try {
            val jObjError = JSONObject(response?.errorBody()!!.string())
            errorMsg = jObjError.toString()
            return errorMsg
        } catch (e: Exception) {
            Log.d("error", "parseError: "+e.message)
        }
        return errorMsg
    }
}