/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.settings.domain

import dev.marlonlom.mocca.core.preferences.model.UserColorContrasts
import dev.marlonlom.mocca.core.preferences.model.UserSettings
import dev.marlonlom.mocca.core.preferences.repository.PreferencesRepository
import dev.marlonlom.mocca.mobile.settings.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@OptIn(ExperimentalCoroutinesApi::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class SettingsViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val repository = mockk<PreferencesRepository>()
  private lateinit var viewModel: SettingsViewModel

  @Test
  fun `Should update boolean setting then fetch user settings`() = runTest {
    coEvery { repository.toggleBooleanSetting(any(String::class), any(Boolean::class)) } returns Unit
    coEvery { repository.preferencesFlow } returns flowOf(
      UserSettings(
        isOnboarding = true,
        useDarkTheme = false,
        useDynamicColor = false,
        colorContrast = UserColorContrasts.STANDARD.name,
      ),
    )

    viewModel = SettingsViewModel(repository)
    viewModel.updateBooleanSetting("dark_theme", false)
    viewModel.updateBooleanSetting("dynamic_colors", false)

    val result = mutableListOf<SettingsUiState>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.uiState.toList(result)
    }

    advanceUntilIdle()

    val foundItem = result.last()
    assertNotNull(foundItem)
    assertTrue(foundItem is SettingsUiState.Success)
    if (foundItem is SettingsUiState.Success) {
      assertNotNull(foundItem.settings)
      assertTrue(foundItem.settings.isOnboarding)
      assertFalse(foundItem.settings.useDarkTheme)
      assertFalse(foundItem.settings.useDynamicColor)
      assertEquals(UserColorContrasts.STANDARD.name, foundItem.settings.colorContrast)
    }

    job.cancel()
  }

  @Test
  fun `Should update string setting then fetch user settings`() = runTest {
    coEvery { repository.updateStringSetting(any(String::class), any(String::class)) } returns Unit
    coEvery { repository.preferencesFlow } returns flowOf(
      UserSettings(
        isOnboarding = true,
        useDarkTheme = false,
        useDynamicColor = false,
        colorContrast = UserColorContrasts.MEDIUM.name,
      ),
    )

    viewModel = SettingsViewModel(repository)
    viewModel.updateStringSetting("color_contrast", UserColorContrasts.MEDIUM.name)

    val result = mutableListOf<SettingsUiState>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.uiState.toList(result)
    }

    advanceUntilIdle()

    val foundItem = result.last()
    assertNotNull(foundItem)
    assertTrue(foundItem is SettingsUiState.Success)
    if (foundItem is SettingsUiState.Success) {
      assertNotNull(foundItem.settings)
      assertTrue(foundItem.settings.isOnboarding)
      assertFalse(foundItem.settings.useDarkTheme)
      assertFalse(foundItem.settings.useDynamicColor)
      assertEquals(UserColorContrasts.MEDIUM.name, foundItem.settings.colorContrast)
    }

    job.cancel()
  }
}
