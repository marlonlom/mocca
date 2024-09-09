/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.wearos.ui.main

import androidx.compose.runtime.Composable
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.TimeText
import androidx.wear.compose.material3.TimeTextDefaults
import dev.marlonlom.mocca.wearos.ui.navigation.NavigationHost

/**
 * Wear app main content composable.
 *
 * @author marlonlom
 */
@Composable
fun WearAppContent() {
  val scalingLazyListState = rememberScalingLazyListState()
  AppScaffold(
    timeText = {
      if (!scalingLazyListState.isScrollInProgress) {
        TimeText(
          timeTextStyle = TimeTextDefaults.timeTextStyle(
            fontSize = MaterialTheme.typography.bodySmall.fontSize
          )
        ) {
          time()
        }
      }
    },
    content = {
      NavigationHost()
    }
  )
}
