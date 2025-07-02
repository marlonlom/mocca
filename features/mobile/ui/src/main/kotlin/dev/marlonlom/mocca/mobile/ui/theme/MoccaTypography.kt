/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import dev.marlonlom.mocca.core.ui.theme.MoccaFont

/**
 * Class that provides a custom [Typography] for the Mocca app,
 * allowing you to set a consistent [bodyFontFamily] across all text styles.
 *
 * @author marlonlom
 *
 * @param bodyFontFamily The [FontFamily] to apply to all typography styles.
 */
class MoccaTypography(val bodyFontFamily: FontFamily = MoccaFont.fontFamily) {

  /** Baseline typography. */
  private val baseline = Typography()

  /**
   * Returns a [Typography] instance with all text styles overridden
   * to use the specified [bodyFontFamily].
   */
  val appTypography
    get() = Typography(
      displayLarge = baseline.displayLarge.copy(fontFamily = bodyFontFamily),
      displayMedium = baseline.displayMedium.copy(fontFamily = bodyFontFamily),
      displaySmall = baseline.displaySmall.copy(fontFamily = bodyFontFamily),
      headlineLarge = baseline.headlineLarge.copy(fontFamily = bodyFontFamily),
      headlineMedium = baseline.headlineMedium.copy(fontFamily = bodyFontFamily),
      headlineSmall = baseline.headlineSmall.copy(fontFamily = bodyFontFamily),
      titleLarge = baseline.titleLarge.copy(fontFamily = bodyFontFamily),
      titleMedium = baseline.titleMedium.copy(fontFamily = bodyFontFamily),
      titleSmall = baseline.titleSmall.copy(fontFamily = bodyFontFamily),
      bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
      bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
      bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
      labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
      labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
      labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
    )
}
