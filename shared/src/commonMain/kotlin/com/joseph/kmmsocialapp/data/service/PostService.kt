package com.joseph.kmmsocialapp.data.service

import com.joseph.kmmsocialapp.common.data.BASE_URL
import com.joseph.kmmsocialapp.common.data.KtorApi
import com.joseph.kmmsocialapp.data.models.AddPostResponse
import com.joseph.kmmsocialapp.data.models.PostListResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders


internal class PostService : KtorApi() {

    suspend fun addPost(
        byteArrays: List<ByteArray?>,
        message: String?,
        userId: Int
    ): AddPostResponse = client.submitFormWithBinaryData(
        url = "${BASE_URL}/post/add",
        formData = formData {
            append("user_id", userId.toString())
            if (message != null) append("message", message)
            for ((index, byteArray) in byteArrays.withIndex()) {
                if (byteArray != null) append("image$index", byteArray, Headers.build {
                    append(HttpHeaders.ContentType, "image/jpeg")
                    append(HttpHeaders.ContentDisposition, "filename=image$index.png")
                })
            }
        }
    ) {
        onUpload { bytesSentTotal, contentLength ->
            println("Sent $bytesSentTotal bytes from $contentLength")
        }
    }.body()

    suspend fun fetchUserPosts(userId: Int): PostListResponse = client.get {
        endPoint(path = "/post/list/${userId}")
    }.body()
}
