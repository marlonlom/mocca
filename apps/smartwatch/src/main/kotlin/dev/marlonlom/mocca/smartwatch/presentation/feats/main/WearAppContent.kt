/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.smartwatch.presentation.feats.main

import androidx.compose.runtime.Composable
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import dev.marlonlom.mocca.smartwatch.presentation.theme.MoccaTheme

/**
 * Wear app main content composable.
 *
 * @author marlonlom
 */
@Composable
fun WearAppContent() {
  MoccaTheme {
    val scalingLazyListState = rememberScalingLazyListState()
    AppScaffold(listState = scalingLazyListState) {
      AppNavHost()
    }
  }
}
