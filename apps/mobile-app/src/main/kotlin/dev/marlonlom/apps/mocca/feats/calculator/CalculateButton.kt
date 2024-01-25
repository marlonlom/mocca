/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import androidx.compose.foundation.BorderStroke
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
import dev.marlonlom.apps.mocca.R

/**
 * Button composable ui for calculate the transfer costs using provided sending amount.
 *
 * @author marlonlom
 *
 * @param buttonEnabled true/false if button is enabled.
 * @param buttonClicked Action for calculate button clicked.
 */
@Composable
fun CalculateButton(
  buttonEnabled: Boolean,
  buttonClicked: () -> Unit,
) {
  Button(
    modifier = Modifier.padding(4.dp),
    colors = ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer,
      contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    ),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
    shape = MaterialTheme.shapes.small,
    onClick = { buttonClicked() },
    enabled = buttonEnabled,
  ) {
    Text(
      text = stringResource(R.string.text_home_button_calculate),
      style = MaterialTheme.typography.bodyLarge,
      fontWeight = FontWeight.Bold
    )
  }
}
