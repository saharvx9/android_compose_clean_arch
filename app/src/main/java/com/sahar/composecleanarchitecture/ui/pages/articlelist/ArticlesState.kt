package com.sahar.composecleanarchitecture.ui.pages.articlelist

import com.sahar.composecleanarchitecture.data.model.ArticleItem

sealed class ArticlesState {
    object LoadingState : ArticlesState()
    class ErrorState(val message:String) : ArticlesState()
    class ArticlesListResult(val list:List<ArticleItem>):ArticlesState()
}