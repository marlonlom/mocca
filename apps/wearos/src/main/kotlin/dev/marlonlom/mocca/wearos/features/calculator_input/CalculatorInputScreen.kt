/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.wearos.features.calculator_input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.MaterialTheme

/**
 * Calculator input screen composable ui.
 *
 * @author marlonlom
 *
 * @param onCalculationReadyAction Action for money amount ready for calculation.
 * @param calculatorInputState Calculator input state value.
 */
@Composable
fun CalculatorInputScreen(
  onCalculationReadyAction: (String) -> Unit,
  calculatorInputState: CalculatorInputState = rememberCalculatorInputState(),
) = Column(
  modifier = Modifier
      .fillMaxWidth()
      .background(MaterialTheme.colorScheme.background)
      .padding(0.dp),
  horizontalAlignment = Alignment.CenterHorizontally,
  verticalArrangement = Arrangement.Center
) {
  CalculatorTextField(
    inputValue = calculatorInputState.textValue
  )
  CalculatorInputButtons(
    onButtonClick = { buttonTxt ->

      when (buttonTxt) {
        "✔" -> {
          if (calculatorInputState.textValue.length > 1) {
            onCalculationReadyAction(calculatorInputState.textValue)
          }
        }

        else -> {
          calculatorInputState.update(buttonTxt)
        }
      }

    }
  )
}

