/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.onboarding.layout

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
internal class OnboardingRowContentTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun givenOnboardingScreen_whenDisplayed_thenCorrectContentIsVisible() {
    with(composeTestRule) {
      setContent {
        OnboardingRowContent(onOnboarded = { })
      }
      onNodeWithTag("onboarding_img").assertIsDisplayed()
      onNodeWithTag("onboarding_title").assertIsDisplayed()
      onNodeWithTag("onboarding_desc").assertIsDisplayed()
      onNodeWithTag("onboarding_btn").assertIsDisplayed()
    }
  }

  @Test
  fun givenOnboardingScreen_whenButtonClicked_thenOnOnboardedIsInvoked() {
    with(composeTestRule) {
      var onboarded = false
      setContent {
        OnboardingRowContent(onOnboarded = { onboarded = true })
      }
      onNodeWithTag("onboarding_img").assertIsDisplayed()
      onNodeWithTag("onboarding_title").assertIsDisplayed()
      onNodeWithTag("onboarding_desc").assertIsDisplayed()
      onNodeWithTag("onboarding_btn").assertIsDisplayed().performClick()
      assertThat(onboarded).isTrue()
    }
  }
}
