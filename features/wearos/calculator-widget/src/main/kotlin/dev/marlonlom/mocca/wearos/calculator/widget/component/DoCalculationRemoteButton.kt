/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.widget.component

import android.app.PendingIntent
import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.remote.creation.compose.action.pendingIntentAction
import androidx.compose.remote.creation.compose.layout.RemoteComposable
import androidx.compose.remote.creation.compose.layout.RemoteImage
import androidx.compose.remote.creation.compose.modifier.RemoteModifier
import androidx.compose.remote.creation.compose.modifier.height
import androidx.compose.remote.creation.compose.shapes.RemoteRoundedCornerShape
import androidx.compose.remote.creation.compose.state.rb
import androidx.compose.remote.creation.compose.state.rdp
import androidx.compose.remote.creation.compose.state.rs
import androidx.compose.remote.creation.compose.state.rsp
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.wear.compose.remote.material3.RemoteButton
import androidx.wear.compose.remote.material3.RemoteButtonDefaults
import androidx.wear.compose.remote.material3.RemoteIconButton
import androidx.wear.compose.remote.material3.RemoteIconButtonDefaults
import androidx.wear.compose.remote.material3.RemoteMaterialTheme
import androidx.wear.compose.remote.material3.RemoteText
import dev.marlonlom.mocca.wearos.calculator.widget.R
import dev.marlonlom.mocca.wearos.calculator.widget.util.WidgetActivityAction.INPUT_CALCULATION
import dev.marlonlom.mocca.wearos.calculator.widget.util.createActionWatchActivityPendingIntent
import dev.marlonlom.mocca.wearos.calculator.widget.util.rememberVectorBitmap

/**
 * An internal remote composable button that triggers calculation input action.
 *
 * @author marlonlom
 *
 * @param modifier The modifier to be applied to the button.
 */
@RemoteComposable
@Composable
internal fun DoCalculationRemoteButton(
  isSmallScreen: Boolean,
  modifier: RemoteModifier = RemoteModifier
) {

  val remoteImage: @RemoteComposable @Composable () -> Unit = {
    RemoteImage(
      remoteBitmap = rememberVectorBitmap(
        imageVector = Icons.Rounded.AttachMoney,
        size = 16.dp,
        tint = androidx.compose.ui.graphics.Color(0xFF3C2F00),
      ).rb,
      contentDescription = stringResource(R.string.text_start_calculation_widget).rs,
    )
  }

  val pendingIntent: (Context) -> PendingIntent = { context ->
    createActionWatchActivityPendingIntent(
      context = context,
      widgetAction = INPUT_CALCULATION,
    )
  }


  when {
    isSmallScreen -> RemoteIconButton(
      modifier = modifier,
      onClick = pendingIntentAction { context -> pendingIntent(context) },
      colors = RemoteIconButtonDefaults.iconButtonColors(
        containerColor = RemoteMaterialTheme.colorScheme.primary,
        contentColor = RemoteMaterialTheme.colorScheme.onPrimary,
      ),
      content = {
        remoteImage()
      },
    )

    else -> RemoteButton(
      modifier = modifier.height(48.rdp),
      onClick = pendingIntentAction { context -> pendingIntent(context) },
      shape = RemoteRoundedCornerShape(20.rdp),
      colors = RemoteButtonDefaults.buttonColors(
        containerColor = RemoteMaterialTheme.colorScheme.primary,
        contentColor = RemoteMaterialTheme.colorScheme.onPrimary,
      ),
      icon = {
        remoteImage()
      },
      label = {
        RemoteText(
          text = stringResource(R.string.text_start_calculation_widget).rs,
          style = RemoteMaterialTheme.typography.labelSmall.copy(fontSize = 12.rsp),
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
        )
      },
    )
  }
}
