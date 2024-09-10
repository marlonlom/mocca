/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.features.calculator.output

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.ButtonDefaults
import androidx.wear.compose.material3.CompactButton
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.MaterialTheme
import dev.marlonlom.mocca.R
import dev.marlonlom.mocca.calculator.Calculator
import dev.marlonlom.mocca.calculator.RequestedQuantity
import dev.marlonlom.mocca.calculator.model.CalculationException
import dev.marlonlom.mocca.calculator.model.OrderResponse

/**
 * Calculator output view composable ui.
 *
 * @author marlonlom
 *
 * @param amountText Input amount text value.
 * @param onBackNavigationAction Action for back navigation.
 */
@Composable
fun CalculatorOutputScreen(amountText: String, onBackNavigationAction: () -> Unit) {
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

  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 20.dp),
    contentAlignment = Alignment.Center,
  ) {
    val scrollState = rememberScrollState()

    when (calculationResultState) {
      is OrderResponse.Failure -> {
        val alertMessageText = when (calculationResultState.exception) {
          is CalculationException.AboveQuantityRange -> errorTextAboveRange
          is CalculationException.BelowQuantityRange -> errorTextBelowRange
          is CalculationException.NegativeQuantity -> errorTextNegative
          else -> errorTextInternal
        }

        FailureCalculatorOutput(
          scrollState = scrollState,
          alertMessageText = alertMessageText,
          onBackNavigationAction = onBackNavigationAction,
        )
      }

      is OrderResponse.Success -> SuccessCalculatorOutput(
        scrollState = scrollState,
        calculationResult = calculationResultState.item,
        onBackNavigationAction = onBackNavigationAction,
      )

      else -> FailureCalculatorOutput(
        scrollState = scrollState,
        alertMessageText = errorTextInternal,
        onBackNavigationAction = onBackNavigationAction,
      )
    }
  }
}

/**
 * Shared Back navigation button composable ui for success and failure output.
 *
 * @author marlonlom
 *
 * @param isFailure True/False if the output result is failure.
 * @param onBackNavigationAction Action for back navigation.
 */
@Composable
internal fun BackNavigationButton(isFailure: Boolean, onBackNavigationAction: () -> Unit) {
  val buttonColors = ButtonDefaults.buttonColors(
    containerColor = if (isFailure) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary,
    contentColor = if (isFailure) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onTertiary,
  )
  CompactButton(
    colors = buttonColors,
    onClick = {
      onBackNavigationAction()
    },
  ) {
    Icon(
      modifier = Modifier.size(DpSize(width = 20.dp, height = 20.dp)),
      painter = painterResource(
        if (isFailure) R.drawable.ic_rounded_close else R.drawable.ic_rounded_refresh,
      ),
      contentDescription = null,
    )
  }
}
