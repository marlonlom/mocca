/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.wearos.presentation.theme

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
    colors = BrandColors.wearColorPalette,
    typography = BrandTypography.appTypography,
    content = content
  )
}
