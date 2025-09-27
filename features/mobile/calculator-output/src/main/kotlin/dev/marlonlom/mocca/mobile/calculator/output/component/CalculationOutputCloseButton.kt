/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.output.R
import dev.marlonlom.mocca.mobile.calculator.output.domain.CalculatorOutputState

/**
 * Displays a close button for the calculation output.
 *
 * @author marlonlom
 *
 * @param uiState The state that may influence the button's appearance or behavior.
 * @param buttonEnabled A boolean to explicitly enable or disable the button.
 * @param onButtonClicked The action to perform when the button is clicked.
 */
@Composable
internal fun CalculationOutputCloseButton(
  uiState: CalculatorOutputState,
  buttonEnabled: Boolean,
  onButtonClicked: () -> Unit,
) {
  val buttonTextRes = when {
    uiState.isFailure() -> R.string.text_close_failed
    uiState.isSuccess() -> R.string.text_close_success
    else -> 0
  }
  val buttonBorderColor = when {
    uiState.isFailure() -> MaterialTheme.colorScheme.onErrorContainer
    uiState.isSuccess() -> MaterialTheme.colorScheme.onTertiaryContainer
    else -> MaterialTheme.colorScheme.surface
  }
  val buttonColors = when {
    uiState.isFailure() -> ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.errorContainer,
      contentColor = MaterialTheme.colorScheme.onErrorContainer,
    )

    uiState.isSuccess() -> ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.tertiaryContainer,
      contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
    )

    else -> ButtonDefaults.buttonColors()
  }

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 20.dp),
  ) {
    Button(
      modifier = Modifier
        .weight(1.0f)
        .padding(4.dp),
      enabled = buttonEnabled,
      onClick = onButtonClicked,
      border = BorderStroke(
        width = 1.dp,
        color = if (buttonEnabled) buttonBorderColor else MaterialTheme.colorScheme.surface,
      ),
      colors = buttonColors,
    ) {
      Text(
        text = stringResource(buttonTextRes),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyLarge,
      )
    }
  }
}
