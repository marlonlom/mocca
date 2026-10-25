/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.fees.layout

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.mocca.mobile.calculator.fees.R
import dev.marlonlom.mocca.mobile.calculator.fees.domain.SuccessfulFeesDomainData
import dev.marlonlom.mocca.mobile.calculator.fees.util.InstrumentationUtil
import dev.marlonlom.mocca.mobile.ui.window.MobileWindowSize
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class PortraitHistoryUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayPortraitHistoryEmptyFees() {
    rule.setContent {
      PortraitHistory(
        feesData = { emptyList() },
        mobileWindowSize = MobileWindowSize.MOBILE_PORTRAIT,
        showCloseButton = false,
        onCloseButtonClicked = {},
      )
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

  @Test
  fun shouldDisplayPortraitHistoryNonEmptyFees() {
    val domainData = SuccessfulFeesDomainData(
      min = 5_000.0,
      max = 50_000.0,
      fixedFee = 4_700.0,
      haveVariableFee = false,
      variableFeeFactor = 0.0,
    )
    rule.setContent {
      PortraitHistory(
        feesData = { listOf(domainData) },
        mobileWindowSize = MobileWindowSize.MOBILE_LANDSCAPE,
        showCloseButton = true,
        onCloseButtonClicked = {},
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
}
