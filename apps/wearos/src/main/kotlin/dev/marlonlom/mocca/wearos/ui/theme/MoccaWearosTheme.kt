/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material3.MaterialTheme

/**
 * Application theme composable function.
 *
 * @author marlonlom
 *
 * @param content Application context.
 */
@Composable
fun MoccaWearosTheme(content: @Composable () -> Unit) = MaterialTheme(
  colorScheme = MoccaColorSchemes.colorScheme,
  typography = MoccaTypography.appTypography,
  content = content,
)
