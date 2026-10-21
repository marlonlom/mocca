/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.slot

import android.content.Context
import androidx.annotation.OptIn
import androidx.wear.protolayout.DeviceParametersBuilders.DeviceParameters
import androidx.wear.protolayout.DimensionBuilders.dp
import androidx.wear.protolayout.DimensionBuilders.expand
import androidx.wear.protolayout.DimensionBuilders.wrap
import androidx.wear.protolayout.LayoutElementBuilders.HORIZONTAL_ALIGN_CENTER
import androidx.wear.protolayout.LayoutElementBuilders.LayoutElement
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.expression.ProtoLayoutExperimental
import androidx.wear.protolayout.layout.column
import androidx.wear.protolayout.layout.spacer
import androidx.wear.protolayout.material3.ColorScheme
import androidx.wear.protolayout.material3.buttonGroup
import androidx.wear.protolayout.material3.materialScope
import dev.marlonlom.mocca.wearos.calculator.tile.component.numberCompactButton

/**
 * Builds the main content slot for the calculator tile, containing the input amount display
 * and the number keypad button groups (digits 0 to 9).
 *
 * @author marlonlom
 *
 * @param context The Android context used to resolve string resources.
 * @param deviceParameters The device parameters containing screen metrics and dimensions.
 * @param colorScheme The Wear Material 3 color scheme applied to the layout elements.
 * @param provideInputAmount Lambda function that provides the current input amount integer value.
 * @param onNumberButtonClicked Function that provides the click listener action for each digit button.
 *
 * @return The layout element representing the main content section of the tile.
 */
@OptIn(ProtoLayoutExperimental::class)
internal fun buildCalculatorInputSlot(
  context: Context,
  deviceParameters: DeviceParameters,
  colorScheme: ColorScheme,
  provideInputAmount: () -> Int,
  onNumberButtonClicked: (Int) -> ModifiersBuilders.Clickable,
): LayoutElement = materialScope(
  context = context,
  deviceConfiguration = deviceParameters,
  defaultColorScheme = colorScheme,
) {
  column(
    buildInputAmountSlot(
      context = context,
      deviceParameters = deviceConfiguration,
      colorScheme = colorScheme,
      provideInputAmount = provideInputAmount,
    ),
    spacer(height = dp(8f)),
    buttonGroup(
      width = expand(),
      height = wrap(),
    ) {
      (0..4).forEach { digit ->
        buttonGroupItem {
          numberCompactButton(
            context = context,
            deviceParameters = deviceConfiguration,
            colorScheme = colorScheme,
            numberText = digit + 1,
            onNumberButtonClicked = onNumberButtonClicked(digit),
          )
        }
      }
    },
    spacer(height = dp(4f)),
    buttonGroup(
      width = expand(),
      height = wrap(),
    ) {
      (5..9).forEach { digit ->
        buttonGroupItem {
          numberCompactButton(
            context = context,
            deviceParameters = deviceConfiguration,
            colorScheme = colorScheme,
            numberText = if (digit == 9) 0 else digit,
            onNumberButtonClicked = onNumberButtonClicked(digit),
          )
        }
      }
    },
    horizontalAlignment = HORIZONTAL_ALIGN_CENTER,
    width = expand(),
    height = wrap(),
  )
}
