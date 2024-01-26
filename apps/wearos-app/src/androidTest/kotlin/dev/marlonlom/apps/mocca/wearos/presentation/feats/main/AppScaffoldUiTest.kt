/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.wearos.presentation.feats.main

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Text
import org.junit.Rule
import org.junit.Test

internal class AppScaffoldUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldDisplayScaffold() {
    with(rule) {
      setContent {
        AppScaffold(listState = rememberScalingLazyListState()) {
          Text(text = "Hello world")
        }
      }
      onNodeWithText("Hello world").assertIsDisplayed()
    }
  }

}
