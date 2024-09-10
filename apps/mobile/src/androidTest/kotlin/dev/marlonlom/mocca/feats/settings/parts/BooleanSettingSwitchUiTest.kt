/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.feats.settings.parts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.mocca.R
import org.junit.Rule
import org.junit.Test

internal class BooleanSettingSwitchUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplaySwitchWithTitleOnly() {
    var checked = false
    with(composeTestRule) {
      setContent {
        BooleanSettingSwitch(
          title = R.string.text_settings_label_dark_theme,
          subtitle = R.string.text_settings_hint_system_dark_theme,
          checked = checked,
          onChecked = {
            checked = it
          },
          showSubtitle = false,
        )
      }

      onNodeWithText("Dark theme").assertIsDisplayed()
      onNodeWithTag("BooleanSettingSwitch").assertIsDisplayed().performClick()
      assertThat(checked).isTrue()
    }
  }

  @Test
  fun shouldDisplaySwitchWithTitleAndSubtitle() {
    var checked = true
    with(composeTestRule) {
      setContent {
        BooleanSettingSwitch(
          title = R.string.text_settings_label_dark_theme,
          subtitle = R.string.text_settings_hint_system_dark_theme,
          checked = checked,
          onChecked = {
            checked = it
          },
        )
      }

      onNodeWithText("Dark theme")
        .assertIsDisplayed()
      onNodeWithText("Disabled when applied by the os.")
        .assertIsDisplayed()
      onNodeWithTag("BooleanSettingSwitch")
        .assertIsDisplayed().performClick()
      assertThat(checked).isFalse()
    }
  }
}
