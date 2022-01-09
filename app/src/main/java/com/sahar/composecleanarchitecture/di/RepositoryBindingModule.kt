package com.sahar.composecleanarchitecture.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.sahar.composecleanarchitecture.data.UserDataSource
import com.sahar.composecleanarchitecture.data.UserRepository
import com.sahar.composecleanarchitecture.data.local.UserLocalRepository
import com.sahar.composecleanarchitecture.data.remote.UserRemoteRepository
import com.sahar.composecleanarchitecture.di.scope.Local
import com.sahar.composecleanarchitecture.di.scope.Remote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
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
}