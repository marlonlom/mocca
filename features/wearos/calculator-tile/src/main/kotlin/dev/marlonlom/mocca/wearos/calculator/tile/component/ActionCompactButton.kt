/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.component

import android.content.Context
import androidx.wear.protolayout.DeviceParametersBuilders.DeviceParameters
import androidx.wear.protolayout.DimensionBuilders.wrap
import androidx.wear.protolayout.LayoutElementBuilders.LayoutElement
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.material3.ButtonColors
import androidx.wear.protolayout.material3.ColorScheme
import androidx.wear.protolayout.material3.Typography.BODY_EXTRA_SMALL
import androidx.wear.protolayout.material3.compactButton
import androidx.wear.protolayout.material3.materialScope
import androidx.wear.protolayout.material3.text
import androidx.wear.protolayout.material3.textButton
import androidx.wear.protolayout.types.layoutString
import dev.marlonlom.mocca.wearos.calculator.tile.util.WatchScreenHelper

/**
 * Creates a compact action button for the tile bottom slot.
 *
 * @author marlonlom
 *
 * @param context The Android context used to resolve resources.
 * @param deviceParameters The device parameters containing screen metrics.
 * @param colorScheme The Wear Material 3 color scheme applied.
 * @param actionText The text label displayed inside the button.
 * @param actionButtonColors The button colors.
 * @param onActionButtonClicked The click listener action.
 *
 * @return The layout element for the compact action button.
 */
internal fun actionCompactButton(
  context: Context,
  deviceParameters: DeviceParameters,
  colorScheme: ColorScheme,
  actionText: String = "✔",
  actionButtonColors: ButtonColors,
  onActionButtonClicked: ModifiersBuilders.Clickable,
): LayoutElement = materialScope(
  context = context,
  deviceConfiguration = deviceParameters,
  defaultColorScheme = colorScheme,
) {
  when {
    WatchScreenHelper.isSmallScreen(deviceParameters) -> {
      textButton(
        labelContent = {
          text(
            text = actionText.layoutString,
            typography = BODY_EXTRA_SMALL,
          )
        },
        colors = actionButtonColors,
        width = wrap(),
        height = wrap(),
        onClick = onActionButtonClicked,
      )
    }

    else -> {
      compactButton(
        labelContent = {
          text(text = actionText.layoutString)
        },
        colors = actionButtonColors,
        width = wrap(),
        onClick = onActionButtonClicked,
      )
    }
  }
}
