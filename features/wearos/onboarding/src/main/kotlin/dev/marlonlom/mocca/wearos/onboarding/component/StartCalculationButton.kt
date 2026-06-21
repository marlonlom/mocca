/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.onboarding.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.ButtonDefaults
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import dev.marlonlom.mocca.wearos.onboarding.R

/**
 * Displays a button that navigates to the calculation screen.
 *
 * @author marlonlom
 *
 * @param onClicked Action invoked when the button is clicked.
 */
@Composable
internal fun StartCalculationButton(onClicked: () -> Unit) = Button(
  modifier = Modifier.testTag("start_calculation_action_button")
    .fillMaxWidth(),
  onClick = onClicked,
  colors = ButtonDefaults.buttonColors(
    containerColor = Color.Transparent,
    disabledContainerColor = Color.Transparent,
    contentColor = MaterialTheme.colorScheme.primary,
  ),
  border = BorderStroke(
    width = 1.dp,
    color = MaterialTheme.colorScheme.primary,
  ),
  icon = {
    Icon(
      imageVector = Icons.Rounded.AttachMoney,
      contentDescription = stringResource(R.string.text_start_calculation),
      tint = MaterialTheme.colorScheme.primary,
    )
  },
  label = {
    Text(
      text = stringResource(R.string.text_start_calculation),
      color = MaterialTheme.colorScheme.primary,
      maxLines = 1,
      style = MaterialTheme.typography.bodySmall,
      overflow = TextOverflow.Ellipsis,
    )
  },
)
