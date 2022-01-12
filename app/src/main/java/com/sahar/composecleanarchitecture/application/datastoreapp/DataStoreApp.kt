package com.sahar.composecleanarchitecture.application.datastoreapp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sahar.composecleanarchitecture.ui.pages.base.ThemeType
import com.sahar.composecleanarchitecture.ui.pages.base.asThemeValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreApp
@Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object{
        private val THEME_KEY = stringPreferencesKey("THEME_KEY")
    }

    fun getThemeType():Flow<ThemeType> = dataStore.data.map { it[THEME_KEY]?.let { value-> value.asThemeValue() }?:ThemeType.SYSTEM }
    suspend fun setThemeType(type:ThemeType) = dataStore.edit { it[THEME_KEY] = type.value}
}