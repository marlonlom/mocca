/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator.output

import androidx.annotation.StringRes
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
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorUiState
import kotlin.math.roundToLong

/**
 * Success result slot composable ui.
 *
 * @author marlonlom
 *
 * @param successState Calculation success state.
 * @param onSlotClosedAction Action for success dialog closed.
 */
@Composable
fun SuccessResultSlot(
  successState: CalculatorUiState.WithSuccess,
  onSlotClosedAction: () -> Unit
) {

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 10.dp)
      .border(
        width = 1.dp,
        color = MaterialTheme.colorScheme.tertiary,
        shape = MaterialTheme.shapes.medium,
      )
      .background(
        color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.25f),
        shape = MaterialTheme.shapes.medium,
      ),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    Row(verticalAlignment = Alignment.Bottom) {
      Column(
        modifier = Modifier.fillMaxWidth(0.5f),
        horizontalAlignment = Alignment.End
      ) {
        SuccessResultTitle(R.string.text_home_label_fee)
        SuccessResultValue(amountNumber = successState.response.fixedFee + successState.response.variableFee)
      }
      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
      ) {
        SuccessResultTitle(R.string.text_home_label_total)
        SuccessResultValue(amountNumber = successState.response.total)
      }
    }

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 10.dp),
      horizontalArrangement = Arrangement.End,
      verticalAlignment = Alignment.CenterVertically
    ) {
      TextButton(
        colors = ButtonDefaults.buttonColors(
          containerColor = MaterialTheme.colorScheme.tertiaryContainer,
          contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        shape = MaterialTheme.shapes.small,
        onClick = { onSlotClosedAction() },
      ) {
        Text(
          text = stringResource(id = R.string.text_home_button_close),
          fontWeight = FontWeight.Bold
        )
      }
    }

  }
}

@Composable
internal fun SuccessResultValue(
  amountNumber: Double
) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp)
      .padding(top = 4.dp),
    text = "\$ ${amountNumber.roundToLong()}",
    fontWeight = FontWeight.Bold,
    color = MaterialTheme.colorScheme.tertiary,
    style = MaterialTheme.typography.titleMedium,
  )
}

@Composable
internal fun SuccessResultTitle(
  @StringRes titleStringRes: Int
) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp)
      .padding(top = 20.dp),
    text = stringResource(id = titleStringRes),
    fontWeight = FontWeight.Bold,
    color = MaterialTheme.colorScheme.tertiary,
    style = MaterialTheme.typography.bodyMedium,
  )
}
