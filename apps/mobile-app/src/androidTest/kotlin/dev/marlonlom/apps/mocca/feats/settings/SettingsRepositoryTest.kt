/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.settings

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
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
internal class SettingsRepositoryTest {

  private val testCoroutineDispatcher = StandardTestDispatcher()
  private val testCoroutineScope = TestScope(testCoroutineDispatcher + Job())

  private var repository: SettingsRepository? = null

  @Before
  fun setUp() {
    repository = SettingsRepository(TestablePreferencesDataStore.getInstance(testCoroutineScope))
  }

  @After
  fun tearDown() {
    testCoroutineScope.cancel()
    repository = null
  }

  @Test
  fun repository_testFetchInitialPreferences() {
    testCoroutineScope.launch {
      val preferences = repository!!.settingsFlow.first()
      with(preferences) {
        assertThat(appVersion).isEqualTo("")
        assertThat(aboutEfectyUrl).isEqualTo("")
        assertThat(darkTheme).isEqualTo(false)
        assertThat(dynamicColors).isEqualTo(true)
      }
    }
  }

  @Test
  fun repository_saveDefaultSettings() {
    testCoroutineScope.launch {
      val expectedSettings = UserPreferences(
        aboutEfectyUrl = "https://efecty.com/about",
        appVersion = "1.0.0",
        darkTheme = false,
        dynamicColors = true
      )
      repository!!.saveDefaults(expectedSettings)
      val preferences = repository!!.settingsFlow.first()
      with(preferences) {
        assertThat(appVersion).isEqualTo(expectedSettings.appVersion)
        assertThat(aboutEfectyUrl).isEqualTo(expectedSettings.aboutEfectyUrl)
        assertThat(darkTheme).isEqualTo(expectedSettings.darkTheme)
        assertThat(dynamicColors).isEqualTo(expectedSettings.dynamicColors)
      }
    }
  }

  @Test
  fun repository_toggleBooleanSettings() {
    testCoroutineScope.launch {
      val expectedSettings = UserPreferences(
        aboutEfectyUrl = "https://efecty.com/about",
        appVersion = "1.0.0",
        darkTheme = false,
        dynamicColors = true
      )
      repository!!.saveDefaults(expectedSettings)
      repository!!.toggleBooleanPreference("dark_theme", !expectedSettings.darkTheme)
      repository!!.toggleBooleanPreference("dynamic_colors", !expectedSettings.dynamicColors)
      val preferences = repository!!.settingsFlow.first()
      with(preferences) {
        assertThat(appVersion).isEqualTo(expectedSettings.appVersion)
        assertThat(aboutEfectyUrl).isEqualTo(expectedSettings.aboutEfectyUrl)
        assertThat(darkTheme).isEqualTo(!expectedSettings.darkTheme)
        assertThat(dynamicColors).isEqualTo(!expectedSettings.dynamicColors)
      }
    }
  }

}
