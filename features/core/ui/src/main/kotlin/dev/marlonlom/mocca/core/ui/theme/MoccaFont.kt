/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import dev.marlonlom.mocca.core.ui.R

/**
 * An object holding the custom font family, providing regular and italic styles.
 *
 * @author marlonlom
 *
 */
object MoccaFont {

  /** Defines a [FontFamily] containing the application font. */
  val fontFamily: FontFamily
    get() = FontFamily(
      Font(resId = R.font.abeezee_regular, style = FontStyle.Normal),
      Font(resId = R.font.abeezee_italic, style = FontStyle.Italic),
    )
}
