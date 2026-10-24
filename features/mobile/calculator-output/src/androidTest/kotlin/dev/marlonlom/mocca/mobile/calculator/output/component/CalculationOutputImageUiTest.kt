/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import dev.marlonlom.mocca.calculator.model.CalculationException
import dev.marlonlom.mocca.calculator.model.CalculationResult
import dev.marlonlom.mocca.mobile.calculator.output.domain.CalculatorOutputState
import dev.marlonlom.mocca.mobile.calculator.output.domain.CalculatorOutputState.WithSuccess
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CalculationOutputImageUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayFailureImage() {
    val failedResult = CalculatorOutputState.WithFailure(
      amount = "1500",
      exception = CalculationException.NegativeQuantity(),
    )
    rule.setContent { CalculationOutputImage(uiState = failedResult) }
    rule.onNodeWithTag(testTag = "calculation_failed_img").assertIsDisplayed()
  }

  @Test
  fun shouldDisplayPendingImage() {
    val emptyResult = CalculatorOutputState.Empty
    rule.setContent { CalculationOutputImage(uiState = emptyResult) }
    rule.onNodeWithTag(testTag = "calculation_pending_img").assertIsDisplayed()
  }

  @Test
  fun shouldDisplaySuccessImage() {
    val successResult = WithSuccess(
      amount = "5000",
      response = CalculationResult(
        total = 9_700.0,
        fixedFee = 4_700.0,
        variableFee = 0.0,
      ),
    )
    rule.setContent { CalculationOutputImage(uiState = successResult) }
    rule.onNodeWithTag(testTag = "calculation_success_img").assertIsDisplayed()
  }
}
