package com.example.common.utils

import android.content.Context
import com.example.common.di.AppScope
import javax.inject.Inject

@AppScope
class ResourceManager @Inject constructor(
    private val context: Context
) {
    fun getString(resId: Int): String = context.getString(resId)
    fun getString(resId: Int, vararg formatArgs: Any): String =
        context.getString(resId, *formatArgs)
}
