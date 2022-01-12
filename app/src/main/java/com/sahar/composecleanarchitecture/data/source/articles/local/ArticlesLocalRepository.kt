package com.sahar.composecleanarchitecture.data.source.articles.local

import com.sahar.composecleanarchitecture.application.di.scope.Local
import com.sahar.composecleanarchitecture.data.model.ArticleItem
import com.sahar.composecleanarchitecture.data.source.articles.ArticlesDataSource
import com.sahar.composecleanarchitecture.application.db.dao.ArticleDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Local
class ArticlesLocalRepository
    @Inject constructor(private val articleDao: ArticleDao) : ArticlesDataSource.Local {

    private var articleItemCache: ArticleItem? = null

    override fun loadArticles(): Flow<List<ArticleItem>> {
        return flow { emit(articleDao.getAllArticles()) }
    }

    override fun saveArticles(articles:List<ArticleItem>): Flow<Unit> {
        return flow { emit(articleDao.insertArticles(articles)) }
    }

    override suspend fun deleteAll() {
        return articleDao.deleteAllArticles()
    }

    override suspend fun saveArticleItemCache(articleItem: ArticleItem){
        this.articleItemCache = articleItem
    }

    override fun loadArticleItemCache(): Flow<ArticleItem> {
       return flow { emit(articleItemCache!!) }
    }
}