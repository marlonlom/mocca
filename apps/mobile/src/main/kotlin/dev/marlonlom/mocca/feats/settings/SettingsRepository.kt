/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.feats.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import timber.log.Timber

/**
 * User preferences data class.
 *
 * @author marlonlom
 *
 * @property appVersion String preference value for efecty about url.
 * @property aboutEfectyUrl String preference value for efecty about url.
 * @property darkTheme Boolean preference value for enabling dark theme.
 * @property dynamicColors Boolean preference value for enabling dynamic colors.
 */
data class UserPreferences(
  val aboutEfectyUrl: String,
  val appVersion: String,
  val darkTheme: Boolean,
  val dynamicColors: Boolean,
)

/**
 * Class that handles saving and retrieving user preferences.
 *
 * @author marlonlom
 *
 * @property dataStore Preferences datastore.
 */
class SettingsRepository(
  private val dataStore: DataStore<Preferences>
) {

  private object PreferencesKeys {
    val APP_VERSION = stringPreferencesKey("app_version")
    val ABOUT_EFECTY_URL = stringPreferencesKey("about_efecty_url")
    val DARK_THEME = booleanPreferencesKey("dark_theme")
    val DYNAMIC_COLORS = booleanPreferencesKey("dynamic_colors")
  }

  /** Settings as FLow. */
  val settingsFlow: Flow<UserPreferences> = dataStore.data
    .map { preferences -> mapSettings(preferences) }

  /**
   * Save default user preferences into datastore.
   *
   * @param prefs User preferences.
   */
  fun saveDefaults(prefs: UserPreferences) {
    runBlocking {
      dataStore.edit { preferences ->
        val oldAppVersion = preferences[PreferencesKeys.APP_VERSION]
        if (oldAppVersion.isNullOrEmpty()) {
          Timber.d("[SettingsRepository] saving default settings.")
          preferences[PreferencesKeys.ABOUT_EFECTY_URL] = prefs.aboutEfectyUrl
          preferences[PreferencesKeys.APP_VERSION] = prefs.appVersion
          preferences[PreferencesKeys.DARK_THEME] = prefs.darkTheme
          preferences[PreferencesKeys.DYNAMIC_COLORS] = prefs.dynamicColors
        }
      }
    }
  }

  /**
   * Updates boolean preference by defined key.
   *
   * @param preferenceKey Preference key text.
   * @param newValue New boolean value for save.
   */
  suspend fun toggleBooleanPreference(preferenceKey: String, newValue: Boolean) {
    dataStore.edit { prefs ->
      val foundPreference = when (preferenceKey) {
        PreferencesKeys.DARK_THEME.name -> PreferencesKeys.DARK_THEME
        PreferencesKeys.DYNAMIC_COLORS.name -> PreferencesKeys.DYNAMIC_COLORS
        else -> null
      }

      if (foundPreference != null) {
        prefs[foundPreference] = newValue
      }
    }
  }

  /**
   * Map settings to user preference state.
   *
   * @param preferences User preferences from datastore.
   *
   * @return User settings data class with preferences data.
   */
  private fun mapSettings(
    preferences: Preferences
  ) = preferences.run {

    val appVersion = this[PreferencesKeys.APP_VERSION] ?: ""
    val aboutEfectyUrlText = this[PreferencesKeys.ABOUT_EFECTY_URL] ?: ""
    val darkTheme = this[PreferencesKeys.DARK_THEME] ?: false
    val dynamicColors = this[PreferencesKeys.DYNAMIC_COLORS] ?: true

    UserPreferences(
      aboutEfectyUrl = aboutEfectyUrlText,
      appVersion = appVersion,
      darkTheme = darkTheme,
      dynamicColors = dynamicColors
    )
  }

}
