/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.feats.calculator.input

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.mocca.feats.calculator.utils.WindowSizeUtilityDefaults
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterial3WindowSizeClassApi
internal class MoneyAmountInputUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldCheckAmountIsDisplayed() {
    composeTestRule.setContent {
      MoneyAmountInput(
        windowSizeInfo = WindowSizeUtilityDefaults.mobilePortrait,
        amountTextState = mutableStateOf("123"),
      )
    }
    composeTestRule.onNodeWithText("123").assertIsDisplayed()
  }

}
