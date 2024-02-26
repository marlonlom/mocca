/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator.slots

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.apps.mocca.calculator.model.CalculationException
import dev.marlonlom.apps.mocca.calculator.model.CalculationResult
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorUiState
import dev.marlonlom.apps.mocca.feats.calculator.utils.WindowSizeUtilityDefaults
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterial3WindowSizeClassApi
internal class TopContentSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldCheckResultsForEmptyUiState() {
    val amountExpectedValue = "0"
    composeTestRule.setContent {
      TopContentSlot(
        windowSizeInfo = WindowSizeUtilityDefaults.mobilePortrait,
        calculationTextState = mutableStateOf(amountExpectedValue),
        calculatorUiState = CalculatorUiState.Empty,
        onSlotClosedAction = {}
      )
    }

    composeTestRule
      .onNodeWithText(amountExpectedValue)
      .assertIsDisplayed()
  }

  @Test
  fun shouldCheckResultsForSuccessUiState() {
    val amountExpectedValue = "450000"
    composeTestRule.setContent {
      TopContentSlot(
        windowSizeInfo = WindowSizeUtilityDefaults.mobilePortrait,
        calculationTextState = mutableStateOf(amountExpectedValue),
        calculatorUiState = CalculatorUiState.WithSuccess(
          amount = amountExpectedValue,
          response = CalculationResult(
            variableFee = 18000.0,
            fixedFee = 0.0,
            total = 468000.0
          )
        ),
        onSlotClosedAction = {}
      )
    }

    composeTestRule
      .onNodeWithText(amountExpectedValue)
      .assertIsDisplayed()
    composeTestRule.onNodeWithText("Transferring fee").assertIsDisplayed()
    composeTestRule.onNodeWithText("\$ 18000").assertIsDisplayed()
    composeTestRule.onNodeWithText("Total to pay").assertIsDisplayed()
    composeTestRule.onNodeWithText("\$ 468000").assertIsDisplayed()
  }

  @Test
  fun shouldCheckErrorForBelowAmountException() {
    val amountExpectedValue = "123"
    composeTestRule.setContent {
      TopContentSlot(
        windowSizeInfo = WindowSizeUtilityDefaults.mobilePortrait,
        calculationTextState = mutableStateOf(amountExpectedValue),
        calculatorUiState = CalculatorUiState.WithFailure(
          amount = amountExpectedValue,
          exception = CalculationException.BelowQuantityRange()
        ),
        onSlotClosedAction = {},
      )
    }
    composeTestRule
      .onNodeWithText(amountExpectedValue)
      .assertIsDisplayed()
    composeTestRule
      .onNodeWithText("You cannot send less than \$5,000.")
      .assertIsDisplayed()
  }

  @Test
  fun shouldCheckErrorForAboveAmountException() {
    val amountExpectedValue = "99999999"
    composeTestRule.setContent {
      TopContentSlot(
        windowSizeInfo = WindowSizeUtilityDefaults.mobilePortrait,
        calculationTextState = mutableStateOf(amountExpectedValue),
        calculatorUiState = CalculatorUiState.WithFailure(
          amount = amountExpectedValue,
          exception = CalculationException.AboveQuantityRange()
        ),
        onSlotClosedAction = {}
      )
    }
    composeTestRule
      .onNodeWithText(amountExpectedValue)
      .assertIsDisplayed()
    composeTestRule
      .onNodeWithText("You cannot send more than \$3,000,000.")
      .assertIsDisplayed()
  }

  @Test
  fun shouldCheckErrorForNegativeAmountException() {
    val amountExpectedValue = "-1"
    composeTestRule.setContent {
      TopContentSlot(
        windowSizeInfo = WindowSizeUtilityDefaults.mobilePortrait,
        calculationTextState = mutableStateOf(amountExpectedValue),
        calculatorUiState = CalculatorUiState.WithFailure(
          amount = amountExpectedValue,
          exception = CalculationException.NegativeQuantity()
        ),
        onSlotClosedAction = {}
      )
    }
    composeTestRule
      .onNodeWithText(amountExpectedValue)
      .assertIsDisplayed()
    composeTestRule
      .onNodeWithText("You cannot send negative amounts.")
      .assertIsDisplayed()
  }
}
