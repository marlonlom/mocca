/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.smartwatch.presentation.feats.calculator_input

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

internal class InputMoneyAmountTitleUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayInputMoneyAmountTitle() {
    with(rule) {

      setContent {
        InputMoneyAmountTitle()
      }

      onNodeWithText("Money amount").assertIsDisplayed()
    }
  }

}
