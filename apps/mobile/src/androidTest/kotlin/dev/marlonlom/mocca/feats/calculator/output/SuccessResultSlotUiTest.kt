/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.feats.calculator.output

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth
import dev.marlonlom.mocca.calculator.model.CalculationResult
import dev.marlonlom.mocca.feats.calculator.CalculatorUiState
import org.junit.Rule
import org.junit.Test

internal class SuccessResultSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  private var clicked = false

  @Test
  fun shouldCheckResultsForSuccessUiState() {
    with(composeTestRule) {
      setContent {
        SuccessResultSlot(
          successState = CalculatorUiState.WithSuccess(
            amount = "450000",
            response = CalculationResult(
              variableFee = 18000.0,
              fixedFee = 0.0,
              total = 468000.0
            )
          ),
          onSlotClosedAction = {
            clicked = true
          }
        )
      }

      onNodeWithText("Transferring fee").assertIsDisplayed()
      onNodeWithText("\$ 18000").assertIsDisplayed()
      onNodeWithText("Total to pay").assertIsDisplayed()
      onNodeWithText("\$ 468000").assertIsDisplayed()
      onNodeWithText("Close").assertIsDisplayed().performClick()
      Truth.assertThat(clicked).isTrue()
    }
  }
}
