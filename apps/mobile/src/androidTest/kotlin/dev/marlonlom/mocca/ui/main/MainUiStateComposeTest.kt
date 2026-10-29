/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.main

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marlonlom.mocca.core.preferences.model.UserColorContrasts
import dev.marlonlom.mocca.core.preferences.model.UserSettings
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
internal class MainUiStateComposeTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun testLoadingState_dynamicThemingShouldBeFalse() {
    val state = MainUiState.Loading
    var result = true
    composeTestRule.setContent {
      result = state.shouldUseDynamicTheming()
    }
    assertFalse(result)
  }

  @Test
  fun testLoadingState_colorContrastShouldBeStandard() {
    val state = MainUiState.Loading
    var result = ""
    composeTestRule.setContent {
      result = state.shouldUseColorContrast()
    }
    assertEquals(UserColorContrasts.STANDARD.name, result)
  }

  @Test
  fun testSuccessState_shouldUseDynamicThemingFromUserData() {
    val settingsTrue = UserSettings(
      useDynamicColor = true,
      useDarkTheme = false,
      colorContrast = "HIGH",
      isOnboarding = false,
    )
    val settingsFalse = UserSettings(
      useDynamicColor = false,
      useDarkTheme = false,
      colorContrast = "HIGH",
      isOnboarding = false,
    )
    val stateTrue = MainUiState.Success(userData = settingsTrue)
    val stateFalse = MainUiState.Success(userData = settingsFalse)
    var resultTrue = false
    var resultFalse = true
    composeTestRule.setContent {
      resultTrue = stateTrue.shouldUseDynamicTheming()
      resultFalse = stateFalse.shouldUseDynamicTheming()
    }
    assertTrue(resultTrue)
    assertFalse(resultFalse)
  }

  @Test
  fun testSuccessState_shouldUseColorContrastFromUserData() {
    val settings = UserSettings(
      useDynamicColor = false,
      useDarkTheme = false,
      colorContrast = "MEDIUM",
      isOnboarding = false,
    )
    val state = MainUiState.Success(userData = settings)
    var result = ""
    composeTestRule.setContent {
      result = state.shouldUseColorContrast()
    }
    assertEquals("MEDIUM", result)
  }

  @Test
  fun testSuccessState_shouldUseDarkThemeRespectsUserData() {
    val settingsTrue = UserSettings(
      useDynamicColor = false,
      useDarkTheme = true,
      colorContrast = "STANDARD",
      isOnboarding = false,
    )
    val stateTrue = MainUiState.Success(userData = settingsTrue)
    var result = false
    composeTestRule.setContent {
      result = stateTrue.shouldUseDarkTheme()
    }
    assertTrue(result)
  }
}
