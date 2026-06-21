/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.fees.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.mocca.wearos.calculator.fees.domain.CalculatingFeesDomainData
import org.junit.Rule
import org.junit.Test

internal class CalculationFeeListItemUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayListItemScreenWithFixedFee() {
    with(rule) {
      setContent {
        CalculationFeeListItem(
          position = 1,
          domainItem = CalculatingFeesDomainData(
            min = 5000.0,
            max = 50000.0,
            fixedFee = 4700.0,
            haveVariableFee = false,
            variableFeeFactor = 0.0,
          ),
        )
      }
      onNodeWithText("4%").assertIsNotDisplayed()
      onNodeWithText("$4.700").assertIsNotDisplayed()
      onNodeWithText("From $5K to $50K").assertIsDisplayed()
    }
  }

  @Test
  fun shouldDisplayListItemScreenWithVariableFee() {
    with(rule) {
      setContent {
        CalculationFeeListItem(
          position = 1,
          domainItem = CalculatingFeesDomainData(
            min = 200_001.0,
            max = 3_000_000.0,
            fixedFee = 0.0,
            haveVariableFee = true,
            variableFeeFactor = 4.0,
          ),
        )
      }

      onNodeWithText("4%").assertIsDisplayed()
      onNodeWithText("From $200K to $3M").assertIsDisplayed()
    }
  }
}
