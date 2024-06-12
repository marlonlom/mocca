/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.feats.settings.slots

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth
import dev.marlonlom.mocca.BuildConfig
import dev.marlonlom.mocca.feats.settings.UserPreferences
import org.junit.Rule
import org.junit.Test

internal class AboutSettingsSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplaySlotAndClickFeedbackButton() {
    var isFeedbackSettingClicked = false
    with(composeTestRule) {
      setContent {
        AboutSettingsSlot(
          userPreferences = UserPreferences(
            aboutEfectyUrl = "",
            appVersion = BuildConfig.VERSION_NAME,
            darkTheme = false,
            dynamicColors = false
          ),
          onFeedbackSettingLinkClicked = {
            isFeedbackSettingClicked = true
          }
        )
      }

      onNodeWithText("About the app").assertIsDisplayed()
      onNodeWithText("Version").assertIsDisplayed()
      onNodeWithText(BuildConfig.VERSION_NAME).assertIsDisplayed()
      onNodeWithText("Feedback").assertIsDisplayed().onParent().performClick()
      Truth.assertThat(isFeedbackSettingClicked).isTrue()
    }
  }
}
