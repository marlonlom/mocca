/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.slot

import android.content.Context
import androidx.annotation.OptIn
import androidx.wear.protolayout.DeviceParametersBuilders.DeviceParameters
import androidx.wear.protolayout.DimensionBuilders
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.LayoutElementBuilders.LayoutElement
import androidx.wear.protolayout.expression.ProtoLayoutExperimental
import androidx.wear.protolayout.material3.ColorScheme
import dev.marlonlom.mocca.wearos.calculator.tile.component.titleSlotText
import dev.marlonlom.mocca.wearos.calculator.tile.util.WatchScreenHelper

/**
 * Builds the optional title slot based on the device screen size.
 *
 * @author marlonlom
 *
 * @param context The Android context used to resolve string resources.
 * @param deviceParameters The device parameters containing screen metrics and dimensions.
 * @param colorScheme The Wear Material 3 color scheme applied to the layout elements.
 *
 * @return The layout element for the title slot, or an empty spacer if the screen is compact.
 */
@OptIn(ProtoLayoutExperimental::class)
internal fun buildTitleSlot(
  context: Context,
  deviceParameters: DeviceParameters,
  colorScheme: ColorScheme,
): LayoutElement = when {
  !WatchScreenHelper.isSmallScreen(deviceParameters) -> {
    titleSlotText(context, deviceParameters, colorScheme)
  }

  else -> {
    LayoutElementBuilders.Spacer.Builder()
      .setHeight(DimensionBuilders.dp(0f))
      .build()
  }
}
