/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material3.ColorScheme

/**
 * Branded colors single object.
 *
 * @author marlonlom
 */
internal object MoccaColorSchemes {

  /** App color scheme value. */
  val colorScheme
    get() = ColorScheme(
      primary = Color(0xFFE1C46D),
      primaryDim = Color(0xFF715C0C),
      primaryContainer = Color(0xFF564500),
      onPrimary = Color(0xFF3C2F00),
      onPrimaryContainer = Color(0xFFFEE086),
      secondary = Color(0xffebc16c),
      secondaryDim = Color(0xff79590c),
      secondaryContainer = Color(0xff5c4200),
      onSecondary = Color(0xff402d00),
      onSecondaryContainer = Color(0xffffdea2),
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
    )
}
