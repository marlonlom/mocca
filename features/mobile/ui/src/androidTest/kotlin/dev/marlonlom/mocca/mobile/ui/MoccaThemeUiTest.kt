/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.mobile.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.marlonlom.mocca.mobile.ui.theme.MoccaTheme
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class MoccaThemeUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldApplyTheme() {
    with(composeTestRule) {
      setContent {
        MoccaTheme(
          darkTheme = false,
          dynamicColor = false
        ) {
          Text(
            modifier = Modifier.padding(2.dp),
            text = "MoccaTheme"
          )
        }
      }
      onNodeWithText("MoccaTheme").assertIsDisplayed()
    }
  }
}
