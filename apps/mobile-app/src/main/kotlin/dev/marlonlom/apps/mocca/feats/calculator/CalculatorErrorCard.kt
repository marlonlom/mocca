/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.calculator.model.CalculationException
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil

/**
 * Calculation failure card composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeUtil Window size utility.
 * @param failureCalculationState Failure calculation ui state.
 */
@Composable
fun CalculatorErrorCard(
  windowSizeUtil: WindowSizeUtil,
  failureCalculationState: CalculatorUiState.WithFailure
) {

  val labelHorizontalPadding = when {
    windowSizeUtil.isTabletLandscape -> PaddingValues(horizontal = 10.dp)
    else -> PaddingValues(horizontal = 20.dp)
  }

  val labelVerticalPadding = PaddingValues(vertical = 20.dp)

  val labelTextStyle = when {
    windowSizeUtil.isTabletLandscape -> MaterialTheme.typography.bodySmall
    else -> MaterialTheme.typography.bodyMedium
  }

  val errorMessage = when (failureCalculationState.exception) {
    is CalculationException.AboveQuantityRange -> R.string.text_home_error_above_range
    is CalculationException.BelowQuantityRange -> R.string.text_home_error_below_range
    is CalculationException.NegativeQuantity -> R.string.text_home_error_negative_amounts
  }

  Card(
    modifier = Modifier.fillMaxWidth(),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.errorContainer,
      contentColor = MaterialTheme.colorScheme.onErrorContainer
    ),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.error)
  ) {
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(labelHorizontalPadding)
        .padding(labelVerticalPadding),
      text = stringResource(errorMessage),
      color = MaterialTheme.colorScheme.onErrorContainer,
      style = labelTextStyle,
      fontWeight = FontWeight.Normal
    )
  }
}
