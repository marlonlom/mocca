/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.wearos.presentation.feats.calculator_output

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.CardDefaults
import androidx.wear.compose.material.CompactButton
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TitleCard
import dev.marlonlom.apps.mocca.calculator.Calculator
import dev.marlonlom.apps.mocca.calculator.RequestedQuantity
import dev.marlonlom.apps.mocca.calculator.model.CalculationException
import dev.marlonlom.apps.mocca.calculator.model.CalculationResult
import dev.marlonlom.apps.mocca.calculator.model.OrderResponse
import dev.marlonlom.apps.mocca.wearos.R
import kotlin.math.roundToLong

@Composable
fun CalculatorOutput(
  amountText: String,
  onBackNavigationAction: () -> Unit
) {
  BackHandler {
    onBackNavigationAction()
  }
  val errorTextAboveRange = stringResource(R.string.text_result_error_above_range)
  val errorTextBelowRange = stringResource(R.string.text_result_error_below_range)
  val errorTextNegative = stringResource(R.string.text_result_error_negative_amounts)
  val errorTextInternal = stringResource(R.string.text_result_error_internal)

  val calculationResultState = remember {
    Calculator.calculate(RequestedQuantity(amountText.toDouble()))
  }

  when (calculationResultState) {
    is OrderResponse.Failure -> {
      val alertMessageText = when (calculationResultState.exception) {
        is CalculationException.AboveQuantityRange -> errorTextAboveRange
        is CalculationException.BelowQuantityRange -> errorTextBelowRange
        is CalculationException.NegativeQuantity -> errorTextNegative
        else -> errorTextInternal
      }

      FailureCalculatorOutput(
        alertMessageText = alertMessageText,
        onBackNavigationAction = onBackNavigationAction
      )
    }

    is OrderResponse.Success -> SuccessCalculatorOutput(
      calculationResult = calculationResultState.item,
      onBackNavigationAction = onBackNavigationAction
    )

    else -> FailureCalculatorOutput(
      alertMessageText = errorTextInternal,
      onBackNavigationAction = onBackNavigationAction
    )
  }

}

@Composable
private fun SuccessCalculatorOutput(
  calculationResult: CalculationResult,
  onBackNavigationAction: () -> Unit
) {
  Column(
    modifier = Modifier
      .padding(top = 40.dp, bottom = 20.dp)
      .padding(horizontal = 20.dp)
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    CalculationTitleCard(
      titleRes = R.string.text_result_label_fee,
      calculationResultValue = calculationResult.fixedFee.plus(calculationResult.variableFee)
    )
    CalculationTitleCard(
      titleRes = R.string.text_result_label_total,
      calculationResultValue = calculationResult.total
    )
    BackNavigationButton(
      isFailure = false,
      onBackNavigationAction = onBackNavigationAction
    )
  }
}

@Composable
private fun CalculationTitleCard(
  titleRes: Int,
  calculationResultValue: Double
) {
  TitleCard(
    onClick = {},
    title = {
      Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(titleRes),
        maxLines = 1,
        color = MaterialTheme.colors.secondary,
        textAlign = TextAlign.Right,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.caption3
      )
    },
    backgroundPainter = CardDefaults.cardBackgroundPainter(
      startBackgroundColor = MaterialTheme.colors.surface,
      endBackgroundColor = MaterialTheme.colors.surface,
    )
  ) {
    Text(
      modifier = Modifier.fillMaxWidth(),
      text = stringResource(
        R.string.text_cop_currency_value,
        calculationResultValue.roundToLong()
      ),
      maxLines = 1,
      color = MaterialTheme.colors.secondary,
      textAlign = TextAlign.Right,
      style = MaterialTheme.typography.body2
    )
  }
}

@Composable
private fun FailureCalculatorOutput(
  alertMessageText: String,
  onBackNavigationAction: () -> Unit
) {
  Column(
    modifier = Modifier
      .padding(top = 30.dp, bottom = 20.dp)
      .padding(horizontal = 20.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Icon(
      imageVector = Icons.Rounded.ErrorOutline,
      tint = MaterialTheme.colors.error,
      contentDescription = null
    )
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp),
      text = alertMessageText,
      color = MaterialTheme.colors.error,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.caption2
    )
    BackNavigationButton(
      isFailure = true,
      onBackNavigationAction = onBackNavigationAction
    )
  }
}

@Composable
private fun BackNavigationButton(
  isFailure: Boolean,
  onBackNavigationAction: () -> Unit
) {
  val buttonColors = ButtonDefaults.buttonColors(
    backgroundColor = if (isFailure) MaterialTheme.colors.error else MaterialTheme.colors.secondary,
    contentColor = if (isFailure) MaterialTheme.colors.onError else MaterialTheme.colors.onSecondary,
  )
  CompactButton(colors = buttonColors, onClick = {
    onBackNavigationAction()
  }) {
    Icon(
      modifier = Modifier.size(DpSize(width = 20.dp, height = 20.dp)),
      imageVector = if (isFailure) Icons.Rounded.Close else Icons.Rounded.Refresh,
      contentDescription = null
    )
  }
}
