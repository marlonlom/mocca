/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.foundation.CurvedTextStyle
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.TimeText
import androidx.wear.compose.material3.TimeTextDefaults
import androidx.wear.compose.material3.timeTextCurvedText
import dev.marlonlom.mocca.wearos.calculator.fees.CalculatorFeesListScreen
import dev.marlonlom.mocca.wearos.calculator.input.CalculatorInputScreen
import dev.marlonlom.mocca.wearos.calculator.output.CalculatorOutputScreen
import dev.marlonlom.mocca.wearos.onboarding.OnboardingScreen
import dev.marlonlom.mocca.wearos.ui.navigation.NavigationHost

/**
 * Root composable for Wear OS app content.
 *
 * @author marlonlom
 *
 * @param isRound Whether the device screen is round.
 * @param scalingLazyListState State holder for ScalingLazyColumn scroll position.
 */
@Composable
fun WearAppContent(
  isRound: Boolean = LocalConfiguration.current.isScreenRound,
  scalingLazyListState: ScalingLazyListState = rememberScalingLazyListState(),
) = AppScaffold(
  timeText = {
    if (!scalingLazyListState.isScrollInProgress) {
      val fontStyle = MaterialTheme.typography.bodySmall
      if (isRound) {
        TimeText { time ->
          timeTextCurvedText(
            time = time,
            style = CurvedTextStyle(
              fontSize = fontStyle.fontSize,
            ),
          )
        }
      } else {
        val time = TimeTextDefaults.rememberTimeSource(TimeTextDefaults.timeFormat()).currentTime()
        Text(
          modifier = Modifier.fillMaxWidth(),
          text = time,
          textAlign = TextAlign.Center,
          style = fontStyle,
        )
      }
    }
  },
  content = {
    NavigationHost(
      home = { onCalculateClick, onViewFeesClick ->
        OnboardingScreen(
          listState = scalingLazyListState,
          onCalculateClick = onCalculateClick,
          onViewFeesClick = onViewFeesClick,
        )
      },
      viewFees = { onBackNavigationAction ->
        CalculatorFeesListScreen(
          listState = scalingLazyListState,
          onBackNavigationAction = onBackNavigationAction,
        )
      },
      calculatorInput = { onCalculationReadyAction ->
        CalculatorInputScreen(onCalculationReadyAction = onCalculationReadyAction)
      },
      calculatorOutput = { amountText, onBackNavigationAction ->
        CalculatorOutputScreen(
          amountText = amountText,
          onBackNavigationAction = onBackNavigationAction,
        )
      },
    )
  },
)
