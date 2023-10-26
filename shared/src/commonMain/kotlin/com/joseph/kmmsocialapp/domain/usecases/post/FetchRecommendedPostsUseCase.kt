package com.joseph.kmmsocialapp.domain.usecases.post

import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.domain.models.PostDomain
import com.joseph.kmmsocialapp.domain.repository.PostRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FetchRecommendedPostsUseCase : KoinComponent {

    private val repository by inject<PostRepository>()

    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
        userId: Int
    ): Result<List<PostDomain>> {
        return repository.fetchRecommendedPosts(page, pageSize, userId)
    }
}