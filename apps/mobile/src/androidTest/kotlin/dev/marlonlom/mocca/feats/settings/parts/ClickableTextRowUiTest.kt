/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.feats.settings.parts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth
import dev.marlonlom.mocca.R
import dev.marlonlom.mocca.test.BuildConfig
import org.junit.Rule
import org.junit.Test

internal class ClickableTextRowUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplaySwitchWithTitleOnly() {
    var clicked = false
    with(composeTestRule) {
      setContent {
        ClickableTextRow(
          title = R.string.text_settings_label_feedback,
          onClicked = {
            clicked = true
          }
        )
      }
      val nodeWithText = onNodeWithText("Feedback")
      nodeWithText.assertIsDisplayed()
      nodeWithText.onParent().performClick()
      Truth.assertThat(clicked).isTrue()
    }
  }

  @Test
  fun shouldDisplaySwitchWithTitleAndSubtitle() {
    var clicked = false
    with(composeTestRule) {
      setContent {
        ClickableTextRow(
          title = R.string.text_settings_label_app_version,
          subtitle = BuildConfig.BUILD_TYPE,
          onClicked = {
            clicked = true
          }
        )
      }

      onNodeWithText("Version").assertIsDisplayed()
      onNodeWithText(BuildConfig.BUILD_TYPE).assertIsDisplayed()
      onNodeWithTag("ClickableTextRow").performClick()
      Truth.assertThat(clicked).isTrue()
    }
  }

}
