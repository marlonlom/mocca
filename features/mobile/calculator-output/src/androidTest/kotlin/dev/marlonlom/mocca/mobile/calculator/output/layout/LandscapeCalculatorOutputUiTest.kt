/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.layout

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import dev.marlonlom.mocca.calculator.model.CalculationException
import dev.marlonlom.mocca.calculator.model.CalculationResult
import dev.marlonlom.mocca.mobile.calculator.output.R
import dev.marlonlom.mocca.mobile.calculator.output.domain.CalculatorOutputState
import dev.marlonlom.mocca.mobile.calculator.output.domain.CalculatorOutputState.WithSuccess
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class LandscapeCalculatorOutputUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayFailureLandscapeOutput() {
    val failedResult = CalculatorOutputState.WithFailure(
      amount = "1500",
      exception = CalculationException.NegativeQuantity(),
    )
    rule.setContent { LandscapeCalculatorOutput(calculationState = failedResult, onCloseButtonClicked = {}) }
    rule.onNodeWithText(text = getStringRes(R.string.text_calculation_failed)).assertIsDisplayed()
    rule.onNodeWithTag(testTag = "calculation_failed_img").assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_negative_amount_error)).assertIsDisplayed()
  }

  @Test
  fun shouldDisplayPendingLandscapeOutput() {
    val emptyResult = CalculatorOutputState.Empty
    rule.setContent { LandscapeCalculatorOutput(calculationState = emptyResult, onCloseButtonClicked = {}) }
    rule.onNodeWithText(text = getStringRes(R.string.text_perform_calculation)).assertIsDisplayed()
    rule.onNodeWithTag(testTag = "calculation_pending_img").assertIsDisplayed()
  }

  @Test
  fun shouldDisplaySuccessLandscapeOutput() {
    val successResult = WithSuccess(
      amount = "5000",
      response = CalculationResult(
        total = 9_700.0,
        fixedFee = 4_700.0,
        variableFee = 0.0,
      ),
    )
    rule.setContent { LandscapeCalculatorOutput(calculationState = successResult, onCloseButtonClicked = {}) }
    rule.onNodeWithText(text = getStringRes(R.string.text_calculation_successful), useUnmergedTree = true)
      .assertIsDisplayed()
    rule.onNodeWithTag(testTag = "calculation_success_img").assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_label_requested_amount), useUnmergedTree = true)
      .assertIsDisplayed()
    rule.onNodeWithText(text = "cop $ 5,000", useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_label_fee), useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithText(text = "cop $ 4,700", useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_label_total), useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithText(text = "cop $ 9,700", useUnmergedTree = true).assertIsDisplayed()
  }

  private fun getStringRes(@StringRes resId: Int): String =
    InstrumentationRegistry.getInstrumentation().targetContext.getString(resId)
}
