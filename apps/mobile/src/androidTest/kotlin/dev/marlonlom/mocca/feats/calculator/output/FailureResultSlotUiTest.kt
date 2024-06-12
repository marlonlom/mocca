/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.feats.calculator.output

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.mocca.calculator.model.CalculationException
import dev.marlonlom.mocca.feats.calculator.CalculatorUiState
import org.junit.Rule
import org.junit.Test

internal class FailureResultSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  private var clicked = false

  @Test
  fun shouldCheckErrorForBelowAmountException() {
    with(composeTestRule) {
      setContent {
        FailureResultSlot(
          failureState = CalculatorUiState.WithFailure(
            amount = "123",
            exception = CalculationException.BelowQuantityRange()
          ),
          onSlotClosedAction = {
            clicked = true
          },
        )
      }
      onNodeWithText("You cannot send less than \$5,000.")
        .assertIsDisplayed()
      onNodeWithText("Close").assertIsDisplayed().performClick()
      assertThat(clicked).isTrue()
    }
  }

  @Test
  fun shouldCheckErrorForAboveAmountException() {
    with(composeTestRule) {
      setContent {
        FailureResultSlot(
          failureState = CalculatorUiState.WithFailure(
            amount = "99999999",
            exception = CalculationException.AboveQuantityRange()
          ),
          onSlotClosedAction = {
            clicked = true
          },
        )
      }
      onNodeWithText("You cannot send more than \$3,000,000.")
        .assertIsDisplayed()
      onNodeWithText("Close").assertIsDisplayed().performClick()
      assertThat(clicked).isTrue()
    }
  }

  @Test
  fun shouldCheckErrorForNegativeAmountException() {
    with(composeTestRule) {
      setContent {
        FailureResultSlot(
          failureState = CalculatorUiState.WithFailure(
            amount = "-1",
            exception = CalculationException.NegativeQuantity()
          ),
          onSlotClosedAction = {
            clicked = true
          },
        )
      }
      onNodeWithText("You cannot send negative amounts.")
        .assertIsDisplayed()
      onNodeWithText("Close").assertIsDisplayed().performClick()
      assertThat(clicked).isTrue()
    }
  }
}
