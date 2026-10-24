/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.slot

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import dev.marlonlom.mocca.calculator.model.CalculationResult
import dev.marlonlom.mocca.mobile.calculator.output.R
import dev.marlonlom.mocca.mobile.calculator.output.domain.CalculatorOutputState.WithSuccess
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CalculationSuccessDetailsSlotUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplaySuccessDetailsSlot() {
    val successResult = WithSuccess(
      amount = "5000",
      response = CalculationResult(
        total = 9_700.0,
        fixedFee = 4_700.0,
        variableFee = 0.0,
      ),
    )
    rule.setContent { CalculationSuccessDetailsSlot(uiState = successResult) }
    rule.onNodeWithText(text = getStringRes(R.string.text_label_requested_amount)).assertIsDisplayed()
    rule.onNodeWithText(text = "cop $ 5,000", useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_label_fee)).assertIsDisplayed()
    rule.onNodeWithText(text = "cop $ 4,700", useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_label_total)).assertIsDisplayed()
    rule.onNodeWithText(text = "cop $ 9,700", useUnmergedTree = true).assertIsDisplayed()
  }

  private fun getStringRes(@StringRes resId: Int): String =
    InstrumentationRegistry.getInstrumentation().targetContext.getString(resId)
}
