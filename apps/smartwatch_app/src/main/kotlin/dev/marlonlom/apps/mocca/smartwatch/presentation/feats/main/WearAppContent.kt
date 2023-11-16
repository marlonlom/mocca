/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.smartwatch.presentation.feats.main

import androidx.compose.runtime.Composable
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import dev.marlonlom.apps.mocca.smartwatch.presentation.theme.MoccaTheme

@Composable
fun WearAppContent() {
  MoccaTheme {
    val scalingLazyListState = rememberScalingLazyListState()
    AppScaffold(listState = scalingLazyListState) {
      AppNavHost()
    }
  }
}
