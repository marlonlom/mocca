/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.input.slot

import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.input.component.CalculatorButton
import dev.marlonlom.mocca.mobile.calculator.input.util.CalculatorNumbersGrid

/**
 * A composable grid layout for displaying calculator buttons.
 *
 * @author marlonlom
 *
 * @param onButtonClicked Callback invoked when any button is clicked, passing the button's text.
 * @param calculatorNumbers A list of strings representing rows of calculator numbers (e.g., ["7 8 9", "4 5 6"]).
 */
@Composable
internal fun CalculatorButtonsGrid(
  onButtonClicked: (String) -> Unit,
  calculatorNumbers: List<String> = CalculatorNumbersGrid.sequenceList(),
) = FlowRow(
  modifier = Modifier
    .fillMaxWidth()
    .padding(vertical = 10.dp),
  maxItemsInEachRow = 3,
  itemVerticalAlignment = Alignment.CenterVertically,
) {
  calculatorNumbers.filter { pts -> pts.length > 1 }.forEach {
    it.split(" ").forEachIndexed { index, numberTxt ->
      CalculatorButton(
        modifier = Modifier.weight(1.0f),
        buttonText = numberTxt,
        onButtonClicked = { onButtonClicked(numberTxt) },
      )
    }
  }

  CalculatorButton(
    modifier = Modifier
      .weight(1.0f)
      .padding(4.dp),
    buttonText = "CE",
    onButtonClicked = { onButtonClicked("CE") },
  )

  CalculatorButton(
    modifier = Modifier
      .weight(1.0f)
      .padding(4.dp),
    buttonText = "0",
    onButtonClicked = { onButtonClicked("0") },
  )

  CalculatorButton(
    modifier = Modifier
      .weight(1.0f)
      .padding(4.dp),
    buttonText = "⌫",
    onButtonClicked = { onButtonClicked("<") },
  )
}
