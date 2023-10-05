/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

/**
 * Main top bar composable ui.
 *
 * @author marlonlom
 *
 * @param onNavigationIconClicked Action for navigation icon clicked.
 * @param onSettingsIconClicked Action for settings icon clicked.
 */
@ExperimentalMaterial3Api
@Composable
fun MainTopBar(
  navigationIconVisible: Boolean,
  onNavigationIconClicked: () -> Unit,
  onSettingsIconClicked: () -> Unit
) {
  TopAppBar(
    title = { },
    navigationIcon = {
      if (navigationIconVisible) {
        IconButton(onClick = { onNavigationIconClicked() }) {
          Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
        }
      }
    },
    actions = {
      if (!navigationIconVisible) {
        IconButton(onClick = { onSettingsIconClicked() }) {
          Icon(imageVector = Icons.Rounded.Settings, contentDescription = null)
        }
      }
    }
  )
}
