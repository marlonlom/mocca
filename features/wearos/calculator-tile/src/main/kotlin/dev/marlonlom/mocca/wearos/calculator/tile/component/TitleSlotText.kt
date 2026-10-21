/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.component

import android.content.Context
import androidx.annotation.OptIn
import androidx.wear.protolayout.DeviceParametersBuilders.DeviceParameters
import androidx.wear.protolayout.LayoutElementBuilders.LayoutElement
import androidx.wear.protolayout.expression.ProtoLayoutExperimental
import androidx.wear.protolayout.material3.ColorScheme
import androidx.wear.protolayout.material3.Typography.BODY_EXTRA_SMALL
import androidx.wear.protolayout.material3.materialScope
import androidx.wear.protolayout.material3.text
import androidx.wear.protolayout.types.layoutString
import dev.marlonlom.mocca.wearos.calculator.tile.R

/**
 * Creates the title slot text element configured with the given color scheme and resource string.
 *
 * @author marlonlom
 *
 * @param context The Android context used to resolve string resources.
 * @param deviceParameters The device parameters containing screen metrics and dimensions.
 * @param colorScheme The Wear Material 3 color scheme applied to the layout elements.
 *
 * @return The layout element for the title slot text.
 */
@OptIn(ProtoLayoutExperimental::class)
internal fun titleSlotText(
  context: Context,
  deviceParameters: DeviceParameters,
  colorScheme: ColorScheme,
): LayoutElement = materialScope(
  context = context,
  deviceConfiguration = deviceParameters,
  defaultColorScheme = colorScheme,
) {
  text(
    text = context.getString(R.string.text_calculator_input_money_amount).layoutString,
    typography = BODY_EXTRA_SMALL,
    color = colorScheme.onBackground,
  )
}
