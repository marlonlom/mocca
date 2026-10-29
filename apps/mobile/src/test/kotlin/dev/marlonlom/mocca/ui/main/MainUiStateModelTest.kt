/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.main

import dev.marlonlom.mocca.core.preferences.model.UserColorContrasts
import dev.marlonlom.mocca.core.preferences.model.UserSettings
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class MainUiStateModelTest {

  @Test
  fun `given default UserSettings instantiation when colorContrast is omitted then default is STANDARD`() {
    val settings = UserSettings(
      isOnboarding = false,
      useDarkTheme = true,
      useDynamicColor = false,
    )
    assertEquals(UserColorContrasts.STANDARD.name, settings.colorContrast)
  }

  @Test
  fun `given MainUiState Loading when accessed then is instance of Loading`() {
    val state: MainUiState = MainUiState.Loading
    assertTrue(state is MainUiState.Loading)
  }

  @Test
  fun `given MainUiState Success when initialized with defaults then shouldOpenFees is false`() {
    val settings = UserSettings(
      isOnboarding = true,
      useDarkTheme = false,
      useDynamicColor = true,
      colorContrast = "HIGH",
    )
    val state = MainUiState.Success(userData = settings)
    assertEquals(settings, state.userData)
    assertFalse(state.shouldOpenFees)
  }

  @Test
  fun `given MainUiState Success when initialized with custom shouldOpenFees then retains custom value`() {
    val settings = UserSettings(
      isOnboarding = false,
      useDarkTheme = false,
      useDynamicColor = false,
    )
    val state = MainUiState.Success(userData = settings, shouldOpenFees = true)
    assertTrue(state.shouldOpenFees)
  }

  @Test
  fun `given UserSettings when copied with new properties then returns updated instance`() {
    val original = UserSettings(
      isOnboarding = true,
      useDarkTheme = false,
      useDynamicColor = false,
      colorContrast = "LOW",
    )
    val modified = original.copy(useDarkTheme = true, colorContrast = "HIGH")
    assertTrue(modified.useDarkTheme)
    assertEquals("HIGH", modified.colorContrast)
    assertEquals(original.isOnboarding, modified.isOnboarding)
  }
}
