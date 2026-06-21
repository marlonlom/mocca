/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.onboarding.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.TableChart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.wear.compose.material3.ButtonDefaults
import androidx.wear.compose.material3.CompactButton
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import dev.marlonlom.mocca.wearos.onboarding.R

/**
 * Displays a compact button that navigates to the fees screen.
 *
 * @author marlonlom
 *
 * @param onClicked Action invoked when the button is clicked.
 */
@Composable
internal fun ViewFeesButton(onClicked: () -> Unit) = CompactButton(
  modifier = Modifier
    .testTag("view_fees_action_button")
    .fillMaxWidth(),
  onClick = onClicked,
  colors = ButtonDefaults.buttonColors(
    containerColor = Color.Transparent,
    disabledContainerColor = Color.Transparent,
    contentColor = MaterialTheme.colorScheme.onSurface,
  ),
  icon = {
    Icon(
      imageVector = Icons.Rounded.TableChart,
      contentDescription = stringResource(R.string.text_view_fees),
      tint = MaterialTheme.colorScheme.onSurface,
    )
  },
  label = {
    Text(
      text = stringResource(R.string.text_view_fees),
      maxLines = 1,
      style = MaterialTheme.typography.bodySmall,
      overflow = TextOverflow.Ellipsis,
    )
  },
)
