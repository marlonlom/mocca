/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dev.marlonlom.mocca.calculator.model.CalculationException
import dev.marlonlom.mocca.mobile.calculator.output.R
import dev.marlonlom.mocca.mobile.calculator.output.domain.CalculatorOutputState

/**
 * Displays a descriptive text based on the result of the calculation.
 *
 * @author marlonlom
 *
 * @param uiState The state that determines the text to be displayed.
 */
@Composable
internal fun CalculationOutputDescription(uiState: CalculatorOutputState) {
  val descriptionText = when {
    uiState.isSuccess() -> R.string.text_review_success_detail

    uiState.isFailure() -> {
      val errorTextRes = when ((uiState as CalculatorOutputState.WithFailure).exception) {
        is CalculationException.AboveQuantityRange -> R.string.text_above_range_error
        is CalculationException.BelowQuantityRange -> R.string.text_below_range_error
        is CalculationException.NegativeQuantity -> R.string.text_negative_amount_error
      }
      errorTextRes
    }

    else -> R.string.text_input_amount_for_calculation
  }

  Text(
    modifier = Modifier
      .fillMaxWidth(),
    style = MaterialTheme.typography.bodyLarge,
    color = MaterialTheme.colorScheme.onSurface,
    textAlign = TextAlign.Center,
    text = stringResource(descriptionText),
  )
}
