/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.slot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.output.R
import dev.marlonlom.mocca.mobile.calculator.output.domain.CalculatorOutputState
import java.text.NumberFormat
import java.util.Locale

/**
 * Displays the details for a successful calculation.
 *
 * @author marlonlom
 *
 * @param uiState The state containing the successful calculation result.
 */
@Composable
internal fun CalculationSuccessDetailsSlot(uiState: CalculatorOutputState.WithSuccess) {
  val numberFormat = NumberFormat.getNumberInstance(Locale.US).also {
    it.maximumFractionDigits = 0
  }

  val inputAmount = numberFormat.format(uiState.amount.toDouble())
  val feeAmount = numberFormat.format(uiState.response.let { it.fixedFee.plus(it.variableFee) })
  val totalAmount = numberFormat.format(uiState.response.total)

  val labelText: @Composable (Int) -> Unit = { labelRes ->
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .paddingFromBaseline(30.dp, 10.dp),
      text = stringResource(labelRes),
      textAlign = TextAlign.Center,
      color = MaterialTheme.colorScheme.onSurface,
    )
  }

  val numberText: @Composable (String) -> Unit = { numberTxt ->
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp),
      text = stringResource(R.string.text_cop_value, numberTxt),
      textAlign = TextAlign.Center,
      color = MaterialTheme.colorScheme.onSurface,
      style = MaterialTheme.typography.titleLarge,
    )
  }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 20.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    labelText(R.string.text_label_requested_amount)
    numberText(inputAmount)

    labelText(R.string.text_label_fee)
    numberText(feeAmount)

    labelText(R.string.text_label_total)
    numberText(totalAmount)
  }
}
