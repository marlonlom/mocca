/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.component

import android.content.Context
import androidx.wear.protolayout.DeviceParametersBuilders.DeviceParameters
import androidx.wear.protolayout.DimensionBuilders.expand
import androidx.wear.protolayout.DimensionBuilders.wrap
import androidx.wear.protolayout.LayoutElementBuilders.LayoutElement
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.material3.ButtonColors
import androidx.wear.protolayout.material3.ColorScheme
import androidx.wear.protolayout.material3.Typography.BODY_EXTRA_SMALL
import androidx.wear.protolayout.material3.materialScope
import androidx.wear.protolayout.material3.text
import androidx.wear.protolayout.material3.textButton
import androidx.wear.protolayout.types.layoutString

/**
 * Creates a compact action button for the main slot, it displays the number used in calculation.
 *
 * @author marlonlom
 *
 * @param context The Android context used to resolve resources.
 * @param deviceParameters The device parameters containing screen metrics.
 * @param colorScheme The Wear Material 3 color scheme applied.
 * @param numberText The text label displayed inside the button.
 * @param onNumberButtonClicked The click listener action.
 *
 * @return The layout element for the compact action button.
 */
internal fun numberCompactButton(
  context: Context,
  deviceParameters: DeviceParameters,
  colorScheme: ColorScheme,
  numberText: Int = 0,
  onNumberButtonClicked: ModifiersBuilders.Clickable,
): LayoutElement = materialScope(
  context = context,
  deviceConfiguration = deviceParameters,
  defaultColorScheme = colorScheme,
) {
  textButton(
    labelContent = {
      text(
        text = numberText.toString().layoutString,
        typography = BODY_EXTRA_SMALL,
      )
    },
    colors = ButtonColors(
      containerColor = colorScheme.surfaceContainer,
      labelColor = colorScheme.onSurface,
    ),
    width = expand(),
    height = wrap(),
    onClick = onNumberButtonClicked,
  )
}
