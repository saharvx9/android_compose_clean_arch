package com.sahar.composecleanarchitecture.application.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sahar.composecleanarchitecture.data.model.ArticleItem
import com.sahar.composecleanarchitecture.application.db.dao.ArticleDao

@Database(entities = [ArticleItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}