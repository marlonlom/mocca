/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator.buttons

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test

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
        ButtonsSection(
          numberTypingEnabledState = numberTypingEnabledState,
          onPerformCalculationAction = { },
          onDeleteLastNumberAction = {},
          onAppendNumberAction = {
            numberText.append(it)
          }
        )
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
        ButtonsSection(
          numberTypingEnabledState = numberTypingEnabledState,
          onPerformCalculationAction = { },
          onDeleteLastNumberAction = {
            numberText.deleteCharAt(numberText.length - 1)
          },
          onAppendNumberAction = {
            numberText.append(it)
          }
        )
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
        ButtonsSection(
          numberTypingEnabledState = numberTypingEnabledState,
          onPerformCalculationAction = {
            calculationPerformed = true
          },
          onDeleteLastNumberAction = {
            numberText.deleteCharAt(numberText.length - 1)
          },
          onAppendNumberAction = {
            numberText.append(it)
          }
        )
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
