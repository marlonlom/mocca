/*
 * Copyright 2026 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.wearos.calculator.fees

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

internal class CalculatorFeesListScreenUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayListScreen() {
    with(rule) {
      setContent {
        CalculatorFeesListScreen(
          onBackNavigationAction = {},
        )
      }
      onNodeWithText("Fees (COP)").assertExists()
    }
  }

}
