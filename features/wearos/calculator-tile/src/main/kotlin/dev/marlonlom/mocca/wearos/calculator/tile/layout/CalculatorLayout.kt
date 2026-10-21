/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.layout

import android.content.Context
import androidx.annotation.OptIn
import androidx.wear.protolayout.DeviceParametersBuilders.DeviceParameters
import androidx.wear.protolayout.LayoutElementBuilders.LayoutElement
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.expression.ProtoLayoutExperimental
import androidx.wear.protolayout.material3.ColorScheme
import androidx.wear.protolayout.material3.materialScope
import androidx.wear.protolayout.material3.primaryLayout
import dev.marlonlom.mocca.wearos.calculator.tile.slot.buildButtonsSlot
import dev.marlonlom.mocca.wearos.calculator.tile.slot.buildCalculatorInputSlot
import dev.marlonlom.mocca.wearos.calculator.tile.slot.buildTitleSlot

/**
 * Builds the primary calculator layout for the Wear OS tile, combining the title,
 * calculator input/keypad content, and bottom action buttons slot.
 *
 * @author marlonlom
 *
 * @param context The Android context used to resolve string resources.
 * @param deviceParameters The device parameters containing screen metrics and dimensions.
 * @param colorScheme The Wear Material 3 color scheme applied to the layout elements.
 * @param provideInputAmount Lambda function that provides the current input amount integer value.
 * @param onNumberButtonClicked Function that provides the click listener action for each digit button.
 * @param onDeleteButtonClicked The click listener action for the delete button.
 * @param onCalculateButtonClicked The click listener action for the calculate button.
 *
 * @return The layout element representing the complete calculator primary layout.
 */
@OptIn(ProtoLayoutExperimental::class)
internal fun calculatorLayout(
  context: Context,
  deviceParameters: DeviceParameters,
  colorScheme: ColorScheme,
  provideInputAmount: () -> Int,
  onNumberButtonClicked: (Int) -> ModifiersBuilders.Clickable,
  onDeleteButtonClicked: ModifiersBuilders.Clickable,
  onCalculateButtonClicked: ModifiersBuilders.Clickable,
): LayoutElement = materialScope(
  context = context,
  deviceConfiguration = deviceParameters,
  defaultColorScheme = colorScheme,
) {
  primaryLayout(
    titleSlot = {
      buildTitleSlot(
        context = context,
        deviceParameters = deviceConfiguration,
        colorScheme = colorScheme,
      )
    },
    mainSlot = {
      buildCalculatorInputSlot(
        context = context,
        deviceParameters = deviceConfiguration,
        colorScheme = colorScheme,
        provideInputAmount = provideInputAmount,
        onNumberButtonClicked = onNumberButtonClicked,
      )
    },
    bottomSlot = {
      buildButtonsSlot(
        context = context,
        deviceParameters = deviceConfiguration,
        colorScheme = colorScheme,
        onDeleteButtonClicked = onDeleteButtonClicked,
        onCalculateButtonClicked = onCalculateButtonClicked,
      )
    },
  )
}
