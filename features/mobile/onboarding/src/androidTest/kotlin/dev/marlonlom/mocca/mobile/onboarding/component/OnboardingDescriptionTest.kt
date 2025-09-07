/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.onboarding.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
internal class OnboardingDescriptionTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun given_onboarding_description_composable_when_displayed_then_description_is_visible() {
    with(composeTestRule) {
      setContent {
        OnboardingDescription()
      }
      onNodeWithTag("onboarding_desc").assertIsDisplayed()
    }
  }
}
