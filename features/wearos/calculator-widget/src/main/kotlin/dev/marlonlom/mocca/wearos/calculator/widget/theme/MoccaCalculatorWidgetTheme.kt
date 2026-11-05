/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.widget.theme

import androidx.compose.ui.graphics.Color
import androidx.glance.wear.WearWidgetBrush
import androidx.glance.wear.color
import androidx.wear.compose.material3.ColorScheme
import androidx.wear.compose.remote.material3.RemoteColorScheme

/**
 * Theme utility object for Mocca calculator Wear OS widget, defining background brushes and styling colors.
 *
 * @author marlonlom
 */
internal object MoccaCalculatorWidgetTheme {

  /**
   * Background brush for the calculator widget.
   */
  val brush: WearWidgetBrush
    get() = WearWidgetBrush.color(colorScheme.surfaceContainerLow)

  /** Remote color scheme for the calculator widget. */
  val colorScheme = RemoteColorScheme(
    colorScheme = ColorScheme(
      primary = Color(0xFFE1C46D),
      primaryDim = Color(0xFF715C0C),
      primaryContainer = Color(0xFF564500),
      onPrimary = Color(0xFF3C2F00),
      onPrimaryContainer = Color(0xFFFEE086),
      secondary = Color(0XFFEBC16C),
      secondaryDim = Color(0XFF79590C),
      secondaryContainer = Color(0XFF5C4200),
      onSecondary = Color(0XFF402D00),
      onSecondaryContainer = Color(0XFFFFDEA2),
      tertiary = Color(0xFFACCFAF),
      tertiaryDim = Color(0xFF46664B),
      tertiaryContainer = Color(0xFF2E4E35),
      onTertiary = Color(0xFF183720),
      onTertiaryContainer = Color(0xFFC7ECCA),
      surfaceContainerLow = Color(0xFF202020),
      surfaceContainer = Color(0xFF121212),
      surfaceContainerHigh = Color(0xFF3E3E3E),
      onSurface = Color(0xFFFFFFFF),
      onSurfaceVariant = Color(0xFFDADCE0),
      outline = Color(0xff979080),
      outlineVariant = Color(0xFF4C4639),
      background = Color(0xFF121212),
      onBackground = Color(0xFFFFFFFF),
      error = Color(0xFFFFB4AB),
      onError = Color(0xFF690005),
      errorContainer = Color(0xFF93000A),
      onErrorContainer = Color(0xFFFFDAD6),
    ),
  )
}
