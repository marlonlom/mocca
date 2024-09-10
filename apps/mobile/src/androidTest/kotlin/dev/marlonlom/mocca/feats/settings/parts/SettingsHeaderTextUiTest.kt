/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.feats.settings.parts

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.mocca.feats.calculator.utils.WindowSizeUtilityDefaults
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterial3WindowSizeClassApi
internal class SettingsHeaderTextUiTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayDefaultSettingSlot() {
    with(composeTestRule) {
      setContent {
        SettingsHeaderText(
          WindowSizeUtilityDefaults.mobilePortrait,
        )
      }

      onNodeWithText("Settings").assertIsDisplayed()
    }
  }
}
