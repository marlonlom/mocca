/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.settings

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
internal class SettingsViewModelTest {

  private val testCoroutineDispatcher = StandardTestDispatcher()
  private val testCoroutineScope = TestScope(testCoroutineDispatcher + Job())

  private var repository: SettingsRepository? = null
  private var viewModel: SettingsViewModel? = null

  @Before
  fun setUp() {
    repository = SettingsRepository(TestablePreferencesDataStore.getInstance(testCoroutineScope))
    viewModel = SettingsViewModel(repository!!)
  }

  @After
  fun tearDown() {
    repository = null
    viewModel = null
  }

  @Test
  fun viewModel_saveDefaultSettings() {
    testCoroutineScope.launch {
      val expectedSettings = UserPreferences(
        aboutEfectyUrl = "https://efecty.com/about",
        appVersion = "1.0.0",
        darkTheme = false,
        dynamicColors = true
      )
      repository!!.saveDefaults(expectedSettings)
      val preferences = viewModel!!.settingsUiState.first()
      with(preferences) {
        Truth.assertThat(appVersion).isEqualTo(expectedSettings.appVersion)
        Truth.assertThat(aboutEfectyUrl).isEqualTo(expectedSettings.aboutEfectyUrl)
        Truth.assertThat(darkTheme).isEqualTo(expectedSettings.darkTheme)
        Truth.assertThat(dynamicColors).isEqualTo(expectedSettings.dynamicColors)
      }
    }
  }

  @Test
  fun viewModel_toggleBooleanSettings() {
    testCoroutineScope.launch {
      val expectedSettings = UserPreferences(
        aboutEfectyUrl = "https://efecty.com/about",
        appVersion = "1.0.0",
        darkTheme = false,
        dynamicColors = true
      )
      repository!!.saveDefaults(expectedSettings)
      viewModel!!.toggleBooleanPreference("dark_theme", !expectedSettings.darkTheme)
      viewModel!!.toggleBooleanPreference("dynamic_colors", !expectedSettings.dynamicColors)

      val preferences = viewModel!!.settingsUiState.first()
      with(preferences) {
        Truth.assertThat(appVersion).isEqualTo(expectedSettings.appVersion)
        Truth.assertThat(aboutEfectyUrl).isEqualTo(expectedSettings.aboutEfectyUrl)
        Truth.assertThat(darkTheme).isEqualTo(!expectedSettings.darkTheme)
        Truth.assertThat(dynamicColors).isEqualTo(!expectedSettings.dynamicColors)
      }
    }
  }
}
