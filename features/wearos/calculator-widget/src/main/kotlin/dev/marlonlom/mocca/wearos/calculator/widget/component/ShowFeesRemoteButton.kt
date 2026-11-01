/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.widget.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.TableChart
import androidx.compose.remote.creation.compose.action.pendingIntentAction
import androidx.compose.remote.creation.compose.layout.RemoteComposable
import androidx.compose.remote.creation.compose.layout.RemoteImage
import androidx.compose.remote.creation.compose.modifier.RemoteModifier
import androidx.compose.remote.creation.compose.state.rb
import androidx.compose.remote.creation.compose.state.rdp
import androidx.compose.remote.creation.compose.state.rs
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.remote.material3.RemoteIconButton
import androidx.wear.compose.remote.material3.RemoteIconButtonDefaults
import androidx.wear.compose.remote.material3.RemoteMaterialTheme
import dev.marlonlom.mocca.wearos.calculator.widget.R
import dev.marlonlom.mocca.wearos.calculator.widget.util.WidgetActivityAction.SHOW_FEES
import dev.marlonlom.mocca.wearos.calculator.widget.util.createActionWatchActivityPendingIntent
import dev.marlonlom.mocca.wearos.calculator.widget.util.rememberVectorBitmap

/**
 * A remote composable button that triggers the action to show the fees table
 * on the watch activity.
 *
 * @author marlonlom
 */
@RemoteComposable
@Composable
internal fun ShowFeesRemoteButton(modifier: RemoteModifier = RemoteModifier) = RemoteIconButton(
  modifier = modifier,
  onClick = pendingIntentAction { context ->
    createActionWatchActivityPendingIntent(
      context = context,
      widgetAction = SHOW_FEES,
    )
  },
  colors = RemoteIconButtonDefaults.iconButtonColors(
    containerColor = RemoteMaterialTheme.colorScheme.surfaceContainerHigh,
    contentColor = RemoteMaterialTheme.colorScheme.onSurface,
  ),
  content = {
    RemoteImage(
      remoteBitmap = rememberVectorBitmap(
        imageVector = Icons.Rounded.TableChart,
        size = 16.dp,
        tint = androidx.compose.ui.graphics.Color(0xFFDADCE0),
      ).rb,
      contentDescription = stringResource(R.string.text_show_fees_table_widget).rs,
    )
  },
)
