/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.preferences.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import dev.marlonlom.mocca.core.preferences.model.UserColorContrasts
import dev.marlonlom.mocca.core.preferences.model.UserPreferenceKeys
import dev.marlonlom.mocca.core.preferences.model.UserSettings
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * Repository for managing user preferences stored in DataStore.
 *
 * This class provides methods to read and update user settings. It encapsulates
 * the logic for interacting with the DataStore and transforming the stored
 * data into a [UserSettings] object.
 *
 * @author marlonlom
 *
 * @property dataStore The DataStore instance for accessing preferences.
 * @property coroutineDispatcher The dispatcher for running DataStore operations.
 */
class PreferencesRepository(
  private val dataStore: DataStore<Preferences>,
  private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

  /**
   * A [Flow] that emits the current [UserSettings] whenever they change.
   *
   * It reads the data from DataStore, handles potential IOExceptions by emitting
   * empty preferences, and maps the raw preferences to a [UserSettings] object.
   * Default values are provided for settings if they are not found in DataStore.
   */
  val preferencesFlow: Flow<UserSettings> = dataStore.data
    .catch { throwable ->
      if (throwable is IOException) {
        emit(emptyPreferences())
      } else {
        throw throwable
      }
    }
    .map { prefs ->
      val useDarkTheme = prefs[UserPreferenceKeys.DARK_THEME] ?: false
      val useDynamicColors = prefs[UserPreferenceKeys.DYNAMIC_COLORS] ?: true
      val isOnboarding = prefs[UserPreferenceKeys.IS_ONBOARDING] ?: true
      val colorContrast = prefs[UserPreferenceKeys.COLOR_CONTRAST] ?: UserColorContrasts.STANDARD.name

      UserSettings(
        useDarkTheme = useDarkTheme,
        useDynamicColor = useDynamicColors,
        isOnboarding = isOnboarding,
        colorContrast = UserColorContrasts.valueOf(colorContrast).name,
      )
    }.flowOn(coroutineDispatcher)

  /**
   * Updates a string preference in DataStore.
   *
   * @param stringKey The key of the string preference to update, as defined in [UserPreferenceKeys].
   * @param newStringValue The new value for the string preference.
   */
  suspend fun updateStringSetting(stringKey: String, newStringValue: String) {
    dataStore.edit { preferences ->
      val stringPref = UserPreferenceKeys.getStringPref(stringKey)
      if (stringPref != null) preferences[stringPref] = newStringValue
    }
  }

  /**
   * Updates a boolean preference in DataStore.
   *
   * @param booleanKey The key of the boolean preference to update, as defined in [UserPreferenceKeys].
   * @param newBooleanValue The new value for the boolean preference.
   */
  suspend fun toggleBooleanSetting(booleanKey: String, newBooleanValue: Boolean) {
    dataStore.edit { preferences ->
      val booleanPref = UserPreferenceKeys.getBooleanPref(booleanKey)
      if (booleanPref != null) preferences[booleanPref] = newBooleanValue
    }
  }
}
