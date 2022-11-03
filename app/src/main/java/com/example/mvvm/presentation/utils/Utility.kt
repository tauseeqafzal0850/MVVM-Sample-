package com.example.mvvm.presentation.utils

import android.util.Log
import org.json.JSONObject
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
    fun parseDate(dateValue: String): String {
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val output = SimpleDateFormat("dd-MMM-yyyy:hh:mm a")
        var date: Date? = null
        try {
            date = input.parse(dateValue)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return output.format(date)
    }
}