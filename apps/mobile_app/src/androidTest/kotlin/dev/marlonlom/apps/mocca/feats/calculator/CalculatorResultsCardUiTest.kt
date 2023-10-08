/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.apps.mocca.calculator.model.CalculationException
import dev.marlonlom.apps.mocca.calculator.model.CalculationResult
import dev.marlonlom.apps.mocca.feats.calculator.utils.WindowSizeUtilityDefaults
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterial3WindowSizeClassApi
internal class CalculatorResultsCardUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldCheckResultsForFailureUiState() {
    composeTestRule.setContent {
      CalculatorResultsCard(
        windowSizeUtil = WindowSizeUtilityDefaults.mobilePortrait,
        CalculatorUiState.WithFailure(
          amount = "99999999",
          exception = CalculationException.AboveQuantityRange()
        )
      )
    }

    composeTestRule.onNodeWithText("Transferring fee").assertIsDisplayed()
    composeTestRule.onNodeWithText("Total to pay").assertIsDisplayed()
    composeTestRule.onAllNodesWithText("").assertCountEquals(2)
  }

  @Test
  fun shouldCheckResultsForSuccessUiState() {
    composeTestRule.setContent {
      CalculatorResultsCard(
        windowSizeUtil = WindowSizeUtilityDefaults.mobilePortrait,
        CalculatorUiState.WithSuccess(
          amount = "450000",
          response = CalculationResult(
            total = 468000.0,
            fixedFee = 0.0,
            variableFee = 18000.0
          )
        )
      )
    }

    composeTestRule.onNodeWithText("Transferring fee").assertIsDisplayed()
    composeTestRule.onNodeWithText("18000").assertIsDisplayed()
    composeTestRule.onNodeWithText("Total to pay").assertIsDisplayed()
    composeTestRule.onNodeWithText("468000").assertIsDisplayed()
  }

}
