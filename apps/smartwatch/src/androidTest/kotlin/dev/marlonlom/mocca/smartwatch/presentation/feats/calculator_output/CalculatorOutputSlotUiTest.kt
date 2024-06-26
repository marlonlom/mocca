/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.smartwatch.presentation.feats.calculator_output

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

internal class CalculatorOutputSlotUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayCalculatorOutputInternalError() {
    with(rule) {

      setContent {
        CalculatorOutput(
          amountText = Double.NaN.toString(),
          onBackNavigationAction = {}
        )
      }

      onNodeWithText("Internal error").assertIsDisplayed()
    }
  }


  @Test
  fun shouldDisplayCalculatorOutputAboveRange() {
    with(rule) {

      setContent {
        CalculatorOutput(
          amountText = "9999999",
          onBackNavigationAction = {}
        )
      }

      onNodeWithText("You cannot send more than \$3,000,000.").assertIsDisplayed()
    }
  }

  @Test
  fun shouldDisplayCalculatorOutputBelowRange() {
    with(rule) {

      setContent {
        CalculatorOutput(
          amountText = "1234",
          onBackNavigationAction = {}
        )
      }

      onNodeWithText("You cannot send less than \$5,000.").assertIsDisplayed()
    }
  }

  @Test
  fun shouldDisplayCalculatorOutputNegativeAmounts() {
    with(rule) {

      setContent {
        CalculatorOutput(
          amountText = "-22",
          onBackNavigationAction = {}
        )
      }

      onNodeWithText("You cannot send negative amounts.").assertIsDisplayed()
    }
  }

  @Test
  fun shouldDisplaySuccessCalculatorOutput() {
    with(rule) {

      setContent {
        CalculatorOutput(
          amountText = 5000.0.toString(),
          onBackNavigationAction = {}
        )
      }

      onNodeWithText("Transferring fee").assertIsDisplayed()
      onNodeWithText("COP \$ 4700").assertIsDisplayed()
      onNodeWithText("Total to pay").assertIsDisplayed()
      onNodeWithText("COP \$ 9700").assertIsDisplayed()
    }
  }


}
