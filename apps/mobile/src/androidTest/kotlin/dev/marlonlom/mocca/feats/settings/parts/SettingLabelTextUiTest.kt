/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.feats.settings.parts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.mocca.BuildConfig
import dev.marlonlom.mocca.R
import org.junit.Rule
import org.junit.Test

internal class SettingLabelTextUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayLabelWithTitleOnly() {
    with(composeTestRule) {
      setContent {
        SettingLabelText(
          title = R.string.text_settings_label_feedback,
        )
      }
      onNodeWithText("Feedback").assertIsDisplayed()
    }
  }

  @Test
  fun shouldDisplayLabelWithTitleAndSubtitle() {
    with(composeTestRule) {
      setContent {
        SettingLabelText(
          title = R.string.text_settings_label_app_version,
          subtitle = BuildConfig.VERSION_NAME
        )
      }

      onNodeWithText("Version").assertIsDisplayed()
      onNodeWithText(BuildConfig.VERSION_NAME).assertIsDisplayed()
    }
  }

}
