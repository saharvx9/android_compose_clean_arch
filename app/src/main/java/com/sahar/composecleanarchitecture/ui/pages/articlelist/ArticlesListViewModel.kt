package com.sahar.composecleanarchitecture.ui.pages.articlelist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sahar.composecleanarchitecture.data.model.ArticleItem
import com.sahar.composecleanarchitecture.data.source.articles.ArticlesRepository
import com.sahar.composecleanarchitecture.ui.pages.articlelist.ArticlesState.*
import com.sahar.composecleanarchitecture.ui.pages.base.BaseViewModel
import com.sahar.composecleanarchitecture.widgets.dialog.AppAlert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesListViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val articlesRepository: ArticlesRepository
) : BaseViewModel() {

    private val articlesStateMutableState = MutableStateFlow<ArticlesState>(LoadingState)
    val articleState get():Flow<ArticlesState> = articlesStateMutableState
    private val articlesCache = arrayListOf<ArticleItem>()

    override fun start() {
        loadArticles()
    }

    fun search(input: String?) {
        viewModelScope.launch(Dispatchers.Default) {
            if (input.isNullOrEmpty()) {
                articlesStateMutableState.emit(ArticlesListResult(articlesCache))
                cancel()
            }
            val filter = articlesCache.filter {
                it.content?.lowercase()?.contains(input!!.lowercase()) == true
                        || it.description?.lowercase()?.contains(input!!.lowercase()) == true
            }
            articlesStateMutableState.emit(ArticlesListResult(filter))
        }
    }

    private fun loadArticles() {
        articlesRepository.loadArticles(true)
            .catch { e ->
                val message = "failed load articles: ${e.message}\""
                dialogMutableStateFlow.tryEmit(AppAlert.ErrorLoadArticles(message))
                articlesStateMutableState.tryEmit(ErrorState(message))
            }
            .collectInScope {
                articlesCache.addAll(it)
                articlesStateMutableState.tryEmit(ArticlesListResult(it))
            }
    }

    fun onClickItem(item: ArticleItem) {

    }

}