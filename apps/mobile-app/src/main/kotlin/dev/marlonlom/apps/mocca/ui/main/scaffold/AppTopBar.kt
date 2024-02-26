/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.main.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
fun AppTopBar(
  navigationIconVisible: Boolean,
  onNavigationIconClicked: () -> Unit,
  onSettingsIconClicked: () -> Unit
) {
  TopAppBar(
    title = { },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.surface,
      navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
      actionIconContentColor = MaterialTheme.colorScheme.onSurface,
    ),
    navigationIcon = {
      if (navigationIconVisible) {
        IconButton(onClick = { onNavigationIconClicked() }) {
          Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
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
