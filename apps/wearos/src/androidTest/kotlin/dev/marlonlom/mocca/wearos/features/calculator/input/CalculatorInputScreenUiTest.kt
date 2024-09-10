/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.wearos.features.input

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.mocca.wearos.features.calculator.input.CalculatorInputScreen
import org.junit.Rule
import org.junit.Test

internal class CalculatorInputScreenUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayNumberReadyForCalculation() {
    with(rule) {
      var updatedTxt = ""
      setContent {
        CalculatorInputScreen(
          onCalculationReadyAction = { txt ->
            updatedTxt = txt
          },
        )
      }

      onNodeWithText("1").performClick()
      onNodeWithText("2").performClick()
      onNodeWithText("3").performClick()
      onNodeWithText("4").performClick()
      onNodeWithText("5").performClick()
      onNodeWithText("6").performClick()
      onNodeWithText("⌫").performClick()
      onNodeWithText("✔").performClick()

      assertThat(updatedTxt).isEqualTo("12345")
    }
  }
}
