/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.smartwatch.presentation.feats.calculator_output

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.apps.mocca.calculator.model.CalculationResult
import org.junit.Rule
import org.junit.Test

internal class SuccessCalculatorOutputUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplaySuccessCalculatorOutput() {
    with(rule) {

      setContent {
        SuccessCalculatorOutput(
          calculationResult = CalculationResult(
            total = 9_700.0,
            fixedFee = 4_700.0,
            variableFee = 0.0,
          ),
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
