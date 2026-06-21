/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.onboarding

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import com.google.common.truth.Truth.assertThat
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class OnboardingScreenUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayScreenThenClickedStartCalculationButton() {
    with(rule) {
      var clicked = false
      setContent {
        OnboardingScreen(
          listState = rememberScalingLazyListState(),
          onCalculateClick = { clicked = true },
          onViewFeesClick = { },
        )
      }
      onNodeWithTag("start_calculation_action_button").assertIsDisplayed()
      onNodeWithTag("view_fees_action_button").assertIsDisplayed()
      onNodeWithText("Start calculation").assertIsDisplayed().performClick()
      onNodeWithText("View fees").assertIsDisplayed()
      assertThat(clicked).isTrue()
    }
  }

  @Test
  fun shouldDisplayScreenThenClickedViewFeesButton() {
    with(rule) {
      var clicked = false
      setContent {
        OnboardingScreen(
          listState = rememberScalingLazyListState(),
          onCalculateClick = { },
          onViewFeesClick = { clicked = true },
        )
      }
      onNodeWithText("Start calculation").assertIsDisplayed()
      onNodeWithText("View fees").assertIsDisplayed().performClick()
      assertThat(clicked).isTrue()
    }
  }
}
