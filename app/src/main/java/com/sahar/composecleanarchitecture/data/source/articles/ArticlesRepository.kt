package com.sahar.composecleanarchitecture.data.source.articles

import com.sahar.composecleanarchitecture.data.model.ArticleItem
import com.sahar.composecleanarchitecture.utils.ext.logInfo
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ArticlesRepository
@Inject constructor(
    private val local: ArticlesDataSource.Local,
    private val remote: ArticlesDataSource.Remote
) {


    fun loadArticles(forceFetch: Boolean): Flow<List<ArticleItem>> {
        return flow {

            emitAll(local.loadArticles())
            if (!forceFetch) return@flow
            val list = remote.loadBitCoinArticles()
                .zip(remote.loadTopHeadlinesArticles()) { a, b -> ArrayList(a + b) }
                .map {
                    local.deleteAll()
                    local.saveArticles(it)
                    it
                }
            emitAll(list)

        }
    }

    suspend fun saveArticleItemCache(article: ArticleItem) {
        local.saveArticleItemCache(article)
    }

    fun loadArticleItemCache(): Flow<ArticleItem> {
        return local.loadArticleItemCache()
    }
}