/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.features.calculator.output

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import dev.marlonlom.mocca.R

/**
 * Failure calculation output composable ui.
 *
 * @author marlonlom
 *
 * @param alertMessageText Alert message text value.
 * @param onBackNavigationAction Action for back navigation.
 */
@Composable
internal fun FailureCalculatorOutput(
  scrollState: ScrollState,
  alertMessageText: String,
  onBackNavigationAction: () -> Unit,
) {
  Column(
    modifier = Modifier
      .padding(top = 30.dp, bottom = 20.dp)
      .padding(horizontal = 20.dp)
      .verticalScroll(scrollState),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Icon(
      painter = painterResource(R.drawable.ic_rounded_error),
      tint = MaterialTheme.colorScheme.error,
      contentDescription = null,
    )
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, bottom = 20.dp),
      text = alertMessageText,
      color = MaterialTheme.colorScheme.error,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.bodyExtraSmall,
    )
    BackNavigationButton(
      isFailure = true,
      onBackNavigationAction = onBackNavigationAction,
    )
  }
}
