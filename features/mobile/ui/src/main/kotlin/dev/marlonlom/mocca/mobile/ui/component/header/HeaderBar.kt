/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.component.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Displays a top app header bar with optional navigation and action content.
 *
 * @author marlonlom
 *
 * @param title Composable content shown as the title.
 * @param isMedium Whether to use a medium-style header bar.
 * @param navigationIcon Composable for the navigation icon (e.g., back button).
 * @param navigationActions Composables for action items on the right side.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderBar(
  title: @Composable (() -> Unit),
  isMedium: Boolean = false,
  navigationIcon: @Composable (() -> Unit) = {},
  navigationActions: @Composable (RowScope.() -> Unit) = {},
) = if (isMedium) {
  MediumTopAppBar(
    modifier = Modifier
      .fillMaxWidth()
      .consumeWindowInsets(WindowInsets.systemBars),
    title = title,
    navigationIcon = navigationIcon,
    actions = navigationActions,
    expandedHeight = 128.dp,
  )
} else {
  TopAppBar(
    modifier = Modifier
      .fillMaxWidth()
      .consumeWindowInsets(WindowInsets.systemBars),
    title = title,
    navigationIcon = navigationIcon,
    actions = navigationActions,
  )
}
