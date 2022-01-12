package com.sahar.composecleanarchitecture.data.source.articles.remote.response

import com.google.gson.annotations.SerializedName
import com.sahar.composecleanarchitecture.data.model.ArticleItem

class ArticlesJSONResponse(
    @SerializedName("totalResults")
    val totalResults: Int? = null,

    @SerializedName("articles")
    val articles: List<ArticleItem>? = null,

    @SerializedName("status")
    val status: String? = null
)