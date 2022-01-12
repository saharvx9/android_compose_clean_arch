package com.sahar.composecleanarchitecture.application.di.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.sahar.composecleanarchitecture.application.datastoreapp.DataStoreApp
import com.sahar.composecleanarchitecture.application.db.AppDatabase
import com.sahar.composecleanarchitecture.application.db.dao.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Module
@InstallIn(SingletonComponent::class)
class DataBindingModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun providesAppDataStore(dataStore:DataStore<Preferences>):DataStoreApp {
        return DataStoreApp(dataStore)
    }

    @Provides
    @Singleton
    fun providesAppDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "ArticlesDb")
            .build()
    }

    @Provides
    fun providesArticleDao(appDatabase: AppDatabase): ArticleDao {
        return appDatabase.articleDao()
    }
}