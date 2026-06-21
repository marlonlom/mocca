/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material3.MaterialTheme
import dev.marlonlom.mocca.wearos.onboarding.component.StartCalculationButton
import dev.marlonlom.mocca.wearos.onboarding.component.ViewFeesButton

/**
 * Displays the onboarding screen with actions to proceed to calculation or view fees.
 *
 * @author marlonlom
 *
 * @param onCalculateClick Invoked when the user selects the calculate action.
 * @param onViewFeesClick Invoked when the user selects the view fees action.
 */
@Composable
fun OnboardingScreen(onCalculateClick: () -> Unit, onViewFeesClick: () -> Unit) {
  val listState: ScalingLazyListState = rememberScalingLazyListState()
  ScalingLazyColumn(
    state = listState,
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    item {
      Spacer(Modifier.height(20.dp))
    }
    item {
      StartCalculationButton(onClicked = onCalculateClick)
    }
    item {
      ViewFeesButton(onClicked = onViewFeesClick)
    }
  }
}
