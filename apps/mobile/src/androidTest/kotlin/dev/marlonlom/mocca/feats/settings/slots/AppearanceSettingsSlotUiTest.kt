/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.feats.settings.slots

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.mocca.BuildConfig
import dev.marlonlom.mocca.feats.settings.UserPreferences
import org.junit.Rule
import org.junit.Test

internal class AppearanceSettingsSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  private var settingKey = ""
  private var settingSwitched = false

  @Test
  fun shouldDisplaySlotAndClickDarkThemeSwitch() {
    with(composeTestRule) {
      setContent {
        AppearanceSettingsSlot(
          userPreferences = UserPreferences(
            aboutEfectyUrl = "",
            appVersion = BuildConfig.VERSION_NAME,
            darkTheme = false,
            dynamicColors = false
          ),
          onBooleanSettingChanged = { key, switched ->
            settingKey = key
            settingSwitched = switched
          }
        )
      }

      onNodeWithText("Appearance").assertIsDisplayed()
      onNodeWithText(text = "Dark theme").assertIsDisplayed()
      onAllNodesWithTag("BooleanSettingSwitch").onFirst().assertIsDisplayed().performClick()

      assertThat(settingKey).isEqualTo("dark_theme")
      assertThat(settingSwitched).isTrue()
    }
  }

  @Test
  fun shouldDisplaySlotAndClickDynamicColorsSwitch() {
    with(composeTestRule) {
      setContent {
        AppearanceSettingsSlot(
          userPreferences = UserPreferences(
            aboutEfectyUrl = "",
            appVersion = BuildConfig.VERSION_NAME,
            darkTheme = false,
            dynamicColors = false
          ),
          onBooleanSettingChanged = { key, switched ->
            settingKey = key
            settingSwitched = switched
          }
        )
      }

      onNodeWithText("Appearance").assertIsDisplayed()
      onNodeWithText(text = "Dynamic colors").assertIsDisplayed()
      onAllNodesWithTag("BooleanSettingSwitch")[1].assertIsDisplayed().performClick()

      assertThat(settingKey).isEqualTo("dynamic_colors")
      assertThat(settingSwitched).isTrue()
    }
  }
}
