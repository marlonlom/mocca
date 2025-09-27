/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.output.R
import dev.marlonlom.mocca.mobile.calculator.output.domain.CalculatorOutputState

/**
 * Displays the title for the calculation output based on the current UI state.
 *
 * @author marlonlom
 *
 * @param uiState The state that contains the result of the calculation.
 */
@Composable
internal fun CalculationOutputTitle(uiState: CalculatorOutputState) = Text(
  modifier = Modifier
    .fillMaxWidth()
    .paddingFromBaseline(top = 40.dp, bottom = 20.dp),
  style = MaterialTheme.typography.headlineMedium,
  color = MaterialTheme.colorScheme.onSurface,
  textAlign = TextAlign.Center,
  text = stringResource(
    when {
      uiState.isFailure() -> R.string.text_calculation_failed
      uiState.isSuccess() -> R.string.text_calculation_successful
      else -> R.string.text_perform_calculation
    },
  ),
)
