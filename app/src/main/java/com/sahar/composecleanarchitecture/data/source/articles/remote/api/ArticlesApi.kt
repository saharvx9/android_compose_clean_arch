package com.sahar.composecleanarchitecture.data.source.articles.remote.api

import com.sahar.composecleanarchitecture.data.source.articles.remote.response.ArticlesJSONResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {

    @GET("everything")
    suspend fun loadBitCoinArticles(
        @Query("q") q: String = "bitcoin",
        @Query("from") from: String,
        @Query("sortBy") sortBy: String = "publishedAt",
    ): ArticlesJSONResponse


    @GET("top-headlines")
    suspend fun loadTopHeadLinesArticles(
        @Query("country") q: String = "us",
        @Query("category") category: String = "business",
    ): ArticlesJSONResponse

}