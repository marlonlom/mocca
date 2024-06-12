/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.smartwatch.presentation.feats.calculator_output

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text

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
  alertMessageText: String,
  onBackNavigationAction: () -> Unit
) {
  Column(
    modifier = Modifier
      .padding(top = 30.dp, bottom = 20.dp)
      .padding(horizontal = 20.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Icon(
      imageVector = Icons.Rounded.ErrorOutline,
      tint = MaterialTheme.colors.error,
      contentDescription = null
    )
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp),
      text = alertMessageText,
      color = MaterialTheme.colors.error,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.caption2
    )
    BackNavigationButton(
      isFailure = true,
      onBackNavigationAction = onBackNavigationAction
    )
  }
}
