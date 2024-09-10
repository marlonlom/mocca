/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.feats.settings.slots

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.marlonlom.mocca.R
import org.junit.Rule
import org.junit.Test

internal class DefaultSettingsSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplayDefaultSettingSlot() {
    with(composeTestRule) {
      setContent {
        DefaultSettingsSlot(
          title = R.string.text_settings_label_feedback,
          content = {},
        )
      }

      onNodeWithText("Feedback").assertIsDisplayed()
    }
  }
}
