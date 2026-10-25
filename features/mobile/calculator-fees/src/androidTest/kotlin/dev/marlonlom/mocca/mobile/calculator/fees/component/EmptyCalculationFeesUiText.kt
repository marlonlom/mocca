/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.fees.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.mocca.mobile.calculator.fees.R
import dev.marlonlom.mocca.mobile.calculator.fees.util.InstrumentationUtil
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class EmptyCalculationFeesUiText {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayEmptyCalculationFees() {
    rule.setContent {
      EmptyCalculationFees()
    }
    rule.onNodeWithText(
      text = InstrumentationUtil.getStringRes(R.string.text_empty_fees_title),
      useUnmergedTree = true,
    ).assertIsDisplayed()
    rule.onNodeWithText(
      text = InstrumentationUtil.getStringRes(R.string.text_empty_fees_detail),
      useUnmergedTree = true,
    ).assertIsDisplayed()
  }
}
