/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.settings.parts

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.test.BuildConfig
import org.junit.Rule
import org.junit.Test

internal class ClickableTextRowUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplaySwitchWithTitleOnly() {
    var clicked = false
    composeTestRule.setContent {
      ClickableTextRow(
        title = R.string.text_settings_label_feedback,
        onClicked = {
          clicked = true
        }
      )
    }

    val nodeWithText = composeTestRule.onNodeWithText("Feedback")
    nodeWithText.assertIsDisplayed()
    nodeWithText.onParent().performClick()
    Truth.assertThat(clicked).isTrue()
  }

  @Test
  fun shouldDisplaySwitchWithTitleAndSubtitle() {
    var clicked = false
    composeTestRule.setContent {
      ClickableTextRow(
        title = R.string.text_settings_label_app_version,
        subtitle = BuildConfig.BUILD_TYPE,
        onClicked = {
          clicked = true
        }
      )
    }

    val nodeWithText = composeTestRule.onNodeWithText("Version\n${BuildConfig.BUILD_TYPE}")
    nodeWithText.assertIsDisplayed()
    nodeWithText.onParent().performClick()
    Truth.assertThat(clicked).isTrue()
  }

}
