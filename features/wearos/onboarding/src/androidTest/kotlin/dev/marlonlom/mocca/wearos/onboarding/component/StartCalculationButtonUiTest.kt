/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.onboarding.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

internal class StartCalculationButtonUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayButtonThenCheckClicked() {
    with(rule) {
      var clicked = false
      setContent {
        StartCalculationButton(
          onClicked = { clicked = true },
        )
      }
      onNodeWithTag("start_calculation_action_button").assertIsDisplayed()
      onNodeWithText("Start calculation").assertIsDisplayed().performClick()
      assertThat(clicked).isTrue()
    }
  }
}
