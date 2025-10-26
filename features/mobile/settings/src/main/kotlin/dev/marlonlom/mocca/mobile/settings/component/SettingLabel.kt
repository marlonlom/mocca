/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.settings.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Displays a non-interactive label used to show a setting’s title and optional subtitle.
 *
 * @author marlonlom
 *
 * @param title The main text label to display.
 * @param modifier Modifier applied to the composable layout.
 * @param subtitle Optional secondary text shown below the title.
 * @param showSubtitle Whether to display the subtitle text.
 */
@Composable
fun SettingLabel(
  title: String,
  modifier: Modifier = Modifier,
  subtitle: String = "",
  showSubtitle: Boolean = subtitle.isNotEmpty(),
) = when {
  showSubtitle -> {
    Column(modifier = modifier) {
      Text(
        text = title,
        color = MaterialTheme.colorScheme.onSurface,
      )
      Text(
        text = subtitle,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.labelSmall,
      )
    }
  }

  else -> {
    Text(
      modifier = modifier,
      color = MaterialTheme.colorScheme.onSurface,
      text = title,
    )
  }
}
