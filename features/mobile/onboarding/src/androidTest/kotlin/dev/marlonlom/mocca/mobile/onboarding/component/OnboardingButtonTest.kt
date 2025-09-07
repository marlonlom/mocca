/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.mobile.onboarding.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
internal class OnboardingButtonTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun given_onboarding_button_composable_when_clicked_then_onboarded_callback_is_invoked() {
    with(composeTestRule) {
      var onboarded = false
      setContent {
        OnboardingButton(onButtonClicked = { onboarded = true })
      }
      onNodeWithTag("onboarding_btn").assertIsDisplayed().performClick()
      assertThat(onboarded).isTrue()
    }
  }
}
