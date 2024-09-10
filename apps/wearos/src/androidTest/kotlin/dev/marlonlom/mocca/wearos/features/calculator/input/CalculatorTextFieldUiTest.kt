/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.features.calculator.input

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

internal class CalculatorTextFieldUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayDefaultFieldValue() {
    with(rule) {
      val defaultValue = "0"
      setContent {
        CalculatorTextField(defaultValue)
      }

      onNodeWithText("COP \$ ").assertIsDisplayed()
      onNodeWithText(defaultValue).assertIsDisplayed()
    }
  }
}
