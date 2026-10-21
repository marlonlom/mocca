/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.component

import android.content.Context
import androidx.annotation.OptIn
import androidx.wear.protolayout.ColorBuilders.ColorProp
import androidx.wear.protolayout.LayoutElementBuilders.FontStyle
import androidx.wear.protolayout.LayoutElementBuilders.Text
import androidx.wear.protolayout.TypeBuilders
import androidx.wear.protolayout.expression.ProtoLayoutExperimental
import androidx.wear.protolayout.material3.ColorScheme
import androidx.wear.protolayout.material3.Typography.BODY_EXTRA_SMALL
import dev.marlonlom.mocca.wearos.calculator.tile.R

/**
 * Creates the title slot text element configured with the given color scheme and resource string.
 *
 * @author marlonlom
 *
 * @param context The Android context used to resolve string resources.
 * @param colorScheme The Wear Material 3 color scheme applied to the text element.
 * @return A [Text] instance configured with the app's title typography and color.
 */
@OptIn(ProtoLayoutExperimental::class)
internal fun titleSlotText(context: Context, colorScheme: ColorScheme): Text = Text.Builder()
  .setText(
    TypeBuilders.StringProp.Builder(
      context.getString(R.string.text_calculator_input_money_amount),
    ).build(),
  )
  .setFontStyle(
    FontStyle.Builder()
      .setColor(
        ColorProp.Builder(colorScheme.onPrimaryContainer.staticArgb).build(),
      )
      .setSizes(BODY_EXTRA_SMALL)
      .build(),
  )
  .build()
