/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ButtonsSection(
  numberTypingEnabledState: MutableState<Boolean>,
  onPerformCalculationAction: () -> Unit,
  onDeleteLastNumberAction: () -> Unit,
  onAppendNumberAction: (String) -> Unit,
) {
  val cols = listOf(
    listOf("7", "4", "1", "0"), listOf("8", "5", "2", "✔"), listOf("9", "6", "3", "⌫")
  )
  HorizontalDivider(
    modifier = Modifier
      .fillMaxWidth()
      .background(
        MaterialTheme.colorScheme.primaryContainer
      )
  )
  Row(
    modifier = Modifier
      .fillMaxSize()
      .border(1.dp, MaterialTheme.colorScheme.primaryContainer)
      .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f))
      .padding(20.dp)
  ) {
    cols.forEach { col ->
      Column(modifier = Modifier.weight(1f)) {
        col.forEach { itm ->
          val textBtnBg = when (itm) {
            "✔" -> MaterialTheme.colorScheme.tertiaryContainer
            "⌫" -> MaterialTheme.colorScheme.secondaryContainer
            else -> Color.Transparent
          }
          val textColor = when (itm) {
            "✔" -> MaterialTheme.colorScheme.onTertiaryContainer
            "⌫" -> MaterialTheme.colorScheme.onSecondaryContainer
            else -> MaterialTheme.colorScheme.onPrimaryContainer
          }
          val buttonShape = when {
            listOf("✔", "⌫").contains(itm) -> MaterialTheme.shapes.small
            else -> RoundedCornerShape(0.dp)
          }
          TextButton(
            modifier = Modifier
              .weight(1f)
              .fillMaxWidth()
              .padding(4.dp),
            shape = buttonShape,
            enabled = numberTypingEnabledState.value,
            colors = ButtonDefaults.textButtonColors(
              containerColor = textBtnBg,
              contentColor = textColor,
              disabledContentColor = textColor.copy(alpha = 0.25f)
            ),
            onClick = {
              when (itm) {
                "✔" -> {
                  onPerformCalculationAction()
                }

                "⌫" -> {
                  onDeleteLastNumberAction()
                }

                else -> {
                  onAppendNumberAction(itm)
                }
              }
            },
          ) {
            Text(
              text = itm,
              fontWeight = FontWeight.Bold,
              style = MaterialTheme.typography.headlineLarge
            )
          }
        }
      }
    }
  }
}
