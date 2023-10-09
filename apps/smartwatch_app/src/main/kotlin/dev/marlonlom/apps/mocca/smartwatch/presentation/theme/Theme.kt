/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.smartwatch.presentation.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material.MaterialTheme

/**
 * Application theme composable function.
 *
 * @author marlonlom
 *
 * @param content Application context.
 */
@Composable
fun MoccaTheme(
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colors = wearColorPalette,
    typography = Typography,
    // For shapes, we generally recommend using the default Material Wear shapes which are
    // optimized for round and non-round devices.
    content = content
  )
}
