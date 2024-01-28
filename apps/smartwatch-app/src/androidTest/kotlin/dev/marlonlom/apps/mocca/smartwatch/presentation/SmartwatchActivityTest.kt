/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.smartwatch.presentation

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marlonlom.apps.mocca.smartwatch.presentation.feats.main.WearAppContent
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class SmartwatchActivityTest {

  @get:Rule
  var rule: AndroidComposeTestRule<ActivityScenarioRule<SmartwatchActivity>, SmartwatchActivity> =
    createAndroidComposeRule()

  @Test
  fun shouldDisplayActivityContents() {
    with(rule) {
      activity.setContent {
        WearAppContent()
      }
      onNodeWithText("Money amount").assertIsDisplayed()
    }
  }
}
