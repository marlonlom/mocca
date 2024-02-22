/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator.output

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.apps.mocca.calculator.model.CalculationException
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorUiState
import org.junit.Rule
import org.junit.Test

internal class FailureResultSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldCheckErrorForBelowAmountException() {
    composeTestRule.setContent {
      FailureResultSlot(
        failureState = CalculatorUiState.WithFailure(
          amount = "123",
          exception = CalculationException.BelowQuantityRange()
        ), onSlotClosedAction = {}
      )
    }
    composeTestRule
      .onNodeWithText("You cannot send less than \$5,000.")
      .assertIsDisplayed()
  }

  @Test
  fun shouldCheckErrorForAboveAmountException() {
    composeTestRule.setContent {
      FailureResultSlot(
        failureState = CalculatorUiState.WithFailure(
          amount = "99999999",
          exception = CalculationException.AboveQuantityRange()
        ),
        onSlotClosedAction = {}
      )
    }
    composeTestRule
      .onNodeWithText("You cannot send more than \$3,000,000.")
      .assertIsDisplayed()
  }

  @Test
  fun shouldCheckErrorForNegativeAmountException() {
    composeTestRule.setContent {
      FailureResultSlot(
        failureState = CalculatorUiState.WithFailure(
          amount = "-1",
          exception = CalculationException.NegativeQuantity()
        ),
        onSlotClosedAction = {}
      )
    }
    composeTestRule
      .onNodeWithText("You cannot send negative amounts.")
      .assertIsDisplayed()
  }
}
