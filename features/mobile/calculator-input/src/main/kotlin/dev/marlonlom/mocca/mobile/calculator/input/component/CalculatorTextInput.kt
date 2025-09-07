/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.input.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.input.R

/**
 * A composable text input field for displaying calculator input or results.
 *
 * @author marlonlom
 *
 * @param amountText The text to display in the input field. Defaults to "0".
 */
@Composable
internal fun CalculatorTextInput(amountText: String = 0.toString()) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 10.dp)
      .paddingFromBaseline(top = 30.dp, bottom = 10.dp),
    text = stringResource(R.string.text_input_label),
    style = MaterialTheme.typography.titleMedium,
    color = MaterialTheme.colorScheme.onBackground,
    minLines = 1,
    maxLines = 1,
  )
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .border(
        border = BorderStroke(
          width = 1.dp,
          color = MaterialTheme.colorScheme.onSurface,
        ),
        shape = MaterialTheme.shapes.large,
      )
      .padding(horizontal = 20.dp, vertical = 10.dp),
    verticalAlignment = Alignment.Bottom,
    horizontalArrangement = Arrangement.SpaceBetween,
  ) {
    Text(
      text = stringResource(R.string.text_cop_signature),
      style = MaterialTheme.typography.titleLarge,
      color = MaterialTheme.colorScheme.onBackground,
      minLines = 1,
      maxLines = 1,
    )
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .height(36.dp),
      color = MaterialTheme.colorScheme.onBackground,
      textAlign = TextAlign.End,
      text = amountText,
      overflow = TextOverflow.Ellipsis,
      style = MaterialTheme.typography.headlineMedium,
      minLines = 1,
      maxLines = 1,
    )
  }
}
