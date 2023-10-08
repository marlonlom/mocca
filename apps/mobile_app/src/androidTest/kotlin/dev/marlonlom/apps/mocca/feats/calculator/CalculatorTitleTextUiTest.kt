/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.apps.mocca.feats.calculator.utils.WindowSizeUtilityDefaults
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterial3WindowSizeClassApi
internal class CalculatorTitleTextUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldCheckTitleTextIsDisplayed() {
    with(composeTestRule) {
      setContent {
        CalculatorTitleText(windowSizeUtil = WindowSizeUtilityDefaults.mobilePortrait)
      }

      onNodeWithText(
        text = "Calculate the cost of",
        substring = true,
        ignoreCase = true
      ).assertIsDisplayed()
    }

  }
}
