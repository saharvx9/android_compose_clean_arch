package com.sahar.composecleanarchitecture.application.di.module


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.sahar.composecleanarchitecture.data.source.user.UserDataSource
import com.sahar.composecleanarchitecture.data.source.user.UserRepository
import com.sahar.composecleanarchitecture.data.source.user.local.UserLocalRepository
import com.sahar.composecleanarchitecture.data.source.user.remote.UserRemoteRepository
import com.sahar.composecleanarchitecture.application.di.scope.Local
import com.sahar.composecleanarchitecture.application.di.scope.Remote
import com.sahar.composecleanarchitecture.application.datastoreapp.DataStoreApp
import com.sahar.composecleanarchitecture.data.source.articles.ArticlesDataSource
import com.sahar.composecleanarchitecture.data.source.articles.ArticlesRepository
import com.sahar.composecleanarchitecture.data.source.articles.local.ArticlesLocalRepository
import com.sahar.composecleanarchitecture.application.db.dao.ArticleDao
import com.sahar.composecleanarchitecture.data.source.articles.remote.ArticlesRemoteRepository
import com.sahar.composecleanarchitecture.data.source.articles.remote.api.ArticlesApi
import com.sahar.composecleanarchitecture.data.source.theme.ThemeDataSource
import com.sahar.composecleanarchitecture.data.source.theme.ThemeLocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryBindingModule {

    @Provides
    fun providesUserRepository(
        @Local userLocalRepository: UserDataSource.Local,
        @Remote userRemoteRepository: UserDataSource.Remote
    ): UserRepository {
        return UserRepository(userLocalRepository, userRemoteRepository)
    }

    @Provides
    @Singleton
    @Local
    fun providesLocalUserRepository(dataStore: DataStore<Preferences>): UserDataSource.Local {
        return UserLocalRepository(dataStore)
    }

    @Provides
    @Remote
    fun providesUserRemoteRepository(): UserDataSource.Remote {
        return UserRemoteRepository()
    }

    @Provides
    @Singleton
    fun providesThemeRepository(appDataStore: DataStoreApp):ThemeDataSource.Local{
        return ThemeLocalRepository(appDataStore)
    }

    @Provides
    fun provideUserRepository(@Local articlesLocalDataSource: ArticlesDataSource.Local,
                              @Remote articlesRemoteDataSource: ArticlesDataSource.Remote): ArticlesRepository {
        return ArticlesRepository(articlesLocalDataSource, articlesRemoteDataSource)
    }

    @Provides
    @Singleton
    @Local
    fun provideLocalArticlesDataSource(articleDao: ArticleDao): ArticlesDataSource.Local {
        return ArticlesLocalRepository(articleDao)
    }

    @Provides
    @Remote
    fun provideRemoteArticlesDataSource(articlesApi: ArticlesApi): ArticlesDataSource.Remote {
        return ArticlesRemoteRepository(articlesApi)
    }

}