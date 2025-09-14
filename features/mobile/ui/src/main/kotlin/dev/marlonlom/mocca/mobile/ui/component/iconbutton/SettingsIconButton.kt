/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.component.iconbutton

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.marlonlom.mocca.mobile.ui.R

/**
 * A composable function that displays a settings icon button.
 *
 * @author marlonlom
 *
 * @param onButtonClicked Action invoked when the button is clicked.
 */
@Composable
fun SettingsIconButton(onButtonClicked: () -> Unit) = IconButton(onClick = onButtonClicked) {
  Icon(
    imageVector = Icons.Rounded.Settings,
    contentDescription = stringResource(R.string.text_settings),
    tint = MaterialTheme.colorScheme.primary,
  )
}
