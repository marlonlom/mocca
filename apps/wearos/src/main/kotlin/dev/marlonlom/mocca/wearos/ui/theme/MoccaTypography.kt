/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.wearos.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.wear.compose.material3.Typography
import dev.marlonlom.mocca.R

/**
 * Branded typography single object.
 *
 * @author marlonlom
 */
internal object MoccaTypography {

  /** Baseline typography. */
  private val baseline = Typography()

  /** Display font family. */
  private val displayFontFamily = FontFamily(
    Font(R.font.comfortaa_regular, style = FontStyle.Normal, weight = FontWeight.Normal)
  )

  /** App typography value. */
  val appTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = displayFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = displayFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = displayFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = displayFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = displayFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = displayFontFamily),
    bodyExtraSmall = baseline.bodyExtraSmall.copy(fontFamily = displayFontFamily)
  )
}
