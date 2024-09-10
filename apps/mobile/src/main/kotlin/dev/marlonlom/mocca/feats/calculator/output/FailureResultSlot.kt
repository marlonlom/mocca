/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.feats.calculator.output

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.R
import dev.marlonlom.mocca.calculator.model.CalculationException
import dev.marlonlom.mocca.feats.calculator.CalculatorUiState

/**
 * Failure result slot composable ui.
 *
 * @author marlonlom
 *
 * @param failureState Calculation failure state.
 * @param onSlotClosedAction Action for failure dialog closed.
 */
@Composable
fun FailureResultSlot(failureState: CalculatorUiState.WithFailure, onSlotClosedAction: () -> Unit) {
  val errorMessage = when (failureState.exception) {
    is CalculationException.AboveQuantityRange -> R.string.text_home_error_above_range
    is CalculationException.BelowQuantityRange -> R.string.text_home_error_below_range
    is CalculationException.NegativeQuantity -> R.string.text_home_error_negative_amounts
  }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 10.dp)
      .border(
        width = 1.dp,
        color = MaterialTheme.colorScheme.error,
        shape = MaterialTheme.shapes.medium,
      )
      .background(
        color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.25f),
        shape = MaterialTheme.shapes.medium,
      ),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
        .padding(top = 20.dp),
      text = stringResource(id = errorMessage),
      fontWeight = FontWeight.Bold,
      color = MaterialTheme.colorScheme.error,
      style = MaterialTheme.typography.bodySmall,
    )

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 10.dp),
      horizontalArrangement = Arrangement.End,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      TextButton(
        colors = ButtonDefaults.buttonColors(
          containerColor = MaterialTheme.colorScheme.errorContainer,
          contentColor = MaterialTheme.colorScheme.onErrorContainer,
        ),
        shape = MaterialTheme.shapes.small,
        onClick = { onSlotClosedAction() },
      ) {
        Text(
          text = stringResource(id = R.string.text_home_button_close),
          fontWeight = FontWeight.Bold,
        )
      }
    }
  }
}
