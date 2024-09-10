/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.feats.settings.slots

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth
import dev.marlonlom.mocca.BuildConfig
import dev.marlonlom.mocca.feats.settings.UserPreferences
import org.junit.Rule
import org.junit.Test

internal class LegalNotesSettingsSlotUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldDisplaySlotAndClickOssLicencesButton() {
    var settingClicked = false
    with(composeTestRule) {
      setContent {
        LegalNotesSettingsSlot(
          userPreferences = UserPreferences(
            aboutEfectyUrl = "",
            appVersion = BuildConfig.VERSION_NAME,
            darkTheme = false,
            dynamicColors = false,
          ),
          onOssLicencesSettingLinkClicked = {
            settingClicked = true
          },
        )
      }

      onNodeWithText("Legal notes").assertIsDisplayed()
      onNodeWithText(text = "Third party licenses").assertIsDisplayed()
      onNodeWithTag("ClickableTextRow").assertIsDisplayed().performClick()
      Truth.assertThat(settingClicked).isTrue()
    }
  }
}
