package com.sahar.composecleanarchitecture.data.source.articles.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.sahar.composecleanarchitecture.application.di.scope.Remote
import com.sahar.composecleanarchitecture.data.model.ArticleItem
import com.sahar.composecleanarchitecture.data.source.articles.ArticlesDataSource
import com.sahar.composecleanarchitecture.data.source.articles.remote.api.ArticlesApi
import com.sahar.composecleanarchitecture.utils.ext.flowOnIOThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@Remote
class ArticlesRemoteRepository
    @Inject constructor(private val articlesApi: ArticlesApi)
    : ArticlesDataSource.Remote {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun loadBitCoinArticles(): Flow<List<ArticleItem>> {
       return flow { emit(articlesApi.loadBitCoinArticles(from= LocalDate.now().toSimpleFormat())) }
           .map { it.articles?: listOf() }
           .flowOnIOThread()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun loadTopHeadlinesArticles(): Flow<List<ArticleItem>> {
        return flow { emit(articlesApi.loadTopHeadLinesArticles()) }
            .map { it.articles?: listOf() }
            .flowOnIOThread()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun LocalDate.toSimpleFormat() = format(DateTimeFormatter.ofPattern("yy-MM-dd"))
}