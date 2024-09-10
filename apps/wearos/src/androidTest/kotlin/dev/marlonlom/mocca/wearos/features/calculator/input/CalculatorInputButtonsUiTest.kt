/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.features.calculator.input

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

internal class CalculatorInputButtonsUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayNumberAfterClickThreeDigits() {
    with(rule) {
      var updatedTxt = ""
      setContent {
        val uiState = rememberCalculatorInputState()
        CalculatorInputButtons(onButtonClick = { txt ->
          uiState.update(txt)
          updatedTxt = uiState.textValue
        })
      }

      onNodeWithText("1").performClick()
      onNodeWithText("2").performClick()
      onNodeWithText("3").performClick()

      assertThat(updatedTxt).isEqualTo("123")
    }
  }

  @Test
  fun shouldDisplayUpdatedNumberAfterClickSixDigitsThenDeletingTwo() {
    with(rule) {
      var updatedTxt = ""
      setContent {
        val uiState = rememberCalculatorInputState()
        CalculatorInputButtons(onButtonClick = { txt ->
          uiState.update(txt)
          updatedTxt = uiState.textValue
        })
      }

      onNodeWithText("1").performClick()
      onNodeWithText("2").performClick()
      onNodeWithText("0").performClick()
      onNodeWithText("2").performClick()
      onNodeWithText("2").performClick()
      onNodeWithText("5").performClick()
      onNodeWithText("⌫").performClick()
      onNodeWithText("⌫").performClick()

      assertThat(updatedTxt).isEqualTo("1202")
    }
  }
}
