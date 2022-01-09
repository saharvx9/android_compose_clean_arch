package com.sahar.composecleanarchitecture.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.sahar.composecleanarchitecture.data.UserDataSource
import com.sahar.composecleanarchitecture.di.scope.Local
import javax.inject.Inject

@Local
class UserLocalRepository @Inject constructor(private val dataStore:DataStore<Preferences>): UserDataSource.Local {

}