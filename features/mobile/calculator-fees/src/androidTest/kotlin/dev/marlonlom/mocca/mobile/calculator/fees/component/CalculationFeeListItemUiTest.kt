/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.fees.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.mocca.mobile.calculator.fees.R
import dev.marlonlom.mocca.mobile.calculator.fees.domain.SuccessfulFeesDomainData
import dev.marlonlom.mocca.mobile.calculator.fees.util.InstrumentationUtil
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CalculationFeeListItemUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayFixedFeeListItem() {
    val domainData = SuccessfulFeesDomainData(
      min = 5_000.0,
      max = 50_000.0,
      fixedFee = 4_700.0,
      haveVariableFee = false,
      variableFeeFactor = 0.0,
    )
    rule.setContent {
      CalculationFeeListItem(
        itemPosition = 1,
        domainItem = domainData,
      )
    }
    rule.onNodeWithText(
      text = InstrumentationUtil.getStringRes(
        R.string.text_price_range,
        domainData.min,
        domainData.max,
      ),
      useUnmergedTree = true,
    ).assertIsDisplayed()
    rule.onNodeWithText(
      text = InstrumentationUtil.getStringRes(
        R.string.text_fee_amount,
        domainData.fixedFee,
      ),
      useUnmergedTree = true,
    ).assertIsDisplayed()
  }

  @Test
  fun shouldDisplayVariableFeeListItem() {
    val domainData = SuccessfulFeesDomainData(
      min = 200_001.0,
      max = 3_000_000.0,
      fixedFee = 0.0,
      haveVariableFee = true,
      variableFeeFactor = 4.0,
    )
    rule.setContent {
      CalculationFeeListItem(
        itemPosition = 1,
        domainItem = domainData,
      )
    }
    rule.onNodeWithText(
      text = InstrumentationUtil.getStringRes(
        R.string.text_price_range,
        domainData.min,
        domainData.max,
      ),
      useUnmergedTree = true,
    ).assertIsDisplayed()
    rule.onNodeWithText(
      text = InstrumentationUtil.getStringRes(
        R.string.text_fee_percentage,
        domainData.variableFeeFactor,
      ),
      useUnmergedTree = true,
    ).assertIsDisplayed()
  }
}
