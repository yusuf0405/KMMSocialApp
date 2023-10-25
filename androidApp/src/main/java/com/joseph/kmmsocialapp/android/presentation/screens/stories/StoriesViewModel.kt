package com.joseph.kmmsocialapp.android.presentation.screens.stories

import androidx.lifecycle.ViewModel
import com.joseph.kmmsocialapp.android.common.fake_data.fakeStories

class StoriesViewModel : ViewModel() {

    init {
       val d= fakeStories.groupBy { it.userId }.toList()

    }

}