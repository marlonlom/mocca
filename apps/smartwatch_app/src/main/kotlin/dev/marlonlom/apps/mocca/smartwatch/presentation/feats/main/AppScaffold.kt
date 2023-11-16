/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.smartwatch.presentation.feats.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition

@Composable
fun AppScaffold(
  listState: ScalingLazyListState,
  appContent: @Composable () -> Unit
) {
  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colors.surface),
    timeText = {
      if (!listState.isScrollInProgress) {
        TimeText()
      }
    },
    vignette = { TopAndBottomVignette() },
    positionIndicator = { PositionIndicator(scalingLazyListState = listState) },
    content = appContent
  )
}

@Composable
internal fun TopAndBottomVignette() = Vignette(vignettePosition = VignettePosition.TopAndBottom)
