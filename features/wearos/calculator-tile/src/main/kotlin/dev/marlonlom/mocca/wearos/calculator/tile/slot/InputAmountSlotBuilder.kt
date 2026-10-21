/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.slot

import android.content.Context
import androidx.annotation.OptIn
import androidx.wear.protolayout.DeviceParametersBuilders.DeviceParameters
import androidx.wear.protolayout.DimensionBuilders.expand
import androidx.wear.protolayout.DimensionBuilders.wrap
import androidx.wear.protolayout.LayoutElementBuilders.LayoutElement
import androidx.wear.protolayout.LayoutElementBuilders.TEXT_ALIGN_END
import androidx.wear.protolayout.LayoutElementBuilders.TEXT_ALIGN_START
import androidx.wear.protolayout.expression.ProtoLayoutExperimental
import androidx.wear.protolayout.layout.row
import androidx.wear.protolayout.layout.spacer
import androidx.wear.protolayout.material3.ColorScheme
import androidx.wear.protolayout.material3.Typography.BODY_EXTRA_SMALL
import androidx.wear.protolayout.material3.Typography.BODY_SMALL
import androidx.wear.protolayout.material3.materialScope
import androidx.wear.protolayout.material3.text
import androidx.wear.protolayout.modifiers.LayoutModifier
import androidx.wear.protolayout.modifiers.border
import androidx.wear.protolayout.modifiers.clip
import androidx.wear.protolayout.modifiers.padding
import androidx.wear.protolayout.types.layoutString
import dev.marlonlom.mocca.wearos.calculator.tile.R

/**
 * Builds the input amount row slot for the tile main section.
 *
 * @author marlonlom
 *
 * @param context The Android context used to resolve string resources.
 * @param deviceParameters The device parameters containing screen metrics and dimensions.
 * @param colorScheme The Wear Material 3 color scheme applied to the layout elements.
 * @param provideInputAmount Lambda function that provides the current input amount integer value.
 *
 * @return The layout element for the input amount slot.
 */
@OptIn(ProtoLayoutExperimental::class)
internal fun buildInputAmountSlot(
  context: Context,
  deviceParameters: DeviceParameters,
  colorScheme: ColorScheme,
  provideInputAmount: () -> Int,
): LayoutElement = materialScope(
  context = context,
  deviceConfiguration = deviceParameters,
  defaultColorScheme = colorScheme,
) {
  row(
    text(
      text = context.getString(R.string.text_calculator_input_currency).lowercase().layoutString,
      typography = BODY_EXTRA_SMALL,
      maxLines = 1,
      alignment = TEXT_ALIGN_START,
      color = colorScheme.onSecondaryContainer,
    ),
    spacer(width = expand()),
    text(
      text = provideInputAmount().toString().layoutString,
      typography = BODY_SMALL,
      maxLines = 1,
      alignment = TEXT_ALIGN_END,
      color = colorScheme.onSecondaryContainer,
    ),
    width = expand(),
    height = wrap(),
    modifier = LayoutModifier
      .border(
        width = 1f,
        color = colorScheme.onSecondaryContainer,
      )
      .clip(12f)
      .padding(horizontal = 16f, vertical = 8f),
  )
}
