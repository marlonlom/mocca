/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.theme

import androidx.wear.protolayout.material3.ColorScheme
import androidx.wear.protolayout.types.LayoutColor

/**
 * Branded colors single object for wearos tile.
 *
 * @author marlonlom
 */
internal object MoccaColorSchemes {

  /** App color scheme value. */
  val colorScheme
    get() = ColorScheme(
      primary = LayoutColor(0xFFE1C46D.toInt()),
      primaryDim = LayoutColor(0xFF715C0C.toInt()),
      primaryContainer = LayoutColor(0xFF564500.toInt()),
      onPrimary = LayoutColor(0xFF3C2F00.toInt()),
      onPrimaryContainer = LayoutColor(0xFFFEE086.toInt()),
      secondary = LayoutColor(0xffebc16c.toInt()),
      secondaryDim = LayoutColor(0xff79590c.toInt()),
      secondaryContainer = LayoutColor(0xff5c4200.toInt()),
      onSecondary = LayoutColor(0xff402d00.toInt()),
      onSecondaryContainer = LayoutColor(0xffffdea2.toInt()),
      tertiary = LayoutColor(0xFFACCFAF.toInt()),
      tertiaryDim = LayoutColor(0xFF46664B.toInt()),
      tertiaryContainer = LayoutColor(0xFF2E4E35.toInt()),
      onTertiary = LayoutColor(0xFF183720.toInt()),
      onTertiaryContainer = LayoutColor(0xFFC7ECCA.toInt()),
      surfaceContainerLow = LayoutColor(0xFF202020.toInt()),
      surfaceContainer = LayoutColor(0xFF121212.toInt()),
      surfaceContainerHigh = LayoutColor(0xFF3E3E3E.toInt()),
      onSurface = LayoutColor(0xFFFFFFFF.toInt()),
      onSurfaceVariant = LayoutColor(0xFFDADCE0.toInt()),
      outline = LayoutColor(0xff979080.toInt()),
      outlineVariant = LayoutColor(0xFF4C4639.toInt()),
      background = LayoutColor(0xFF121212.toInt()),
      onBackground = LayoutColor(0xFFFFFFFF.toInt()),
      error = LayoutColor(0xFFFFB4AB.toInt()),
      onError = LayoutColor(0xFF690005.toInt()),
      errorContainer = LayoutColor(0xFF93000A.toInt()),
      onErrorContainer = LayoutColor(0xFFFFDAD6.toInt()),
    )
}
