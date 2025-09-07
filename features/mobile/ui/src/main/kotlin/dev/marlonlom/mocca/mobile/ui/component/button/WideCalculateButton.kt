/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.ui.R

@Composable
fun WideCalculateButton(buttonEnabled: Boolean, onButtonClicked: () -> Unit) = Row(
  modifier = Modifier
    .fillMaxWidth()
    .padding(vertical = 20.dp),
) {
  Button(
    modifier = Modifier
      .weight(1.0f)
      .padding(4.dp),
    enabled = buttonEnabled,
    onClick = onButtonClicked,
    border = BorderStroke(
      width = 1.dp,
      color = if (buttonEnabled) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.surface,
    ),
    colors = ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer,
      contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    ),
  ) {
    Text(
      text = stringResource(R.string.text_calculate),
      fontWeight = FontWeight.Bold,
      style = MaterialTheme.typography.bodyLarge,
    )
  }
}
