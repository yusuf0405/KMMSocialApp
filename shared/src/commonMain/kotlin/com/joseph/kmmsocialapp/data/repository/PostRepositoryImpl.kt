package com.joseph.kmmsocialapp.data.repository

import com.joseph.kmmsocialapp.common.util.coroutines.DispatcherProvider
import com.joseph.kmmsocialapp.common.util.Result
import com.joseph.kmmsocialapp.data.mappers.PostCloudToPostDomainMapper
import com.joseph.kmmsocialapp.data.models.RecommendedPostsParam
import com.joseph.kmmsocialapp.data.service.PostService
import com.joseph.kmmsocialapp.domain.models.PostDomain
import com.joseph.kmmsocialapp.domain.repository.PostRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.withContext

internal class PostRepositoryImpl(
    private val postService: PostService,
    private val dispatcherProvider: DispatcherProvider,
    private val postCloudToPostDomainMapper: PostCloudToPostDomainMapper,
) : PostRepository {

    private val defaultErrorMessage = "Oops, we could not send your request, try later!"

    override suspend fun addPost(
        byteArray: List<ByteArray?>,
        message: String?,
        userId: Int
    ): Result<PostDomain> {
        return withContext(dispatcherProvider.io) {
            try {
                val response = postService.addPost(byteArray, message, userId)
                return@withContext if (response.data == null) Result.Error(
                    message = response.errorMessage ?: defaultErrorMessage
                )
                else Result.Success(data = postCloudToPostDomainMapper.map(response.data))
            } catch (e: CancellationException) {
                throw e
            } catch (e: Throwable) {
                return@withContext Result.Error(
                    message = e.message ?: defaultErrorMessage,
                )
            }
        }
    }

    override suspend fun fetchUserPosts(userId: Int): Result<List<PostDomain>> {
        return withContext(dispatcherProvider.io) {
            try {
                val response = postService.fetchUserPosts(userId)
                return@withContext if (response.data == null) Result.Error(
                    message = response.errorMessage ?: defaultErrorMessage
                )
                else Result.Success(data = response.data.map(postCloudToPostDomainMapper::map))
            } catch (e: CancellationException) {
                throw e
            } catch (e: Throwable) {
                return@withContext Result.Error(
                    message = defaultErrorMessage,
                )
            }
        }
    }

    override suspend fun fetchRecommendedPosts(
        page: Int,
        pageSize: Int,
        userId: Int
    ): Result<List<PostDomain>> {
        return withContext(dispatcherProvider.io) {
            try {
                val param = RecommendedPostsParam(page, pageSize, userId)
                val response = postService.fetchRecommendedPosts(param)
                return@withContext if (response.data == null) Result.Error(
                    message = response.errorMessage ?: defaultErrorMessage
                )
                else Result.Success(data = response.data.map(postCloudToPostDomainMapper::map))
            } catch (e: CancellationException) {
                throw e
            } catch (e: Throwable) {
                return@withContext Result.Error(
                    message = e.message.toString(),
                )
            }
        }
    }
}