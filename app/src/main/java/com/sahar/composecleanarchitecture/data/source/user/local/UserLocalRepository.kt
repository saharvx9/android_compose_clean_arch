package com.sahar.composecleanarchitecture.data.source.user.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.sahar.composecleanarchitecture.data.source.user.UserDataSource
import com.sahar.composecleanarchitecture.application.di.scope.Local
import javax.inject.Inject

@Local
class UserLocalRepository @Inject constructor(private val dataStore:DataStore<Preferences>): UserDataSource.Local {

}