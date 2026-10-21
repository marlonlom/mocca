/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.slot

import android.content.Context
import androidx.annotation.OptIn
import androidx.wear.protolayout.DeviceParametersBuilders.DeviceParameters
import androidx.wear.protolayout.DimensionBuilders.dp
import androidx.wear.protolayout.DimensionBuilders.wrap
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.LayoutElementBuilders.LayoutElement
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.expression.ProtoLayoutExperimental
import androidx.wear.protolayout.material3.ButtonColors
import androidx.wear.protolayout.material3.ColorScheme
import androidx.wear.protolayout.material3.materialScope
import dev.marlonlom.mocca.wearos.calculator.tile.component.actionCompactButton

/**
 * Builds the buttons row slot for the tile bottom section.
 *
 * @author marlonlom
 *
 * @param context The Android context used to resolve string resources.
 * @param deviceParameters The device parameters containing screen metrics and dimensions.
 * @param colorScheme The Wear Material 3 color scheme applied to the layout elements.
 * @param onCalculateButtonClicked The click action for calculate/confirm button.
 * @param onDeleteButtonClicked The click action for delete/backspace button.
 *
 * @return The layout element for the buttons slot.
 */
@OptIn(ProtoLayoutExperimental::class)
internal fun buildButtonsSlot(
  context: Context,
  deviceParameters: DeviceParameters,
  colorScheme: ColorScheme,
  onCalculateButtonClicked: ModifiersBuilders.Clickable,
  onDeleteButtonClicked: ModifiersBuilders.Clickable,
): LayoutElement = materialScope(
  context = context,
  deviceConfiguration = deviceParameters,
  defaultColorScheme = colorScheme,
) {
  LayoutElementBuilders.Row.Builder().setHeight(wrap()).addContent(
    actionCompactButton(
      context = context,
      deviceParameters = deviceConfiguration,
      colorScheme = colorScheme,
      actionText = "⌫",
      actionButtonColors = ButtonColors(
        containerColor = colorScheme.surfaceContainer,
        labelColor = colorScheme.errorDim,
      ),
      onActionButtonClicked = onDeleteButtonClicked,
    ),
  ).addContent(
    LayoutElementBuilders.Spacer.Builder().setWidth(dp(8f)).build(),
  ).addContent(
    actionCompactButton(
      context = context,
      deviceParameters = deviceConfiguration,
      colorScheme = colorScheme,
      actionButtonColors = ButtonColors(
        containerColor = colorScheme.surfaceContainer,
        labelColor = colorScheme.tertiaryDim,
      ),
      onActionButtonClicked = onCalculateButtonClicked,
    ),
  ).build()
}
