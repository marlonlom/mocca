/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.widget.layout

import androidx.compose.remote.creation.compose.layout.RemoteAlignment
import androidx.compose.remote.creation.compose.layout.RemoteArrangement
import androidx.compose.remote.creation.compose.layout.RemoteColumn
import androidx.compose.remote.creation.compose.layout.RemoteComposable
import androidx.compose.remote.creation.compose.layout.RemoteRow
import androidx.compose.remote.creation.compose.modifier.RemoteModifier
import androidx.compose.remote.creation.compose.modifier.fillMaxSize
import androidx.compose.remote.creation.compose.modifier.fillMaxWidth
import androidx.compose.remote.creation.compose.modifier.padding
import androidx.compose.remote.creation.compose.modifier.width
import androidx.compose.remote.creation.compose.state.rdp
import androidx.compose.remote.creation.compose.state.rf
import androidx.compose.remote.foundation.layout.RemoteSpacer
import androidx.compose.runtime.Composable
import dev.marlonlom.mocca.wearos.calculator.widget.component.DoCalculationRemoteButton
import dev.marlonlom.mocca.wearos.calculator.widget.component.ShowFeesRemoteButton

/**
 * A remote composable layout that displays the main content for the Mocca calculator widget,
 * centering the action buttons horizontally and vertically with spacing between them.
 *
 * @author marlonlom
 *
 */
@RemoteComposable
@Composable
internal fun MoccaCalculatorWidgetContent(modifier: RemoteModifier = RemoteModifier, isSmallScreen: Boolean = false) =
  RemoteColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(if (isSmallScreen) 2.rdp else 4.rdp),
    horizontalAlignment = RemoteAlignment.CenterHorizontally,
    verticalArrangement = RemoteArrangement.Center,
  ) {
    RemoteRow(
      modifier = RemoteModifier.fillMaxWidth(),
      horizontalArrangement = RemoteArrangement.SpaceBetween,
    ) {
      DoCalculationRemoteButton(isSmallScreen = isSmallScreen, modifier = RemoteModifier.weight(1f.rf))
      RemoteSpacer(modifier = RemoteModifier.width(8.rdp))
      ShowFeesRemoteButton()
    }
  }
