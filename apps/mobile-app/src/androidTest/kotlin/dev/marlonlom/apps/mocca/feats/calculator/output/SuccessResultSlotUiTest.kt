/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator.output

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.apps.mocca.calculator.model.CalculationResult
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorUiState
import org.junit.Rule
import org.junit.Test

internal class SuccessResultSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldCheckResultsForSuccessUiState() {
    composeTestRule.setContent {
      SuccessResultSlot(
        successState = CalculatorUiState.WithSuccess(
          amount = "450000",
          response = CalculationResult(
            variableFee = 18000.0,
            fixedFee = 0.0,
            total = 468000.0
          )
        ),
        onSlotClosedAction = {}
      )
    }

    composeTestRule.onNodeWithText("Transferring fee").assertIsDisplayed()
    composeTestRule.onNodeWithText("\$ 18000").assertIsDisplayed()
    composeTestRule.onNodeWithText("Total to pay").assertIsDisplayed()
    composeTestRule.onNodeWithText("\$ 468000").assertIsDisplayed()
  }
}
