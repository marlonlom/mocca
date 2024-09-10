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
    listOf("6", "7", "8", "9", "⌫"),
    listOf("2", "3", "4", "5", "✔"),
    listOf("", "0", "1", "", ""),
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
        modifier = Modifier
          .fillMaxWidth(),
      ) {
        buttonRow.forEach { buttonTxt ->
          if (buttonTxt.isEmpty()) {
            Text(
              modifier = Modifier
                .heightIn(max = 32.dp),
              text = buttonTxt,
              style = MaterialTheme.typography.bodyExtraSmall,
            )
          } else {
            val buttonTextColor = when (buttonTxt) {
              "⌫" -> MaterialTheme.colorScheme.onErrorContainer
              "✔" -> MaterialTheme.colorScheme.onTertiaryContainer
              else -> MaterialTheme.colorScheme.onSurface
            }
            val buttonFontWeight = when (buttonTxt) {
              "⌫" -> FontWeight.Bold
              "✔" -> FontWeight.Bold
              else -> FontWeight.Normal
            }
            CompactButton(
              modifier = Modifier
                .heightIn(max = 32.dp),
              colors = ButtonDefaults.outlinedButtonColors(),
              onClick = { onButtonClick(buttonTxt) },
            ) {
              Text(
                text = buttonTxt,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = buttonFontWeight,
                color = buttonTextColor,
              )
            }
          }
        }
      }
    }
  }
}
