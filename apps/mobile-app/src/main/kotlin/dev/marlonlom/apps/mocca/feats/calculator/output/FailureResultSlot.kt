/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator.output

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.calculator.model.CalculationException
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorUiState

/**
 * Failure result slot composable ui.
 *
 * @author marlonlom
 *
 * @param failureState Calculation failure state.
 * @param onSlotClosedAction Action for failure dialog closed.
 */
@Composable
fun FailureResultSlot(
  failureState: CalculatorUiState.WithFailure,
  onSlotClosedAction: () -> Unit
) {

  val errorMessage = when (failureState.exception) {
    is CalculationException.AboveQuantityRange -> R.string.text_home_error_above_range
    is CalculationException.BelowQuantityRange -> R.string.text_home_error_below_range
    is CalculationException.NegativeQuantity -> R.string.text_home_error_negative_amounts
  }

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(all = 20.dp)
      .border(
        width = 1.dp,
        color = MaterialTheme.colorScheme.error,
        shape = MaterialTheme.shapes.medium,
      )
      .background(
        color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.25f),
        shape = MaterialTheme.shapes.medium,
      ),
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {

    Text(
      modifier = Modifier
        .fillMaxWidth(0.8f)
        .padding(10.dp)
        .padding(start = 20.dp),
      style = MaterialTheme.typography.bodyLarge,
      text = stringResource(id = errorMessage),
      fontWeight = FontWeight.Bold,
      color = MaterialTheme.colorScheme.error,
      maxLines = 2
    )

    IconButton(
      colors = IconButtonDefaults.iconButtonColors(
        containerColor = MaterialTheme.colorScheme.errorContainer,
        contentColor = MaterialTheme.colorScheme.onErrorContainer
      ),
      onClick = { onSlotClosedAction() },
    ) {
      Icon(imageVector = Icons.Rounded.Close, contentDescription = null)
    }

  }
}
