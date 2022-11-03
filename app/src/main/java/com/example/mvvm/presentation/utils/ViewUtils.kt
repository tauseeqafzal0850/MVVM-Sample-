package com.example.mvvm.presentation.utils

import android.app.Activity
import android.app.Application
import androidx.core.content.res.ResourcesCompat
import com.example.mvvm.R

object ViewUtils {

    fun showToast(activity: Activity,title:String,message:String,type:MotionToastStyle)
    {
        MotionToast.createToast(
            activity,
            title,
            message,
            type,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(activity, R.font.helvetica_regular)
        )
    }
}