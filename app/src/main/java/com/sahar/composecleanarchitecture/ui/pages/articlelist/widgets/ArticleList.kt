package com.sahar.composecleanarchitecture.ui.pages.articlelist.widgets

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.sahar.composecleanarchitecture.data.model.ArticleItem

//https://developer.android.com/jetpack/compose/lists

@ExperimentalMaterialApi
@Composable
fun ArticleList(articles: List<ArticleItem>,onClickItem:(item:ArticleItem)->Unit) {
    LazyColumn {
        items(articles) { ArticleItemView(it, onClickItem = onClickItem) }
    }
}

