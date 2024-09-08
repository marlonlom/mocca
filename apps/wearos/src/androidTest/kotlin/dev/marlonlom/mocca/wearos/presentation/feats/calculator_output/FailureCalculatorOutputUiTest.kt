/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.wearos.presentation.feats.calculator_output

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

internal class FailureCalculatorOutputUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayInternalErrorFailureCalculatorOutput() {
    val alertMessageText = "Internal error"
    with(rule) {

      setContent {
        FailureCalculatorOutput(
          alertMessageText = alertMessageText,
          onBackNavigationAction = {}
        )
      }

      onNodeWithText(alertMessageText).assertIsDisplayed()
    }
  }

  @Test
  fun shouldDisplayNegativeAmountFailureCalculatorOutput() {
    val alertMessageText = "You cannot send negative amounts."
    with(rule) {

      setContent {
        FailureCalculatorOutput(
          alertMessageText = alertMessageText,
          onBackNavigationAction = {}
        )
      }

      onNodeWithText(alertMessageText).assertIsDisplayed()
    }
  }

  @Test
  fun shouldDisplayBelowRangeFailureCalculatorOutput() {
    val alertMessageText = "You cannot send less than \$5,000."
    with(rule) {

      setContent {
        FailureCalculatorOutput(
          alertMessageText = alertMessageText,
          onBackNavigationAction = {}
        )
      }

      onNodeWithText(alertMessageText).assertIsDisplayed()
    }
  }

  @Test
  fun shouldDisplayAboveRangeFailureCalculatorOutput() {
    val alertMessageText = "You cannot send more than \$3,000,000."
    with(rule) {

      setContent {
        FailureCalculatorOutput(
          alertMessageText = alertMessageText,
          onBackNavigationAction = {}
        )
      }

      onNodeWithText(alertMessageText).assertIsDisplayed()
    }
  }
}
