/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.smartwatch.presentation.feats.calculator_input

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth
import org.junit.Rule
import org.junit.Test

internal class PerformCalculationButtonUiTest {
  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayInputMoneyAmountTitle() {
    with(rule) {
      val moneyInputState = mutableStateOf("12345")
      setContent {
        PerformCalculationButton(moneyInputState) {
          Truth.assertThat(it).isEqualTo("12345")
        }
      }

      val button = onNodeWithTag("PerformCalculationButton")
      button.performClick()
      button.assertIsDisplayed()
    }
  }
}
