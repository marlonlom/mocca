/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
internal class SettingsRepositoryTest {

  private val testContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
  private val testCoroutineDispatcher = UnconfinedTestDispatcher()
  private val testCoroutineScope = TestScope(testCoroutineDispatcher + Job())
  private val testDataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
    scope = testCoroutineScope,
    produceFile = {
      testContext.preferencesDataStoreFile("test_mocca_settings")
    }
  )
  private val repository: SettingsRepository = SettingsRepository(testDataStore)

  @Before
  fun setup() {
    Dispatchers.setMain(testCoroutineDispatcher)
  }

  @After
  fun cleanUp() {
    Dispatchers.resetMain()
    testCoroutineScope.cancel()
  }

  @Test
  fun repository_testFetchInitialPreferences() = runTestAndCleanup {
    val preferences = repository.settingsFlow.first()
    with(preferences) {
      assertThat(appVersion).isEqualTo("")
      assertThat(aboutEfectyUrl).isEqualTo("")
      assertThat(darkTheme).isEqualTo(false)
      assertThat(dynamicColors).isEqualTo(true)
    }
  }

  @Test
  fun repository_saveDefaultSettings() = runTestAndCleanup {
    val expectedSettings = UserPreferences(
      aboutEfectyUrl = "https://efecty.com/about",
      appVersion = "1.0.0",
      darkTheme = false,
      dynamicColors = true
    )
    repository.saveDefaults(expectedSettings)
    val preferences = repository.settingsFlow.first()
    with(preferences) {
      assertThat(appVersion).isEqualTo(expectedSettings.appVersion)
      assertThat(aboutEfectyUrl).isEqualTo(expectedSettings.aboutEfectyUrl)
      assertThat(darkTheme).isEqualTo(expectedSettings.darkTheme)
      assertThat(dynamicColors).isEqualTo(expectedSettings.dynamicColors)
    }
  }

  @Test
  fun repository_toggleBooleanSettings() = runTestAndCleanup {
    val expectedSettings = UserPreferences(
      aboutEfectyUrl = "https://efecty.com/about",
      appVersion = "1.0.0",
      darkTheme = false,
      dynamicColors = true
    )

    repository.saveDefaults(expectedSettings)
    repository.toggleBooleanPreference("dark_theme", !expectedSettings.darkTheme)
    repository.toggleBooleanPreference("dynamic_colors", !expectedSettings.dynamicColors)

    val preferences = repository.settingsFlow.first()
    with(preferences) {
      assertThat(appVersion).isEqualTo(expectedSettings.appVersion)
      assertThat(aboutEfectyUrl).isEqualTo(expectedSettings.aboutEfectyUrl)
      assertThat(darkTheme).isEqualTo(!expectedSettings.darkTheme)
      assertThat(dynamicColors).isEqualTo(!expectedSettings.dynamicColors)
    }
  }

  private fun runTestAndCleanup(
    block: suspend () -> Unit,
  ) = testCoroutineScope.runTest {
    block()
    testDataStore.edit {
      it.clear()
    }
  }
}
