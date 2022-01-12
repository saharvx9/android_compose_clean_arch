package com.sahar.composecleanarchitecture.application.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sahar.composecleanarchitecture.data.model.ArticleItem

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<ArticleItem>

    @Insert
    suspend fun insertArticles(articles:List<ArticleItem>)

    @Query("DELETE FROM articles")
    suspend fun deleteAllArticles()
}