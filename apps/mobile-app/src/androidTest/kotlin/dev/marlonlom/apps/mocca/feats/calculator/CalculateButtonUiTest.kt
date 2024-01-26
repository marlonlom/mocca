/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

internal class CalculateButtonUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldCheckButtonDisabled() {
    composeTestRule.setContent {
      CalculateButton(buttonEnabled = false, buttonClicked = {})
    }
    val onNodeWithText = noteWithTextCalculate()
    onNodeWithText.assertIsNotEnabled()
  }

  @Test
  fun shouldCheckButtonEnabledAndClickable() {
    composeTestRule.setContent {
      CalculateButton(buttonEnabled = true, buttonClicked = {})
    }
    val onNodeWithText = noteWithTextCalculate()
    onNodeWithText.assertIsEnabled()
    onNodeWithText.performClick()
  }

  private fun noteWithTextCalculate() = composeTestRule.onNodeWithText("Calculate")
}
