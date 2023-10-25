package com.joseph.kmmsocialapp.android.presentation.screens.create

import android.content.ContentResolver
import android.net.Uri

interface CreateScreenActions {

    fun addPost(contentResolver: ContentResolver)

    fun discard()

    fun setImageUri(uri: Uri?)

    fun setMessage(message: String)

    object Preview : CreateScreenActions {
        override fun addPost(contentResolver: ContentResolver) = Unit
        override fun discard() = Unit
        override fun setImageUri(uri: Uri?) = Unit
        override fun setMessage(message: String) = Unit
    }
}