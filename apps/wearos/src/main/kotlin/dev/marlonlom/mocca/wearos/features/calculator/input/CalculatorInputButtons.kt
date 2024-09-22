/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.features.calculator.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.ButtonDefaults
import androidx.wear.compose.material3.CompactButton
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text

/**
 * Calculator buttons composable ui.
 *
 * @author marlonlom
 *
 * @param onButtonClick Action for calculator button clicked.
 */
@Composable
fun CalculatorInputButtons(onButtonClick: (String) -> Unit) {
  val buttonRows = listOf(
    listOf("1", "2", "3", "4", "5"),
    listOf("6", "7", "8", "9", "0"),
    listOf("⌫", " ", "✔"),
  )
  Column(
    modifier = Modifier
      .background(MaterialTheme.colorScheme.surfaceContainerLow)
      .fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    buttonRows.forEach { buttonRow ->
      Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
      ) {
        buttonRow.forEach { buttonTxt ->
          CalculatorDigitButton(buttonTxt, onButtonClick)
        }
      }
    }
  }
}

/**
 * Calculator digit button composable ui.
 *
 * @author marlonlom
 *
 * @param buttonTxt Button text.
 * @param onButtonClick Action for button clicked.
 */
@Composable
private fun CalculatorDigitButton(buttonTxt: String, onButtonClick: (String) -> Unit) {
  val buttonFontWeight = when (buttonTxt) {
    "⌫" -> FontWeight.Bold
    "✔" -> FontWeight.Bold
    else -> FontWeight.Normal
  }
  val buttonColors = when (buttonTxt) {
    "⌫" -> ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.error,
      contentColor = MaterialTheme.colorScheme.onError,
    )

    "✔" -> ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.tertiary,
      contentColor = MaterialTheme.colorScheme.onTertiary,
    )

    else -> ButtonDefaults.outlinedButtonColors()
  }
  val buttonCommonModifier = Modifier.heightIn(max = 32.dp)
  if (buttonTxt.trim().isEmpty()) {
    Text(
      modifier = buttonCommonModifier,
      text = buttonTxt,
    )
  } else {
    CompactButton(
      modifier = buttonCommonModifier
        .testTag("calculatorButton_$buttonTxt"),
      colors = buttonColors,
      onClick = { onButtonClick(buttonTxt) },
    ) {
      Text(
        text = buttonTxt,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = buttonFontWeight,
      )
    }
  }
}
