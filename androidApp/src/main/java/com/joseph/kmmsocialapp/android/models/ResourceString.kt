package com.joseph.kmmsocialapp.android.models

import android.content.Context
import androidx.annotation.StringRes

/** Wrapper around string message. For get string message call [format] method. */
sealed class ResourceString {
    /** Return formatted string message. */
    abstract fun format(context: Context): String
}

/** Wrapper for string message. */
data class TextResourceString(val text: String) : ResourceString() {
    override fun format(context: Context): String = text
}

/** Wrapper for message from string resource. */
data class IdResourceString(@StringRes val id: Int) : ResourceString() {
    override fun format(context: Context): String = context.getString(id)
}

/** Wrapper for message from formatted string resource. */
data class FormatResourceString(@StringRes val id: Int, val values: List<Any>) : ResourceString() {
    constructor(@StringRes id: Int, vararg values: Any): this(id, values.toList())
    override fun format(context: Context): String = context.getString(id, *values.toTypedArray())
}

data class MultiplyFormatResourcesString(@StringRes val id: Int, val resource: List<ResourceString>) : ResourceString() {
    constructor(@StringRes id: Int, vararg values: ResourceString): this(id, values.toList())
    override fun format(context: Context): String {
        val strings = resource.map {
            it.format(context)
        }
        return context.getString(id, *strings.toTypedArray())
    }
}
