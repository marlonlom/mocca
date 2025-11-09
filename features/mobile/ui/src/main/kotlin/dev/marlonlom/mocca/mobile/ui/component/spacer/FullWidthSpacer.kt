/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.component.spacer

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Adds a spacer that fills the full available width within a [ColumnScope].
 *
 * @author marlonlom
 */
@Composable
fun ColumnScope.FullWidthSpacer() = Spacer(
  modifier = Modifier
    .fillMaxWidth()
    .weight(1.0f),
)
