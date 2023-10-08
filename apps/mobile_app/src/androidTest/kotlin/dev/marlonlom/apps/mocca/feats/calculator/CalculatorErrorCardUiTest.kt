/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.apps.mocca.calculator.model.CalculationException
import dev.marlonlom.apps.mocca.feats.calculator.utils.WindowSizeUtilityDefaults
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterial3WindowSizeClassApi
internal class CalculatorErrorCardUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldCheckErrorCardForBelowAmountException() {
    composeTestRule.setContent {
      CalculatorErrorCard(
        windowSizeUtil = WindowSizeUtilityDefaults.mobilePortrait,
        failureCalculationState = CalculatorUiState.WithFailure(
          amount = "123",
          exception = CalculationException.BelowQuantityRange()
        )
      )
    }
    composeTestRule.onNodeWithText("You cannot send less than \$5,000.").assertIsDisplayed()
  }

  @Test
  fun shouldCheckErrorCardForAboveAmountException() {
    composeTestRule.setContent {
      CalculatorErrorCard(
        windowSizeUtil = WindowSizeUtilityDefaults.mobilePortrait,
        failureCalculationState = CalculatorUiState.WithFailure(
          amount = "99999999",
          exception = CalculationException.AboveQuantityRange()
        )
      )
    }
    composeTestRule.onNodeWithText("You cannot send more than \$3,000,000.").assertIsDisplayed()
  }

  @Test
  fun shouldCheckErrorCardForNegativeAmountException() {
    composeTestRule.setContent {
      CalculatorErrorCard(
        windowSizeUtil = WindowSizeUtilityDefaults.mobilePortrait,
        failureCalculationState = CalculatorUiState.WithFailure(
          amount = "-1",
          exception = CalculationException.NegativeQuantity()
        )
      )
    }
    composeTestRule.onNodeWithText("You cannot send negative amounts.").assertIsDisplayed()
  }
}
