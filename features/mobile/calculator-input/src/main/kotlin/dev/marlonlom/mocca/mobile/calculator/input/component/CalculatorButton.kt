/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.input.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * A composable calculator button.
 *
 * @author marlonlom
 *
 * @param onButtonClicked Callback invoked when the button is clicked, passing the [buttonText].
 * @param buttonText The text displayed on the button.
 * @param modifier Modifier to apply to the button layout.
 */
@Composable
internal fun CalculatorButton(onButtonClicked: (String) -> Unit, buttonText: String, modifier: Modifier = Modifier) =
  Button(
    modifier = modifier
      .size(64.dp)
      .padding(4.dp),
    onClick = { onButtonClicked(buttonText) },
    shape = CircleShape,
    colors = ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.surfaceContainer,
      contentColor = MaterialTheme.colorScheme.onSurface,
    ),
  ) {
    Text(
      text = buttonText,
      fontWeight = FontWeight.Bold,
      style = MaterialTheme.typography.bodyLarge,
    )
  }
