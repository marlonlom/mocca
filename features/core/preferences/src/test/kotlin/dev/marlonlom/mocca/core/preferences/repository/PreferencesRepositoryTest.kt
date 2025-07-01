/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.preferences.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import dev.marlonlom.mocca.core.preferences.model.UserColorContrasts
import dev.marlonlom.mocca.core.preferences.model.UserPreferenceKeys
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class PreferencesRepositoryTest {

  @OptIn(ExperimentalCoroutinesApi::class)
  private val testDispatcher = UnconfinedTestDispatcher()

  @Test
  fun `settingsFlow should map preferences to UserPreferences`() = runTest {
    val mockDataStore = object : DataStore<Preferences> {
      override val data: Flow<Preferences> = flowOf(defaultTestPreferences)

      override suspend fun updateData(transform: suspend (t: Preferences) -> Preferences): Preferences =
        transform(defaultTestPreferences)
    }
    val repository = PreferencesRepository(mockDataStore, testDispatcher)
    val userPreferences = repository.preferencesFlow.first()
    assertEquals(false, userPreferences.useDarkTheme)
    assertEquals(true, userPreferences.useDynamicColor)
    assertEquals(UserColorContrasts.STANDARD.name, userPreferences.colorContrast)
  }

  @Test
  fun `updateBooleanPreference should update boolean preference`() = runTest {
    var storedPreferences: Preferences = emptyPreferences()
    val mockDataStore = object : DataStore<Preferences> {
      override val data: Flow<Preferences> = flowOf(defaultTestPreferences)

      override suspend fun updateData(transform: suspend (t: Preferences) -> Preferences): Preferences {
        storedPreferences = transform(storedPreferences)
        return storedPreferences
      }
    }
    val repository = PreferencesRepository(mockDataStore, testDispatcher)
    repository.toggleBooleanSetting(UserPreferenceKeys.DARK_THEME.name, true)
    repository.toggleBooleanSetting(UserPreferenceKeys.DYNAMIC_COLORS.name, false)
    val updatedPreferences = storedPreferences
    assertEquals(true, updatedPreferences[UserPreferenceKeys.DARK_THEME])
    assertEquals(false, updatedPreferences[UserPreferenceKeys.DYNAMIC_COLORS])
  }

  @Test
  fun `updateStringPreference should update string preference`() = runTest {
    var storedPreferences: Preferences = emptyPreferences()
    val mockDataStore = object : DataStore<Preferences> {
      override val data: Flow<Preferences> = flowOf(defaultTestPreferences)

      override suspend fun updateData(transform: suspend (t: Preferences) -> Preferences): Preferences {
        storedPreferences = transform(storedPreferences)
        return storedPreferences
      }
    }
    val repository = PreferencesRepository(mockDataStore, testDispatcher)
    repository.updateStringSetting(UserPreferenceKeys.COLOR_CONTRAST.name, UserColorContrasts.HIGH.name)
    val updatedPreferences = storedPreferences
    assertEquals(UserColorContrasts.HIGH.name, updatedPreferences[UserPreferenceKeys.COLOR_CONTRAST])
  }

  private val defaultTestPreferences = emptyPreferences().toMutablePreferences().apply {
    this[UserPreferenceKeys.DARK_THEME] = false
    this[UserPreferenceKeys.DYNAMIC_COLORS] = true
    this[UserPreferenceKeys.COLOR_CONTRAST] = UserColorContrasts.STANDARD.name
  }.toPreferences()
}
