package com.joseph.kmmsocialapp.android.common.extensions

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream


fun Bitmap?.toByteArray(): ByteArray? {
    if (this == null) return null
    val stream = ByteArrayOutputStream()
    compress(Bitmap.CompressFormat.PNG, 100, stream)
    val byteArray = stream.toByteArray()
    recycle()
    return byteArray
}