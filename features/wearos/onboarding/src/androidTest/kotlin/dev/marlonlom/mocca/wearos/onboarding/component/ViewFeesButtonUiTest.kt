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

internal class ViewFeesButtonUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayButtonThenCheckClicked() {
    with(rule) {
      var clicked = false
      setContent {
        ViewFeesButton(
          onClicked = { clicked = true },
        )
      }
      onNodeWithTag("view_fees_action_button").assertIsDisplayed()
      onNodeWithText("View fees").assertIsDisplayed().performClick()
      assertThat(clicked).isTrue()
    }
  }
}
