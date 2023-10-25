package com.joseph.kmmsocialapp.android.presentation.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.joseph.kmmsocialapp.android.common.theme.SocialAppTheme
import com.joseph.kmmsocialapp.android.models.IdResourceString
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeData()
        setContent {
            SocialAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    SocialApp(
                        loginOutState = viewModel.authState.collectAsStateWithLifecycle(),
                        userId = viewModel.userIdFlow.collectAsStateWithLifecycle(initialValue = null).value
                    )
                }
            }
        }
    }

    private fun observeData(): Unit = with(viewModel) {
        toastFlow.onEach(::showToast).launchIn(lifecycleScope)
    }

    private fun showToast(message: IdResourceString) {
        Toast.makeText(this, message.format(this), Toast.LENGTH_SHORT)
            .show()
    }
}
