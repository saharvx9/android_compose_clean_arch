package com.sahar.composecleanarchitecture.data.source.theme

import com.sahar.composecleanarchitecture.application.datastoreapp.DataStoreApp
import com.sahar.composecleanarchitecture.application.di.scope.Local
import com.sahar.composecleanarchitecture.ui.pages.base.ThemeType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Local
class ThemeLocalRepository
@Inject constructor(private val appDataStore:DataStoreApp):ThemeDataSource.Local {

    override fun loadThemeType(): Flow<ThemeType> {
        return appDataStore.getThemeType()
    }

    override suspend fun setThemeType(type: ThemeType) {
         appDataStore.setThemeType(type)
    }

}