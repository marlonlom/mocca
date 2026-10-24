/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.component

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
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
internal class CalculationOutputCloseButtonUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayFailureOutputButton() {
    val failedResult = CalculatorOutputState.WithFailure(
      amount = "1500",
      exception = CalculationException.NegativeQuantity(),
    )
    var buttonClicked = false
    rule.setContent {
      CalculationOutputCloseButton(
        uiState = failedResult,
        buttonEnabled = true,
        onButtonClicked = {
          buttonClicked = true
        },
      )
    }

    rule.onNodeWithText(
      text = getStringRes(R.string.text_close_failed),
      substring = true,
      useUnmergedTree = true,
    ).assertIsDisplayed()
    rule.onNodeWithTag("calc_output_close_btn").assertIsDisplayed().performClick()

    assertThat(buttonClicked).isTrue()
  }

  @Test
  fun shouldDisplaySuccessOutputButton() {
    val successResult = WithSuccess(
      amount = "5000",
      response = CalculationResult(
        total = 9_700.0,
        fixedFee = 4_700.0,
        variableFee = 0.0,
      ),
    )
    var buttonClicked = false
    rule.setContent {
      CalculationOutputCloseButton(
        uiState = successResult,
        buttonEnabled = true,
        onButtonClicked = {
          buttonClicked = true
        },
      )
    }

    rule.onNodeWithText(text = getStringRes(R.string.text_close_success), useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithTag("calc_output_close_btn").assertIsDisplayed().performClick()

    assertThat(buttonClicked).isTrue()
  }

  private fun getStringRes(@StringRes resId: Int): String =
    InstrumentationRegistry.getInstrumentation().targetContext.getString(resId)
}
