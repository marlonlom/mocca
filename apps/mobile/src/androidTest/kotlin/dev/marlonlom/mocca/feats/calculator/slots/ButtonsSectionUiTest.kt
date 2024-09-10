/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.feats.calculator.slots

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth
import dev.marlonlom.mocca.feats.calculator.utils.WindowSizeUtilityDefaults
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterial3WindowSizeClassApi
internal class ButtonsSectionUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldAddAmountByClickingNumberButtons() {
    val numberTypingEnabledState = mutableStateOf(true)
    val expectedAmount = "15000"
    val numberText = StringBuilder()

    with(composeTestRule) {
      setContent {
        ButtonsContentSlot(
          windowSizeInfo = WindowSizeUtilityDefaults.mobilePortrait,
          numberTypingEnabledState = numberTypingEnabledState,
          onPerformCalculationAction = { },
          onDeleteLastNumberAction = {},
        ) {
          numberText.append(it)
        }
      }

      expectedAmount.split("")
        .filterNot { it.isEmpty() }
        .forEach {
          onNodeWithText(it).assertIsDisplayed().performClick()
        }

      Truth.assertThat(numberText.toString()).isEqualTo(expectedAmount)
    }
  }

  @Test
  fun shouldAddAmountByClickingNumberButtonsThenDeleteTwoDigits() {
    val numberTypingEnabledState = mutableStateOf(true)
    val expectedAmount = "250500"
    val numberText = StringBuilder()

    with(composeTestRule) {
      setContent {
        ButtonsContentSlot(
          windowSizeInfo = WindowSizeUtilityDefaults.mobilePortrait,
          numberTypingEnabledState = numberTypingEnabledState,
          onPerformCalculationAction = { },
          onDeleteLastNumberAction = {
            numberText.deleteCharAt(numberText.length - 1)
          },
        ) {
          numberText.append(it)
        }
      }

      expectedAmount.split("")
        .filterNot { it.isEmpty() }
        .forEach {
          onNodeWithText(it).assertIsDisplayed().performClick()
        }
      onNodeWithText("⌫").assertIsDisplayed().performClick().performClick()

      Truth.assertThat(numberText.toString()).isEqualTo("2505")
    }
  }

  @Test
  fun shouldCheckAmountIsAddedAndCalculationIsPerformed() {
    val numberTypingEnabledState = mutableStateOf(true)
    val expectedAmount = "3550400"
    var calculationPerformed = false
    val numberText = StringBuilder()

    with(composeTestRule) {
      setContent {
        ButtonsContentSlot(
          windowSizeInfo = WindowSizeUtilityDefaults.mobilePortrait,
          numberTypingEnabledState = numberTypingEnabledState,
          onPerformCalculationAction = {
            calculationPerformed = true
          },
          onDeleteLastNumberAction = {
            numberText.deleteCharAt(numberText.length - 1)
          },
        ) {
          numberText.append(it)
        }
      }

      expectedAmount.split("").filterNot { it.isEmpty() }.forEach {
        onNodeWithText(it).assertIsDisplayed().performClick()
      }
      onNodeWithText("⌫").assertIsDisplayed().performClick().performClick()
      onNodeWithText("✔").assertIsDisplayed().performClick()

      Truth.assertThat(numberText.toString()).isEqualTo("35504")
      Truth.assertThat(calculationPerformed).isTrue()
    }
  }
}
