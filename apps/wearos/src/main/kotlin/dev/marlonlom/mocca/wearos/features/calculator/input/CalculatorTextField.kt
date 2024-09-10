/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.features.calculator.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import dev.marlonlom.mocca.R

/**
 * Calculator text field composable ui.
 *
 * @author marlonlom
 *
 * @param inputValue Calculator input value to be displayed.
 *
 */
@Composable
fun CalculatorTextField(inputValue: String) = Row(
  modifier = Modifier
    .fillMaxHeight(0.45f)
    .padding(vertical = 2.dp, horizontal = 24.dp),
  verticalAlignment = Alignment.Bottom,
) {
  val moneyInputTitle = LocalContext.current.getString(R.string.text_calculator_input_money_amount)
  val currencyTitle = LocalContext.current.getString(R.string.text_calculator_input_currency)

  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.SpaceBetween,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    val textColorStyle = MaterialTheme.colorScheme.onSurfaceVariant
    Text(
      text = moneyInputTitle,
      color = MaterialTheme.colorScheme.onPrimaryContainer,
      style = MaterialTheme.typography.bodyExtraSmall,
      textAlign = TextAlign.End,
      modifier = Modifier
        .fillMaxWidth(),
    )
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp),
      horizontalArrangement = Arrangement.SpaceAround,
      verticalAlignment = Alignment.Bottom,
    ) {
      Text(
        text = currencyTitle,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodySmall,
      )
      Text(
        modifier = Modifier
          .fillMaxWidth(),
        color = textColorStyle,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.End,
        text = inputValue,
        style = MaterialTheme.typography.bodyMedium,
      )
    }
  }
}
