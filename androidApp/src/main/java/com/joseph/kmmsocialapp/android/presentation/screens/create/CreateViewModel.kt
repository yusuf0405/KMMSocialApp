package com.joseph.kmmsocialapp.android.presentation.screens.create

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.kmmsocialapp.android.R
import com.joseph.kmmsocialapp.android.common.datastore.UserDataStore
import com.joseph.kmmsocialapp.android.common.datastore.UserPreferences
import com.joseph.kmmsocialapp.android.common.extensions.toByteArray
import com.joseph.kmmsocialapp.android.common.managers.ToastDisplayManager
import com.joseph.kmmsocialapp.android.common.provider.CoroutineDispatcherProvider
import com.joseph.kmmsocialapp.android.mappers.PostDomainToPostMapper
import com.joseph.kmmsocialapp.android.models.IdResourceString
import com.joseph.kmmsocialapp.common.util.coroutines.launchSafe
import com.joseph.kmmsocialapp.domain.usecases.post.AddPostUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

data class CreateUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val message: String? = null,
    val user: UserPreferences = UserPreferences.unknown,
    val uriList: List<Uri> = emptyList(),
)

class CreateViewModel(
    private val addPostUseCase: AddPostUseCase,
    private val postDomainToPostMapper: PostDomainToPostMapper,
    private val userDataStore: UserDataStore,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val toastDisplayManager: ToastDisplayManager,
) : ViewModel(), CreateScreenActions {

    var uiState by mutableStateOf(CreateUiState())
        private set

    init {
        userDataStore.fetchUserFlow()
            .onEach { user -> uiState = uiState.copy(user = user) }
            .launchIn(viewModelScope)
    }

    override fun addPost(contentResolver: ContentResolver) {
        if (uiState.uriList.isEmpty() && uiState.message == null) {
            toastDisplayManager.setToast(IdResourceString(R.string.fill_in_at_least_one_field))
            return
        }

        viewModelScope.launchSafe(coroutineDispatcherProvider.io) {
            uiState = uiState.copy(isLoading = true)
            val bitmaps = uiState.uriList.map { fetchBitmapByUri(contentResolver, it) }

            val response = addPostUseCase(
                byteArray = bitmaps.map { it.toByteArray() },
                message = uiState.message,
                userId = uiState.user.id
            )

            if (response.data != null) {
                discard()
                val result = postDomainToPostMapper.map(response.data!!)
            }

            uiState = uiState.copy(
                isLoading = false,
                errorMessage = response.message
            )
        }
    }

    private fun fetchBitmapByUri(contentResolver: ContentResolver, uri: Uri): Bitmap? {
        return if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(contentResolver, uri)
        } else {
            val source = ImageDecoder.createSource(contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        }
    }

    override fun discard() {
        uiState = uiState.copy(
            uriList = emptyList(),
            message = null
        )
    }

    override fun setImageUri(uri: Uri?) {
        val uriList = uiState.uriList.toMutableList()
        if (uri == null) return
        uriList.add(uri)
        uiState = uiState.copy(uriList = uriList)
    }

    override fun setMessage(message: String) {
        uiState = uiState.copy(message = message)
    }
}
