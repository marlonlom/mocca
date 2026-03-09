/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.history.component.iconbutton

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteForever
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.marlonlom.mocca.mobile.calculator.history.R

/**
 * A composable function that displays a clear history icon button.
 *
 * @author marlonlom
 *
 * @param onButtonClicked Action invoked when the button is clicked.
 */
@Composable
internal fun ClearHistoryIconButton(onButtonClicked: () -> Unit) = IconButton(onClick = onButtonClicked) {
  Icon(
    imageVector = Icons.Rounded.DeleteForever,
    contentDescription = stringResource(R.string.text_delete_history),
    tint = MaterialTheme.colorScheme.primary,
  )
}
