/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/**
 * Defines different contrast levels for Mocca color schemes.
 *
 * Each enum constant holds a [light] and [dark] [ColorScheme].
 *
 * @author marlonlom
 *
 * @property light The [ColorScheme] for light theme.
 * @property dark The [ColorScheme] for dark theme.
 */
enum class MoccaColors(val light: ColorScheme, val dark: ColorScheme) {

  /** Default branded color contrast: Standard. */
  STANDARD(
    light = lightColorScheme(
      primary = Color(0xFF6B5E2D),
      onPrimary = Color(0xFFFFFFFF),
      primaryContainer = Color(0xFFEFDC9F),
      onPrimaryContainer = Color(0xFF6E602F),
      secondary = Color(0xFF6A5F14),
      onSecondary = Color(0xFFFFFFFF),
      secondaryContainer = Color(0xFFFAEA91),
      onSecondaryContainer = Color(0xFF74691E),
      tertiary = Color(0xFF53643C),
      onTertiary = Color(0xFFFFFFFF),
      tertiaryContainer = Color(0xFFD0E4B2),
      onTertiaryContainer = Color(0xFF55663E),
      error = Color(0xFFBA1A1A),
      onError = Color(0xFFFFFFFF),
      errorContainer = Color(0xFFFFDAD6),
      onErrorContainer = Color(0xFF93000A),
      background = Color(0xFFFEF8F1),
      onBackground = Color(0xFF1D1B17),
      surface = Color(0xFFFEF8F1),
      onSurface = Color(0xFF1D1B17),
      surfaceVariant = Color(0xFFEAE2D1),
      onSurfaceVariant = Color(0xFF4B463B),
      outline = Color(0xFF7C7769),
      outlineVariant = Color(0xFFCDC6B6),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFF32302C),
      inverseOnSurface = Color(0xFFF6F0E9),
      inversePrimary = Color(0xFFD8C68B),
      surfaceDim = Color(0xFFDFD9D2),
      surfaceBright = Color(0xFFFEF8F1),
      surfaceContainerLowest = Color(0xFFFFFFFF),
      surfaceContainerLow = Color(0xFFF9F3EC),
      surfaceContainer = Color(0xFFF3EDE6),
      surfaceContainerHigh = Color(0xFFEDE7E0),
      surfaceContainerHighest = Color(0xFFE7E2DB),
    ),
    dark = darkColorScheme(
      primary = Color(0xFFFFF8EF),
      onPrimary = Color(0xFF3A3003),
      primaryContainer = Color(0xFFEFDC9F),
      onPrimaryContainer = Color(0xFF6E602F),
      secondary = Color(0xFFFFFFFF),
      onSecondary = Color(0xFF383100),
      secondaryContainer = Color(0xFFF3E48B),
      onSecondaryContainer = Color(0xFF70651A),
      tertiary = Color(0xFFF0FFD7),
      onTertiary = Color(0xFF263512),
      tertiaryContainer = Color(0xFFD0E4B2),
      onTertiaryContainer = Color(0xFF55663E),
      error = Color(0xFFFFB4AB),
      onError = Color(0xFF690005),
      errorContainer = Color(0xFF93000A),
      onErrorContainer = Color(0xFFFFDAD6),
      background = Color(0xFF15130F),
      onBackground = Color(0xFFE7E2DB),
      surface = Color(0xFF15130F),
      onSurface = Color(0xFFE7E2DB),
      surfaceVariant = Color(0xFF4B463B),
      onSurfaceVariant = Color(0xFFCDC6B6),
      outline = Color(0xFF969082),
      outlineVariant = Color(0xFF4B463B),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFFE7E2DB),
      inverseOnSurface = Color(0xFF32302C),
      inversePrimary = Color(0xFF6B5E2D),
      surfaceDim = Color(0xFF15130F),
      surfaceBright = Color(0xFF3B3934),
      surfaceContainerLowest = Color(0xFF0F0E0A),
      surfaceContainerLow = Color(0xFF1D1B17),
      surfaceContainer = Color(0xFF211F1B),
      surfaceContainerHigh = Color(0xFF2C2A25),
      surfaceContainerHighest = Color(0xFF373430),
    ),
  ),

  /** Branded color contrast: Medium. */
  MEDIUM(
    light = lightColorScheme(
      primary = Color(0xFF403507),
      onPrimary = Color(0xFFFFFFFF),
      primaryContainer = Color(0xFF7B6C3A),
      onPrimaryContainer = Color(0xFFFFFFFF),
      secondary = Color(0xFF3E3600),
      onSecondary = Color(0xFFFFFFFF),
      secondaryContainer = Color(0xFF796E22),
      onSecondaryContainer = Color(0xFFFFFFFF),
      tertiary = Color(0xFF2B3B18),
      onTertiary = Color(0xFFFFFFFF),
      tertiaryContainer = Color(0xFF61734A),
      onTertiaryContainer = Color(0xFFFFFFFF),
      error = Color(0xFF740006),
      onError = Color(0xFFFFFFFF),
      errorContainer = Color(0xFFCF2C27),
      onErrorContainer = Color(0xFFFFFFFF),
      background = Color(0xFFFEF8F1),
      onBackground = Color(0xFF1D1B17),
      surface = Color(0xFFFEF8F1),
      onSurface = Color(0xFF12110D),
      surfaceVariant = Color(0xFFEAE2D1),
      onSurfaceVariant = Color(0xFF3A362B),
      outline = Color(0xFF575246),
      outlineVariant = Color(0xFF726D5F),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFF32302C),
      inverseOnSurface = Color(0xFFF6F0E9),
      inversePrimary = Color(0xFFD8C68B),
      surfaceDim = Color(0xFFCBC6BF),
      surfaceBright = Color(0xFFFEF8F1),
      surfaceContainerLowest = Color(0xFFFFFFFF),
      surfaceContainerLow = Color(0xFFF9F3EC),
      surfaceContainer = Color(0xFFEDE7E0),
      surfaceContainerHigh = Color(0xFFE1DCD5),
      surfaceContainerHighest = Color(0xFFD6D1CA),
    ),
    dark = darkColorScheme(
      primary = Color(0xFFFFF8EF),
      onPrimary = Color(0xFF3A3003),
      primaryContainer = Color(0xFFEFDC9F),
      onPrimaryContainer = Color(0xFF504415),
      secondary = Color(0xFFFFFFFF),
      onSecondary = Color(0xFF383100),
      secondaryContainer = Color(0xFFF3E48B),
      onSecondaryContainer = Color(0xFF524800),
      tertiary = Color(0xFFF0FFD7),
      onTertiary = Color(0xFF263512),
      tertiaryContainer = Color(0xFFD0E4B2),
      onTertiaryContainer = Color(0xFF394A25),
      error = Color(0xFFFFD2CC),
      onError = Color(0xFF540003),
      errorContainer = Color(0xFFFF5449),
      onErrorContainer = Color(0xFF000000),
      background = Color(0xFF15130F),
      onBackground = Color(0xFFE7E2DB),
      surface = Color(0xFF15130F),
      onSurface = Color(0xFFFFFFFF),
      surfaceVariant = Color(0xFF4B463B),
      onSurfaceVariant = Color(0xFFE3DCCB),
      outline = Color(0xFFB8B1A2),
      outlineVariant = Color(0xFF969082),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFFE7E2DB),
      inverseOnSurface = Color(0xFF2C2A25),
      inversePrimary = Color(0xFF534718),
      surfaceDim = Color(0xFF15130F),
      surfaceBright = Color(0xFF47443F),
      surfaceContainerLowest = Color(0xFF080705),
      surfaceContainerLow = Color(0xFF1F1D19),
      surfaceContainer = Color(0xFF2A2823),
      surfaceContainerHigh = Color(0xFF34322E),
      surfaceContainerHighest = Color(0xFF403D39),
    ),
  ),

  /** Branded color contrast: High. */
  HIGH(
    light = lightColorScheme(
      primary = Color(0xFF362B00),
      onPrimary = Color(0xFFFFFFFF),
      primaryContainer = Color(0xFF554819),
      onPrimaryContainer = Color(0xFFFFFFFF),
      secondary = Color(0xFF332C00),
      onSecondary = Color(0xFFFFFFFF),
      secondaryContainer = Color(0xFF534900),
      onSecondaryContainer = Color(0xFFFFFFFF),
      tertiary = Color(0xFF22310E),
      onTertiary = Color(0xFFFFFFFF),
      tertiaryContainer = Color(0xFF3E4E29),
      onTertiaryContainer = Color(0xFFFFFFFF),
      error = Color(0xFF600004),
      onError = Color(0xFFFFFFFF),
      errorContainer = Color(0xFF98000A),
      onErrorContainer = Color(0xFFFFFFFF),
      background = Color(0xFFFEF8F1),
      onBackground = Color(0xFF1D1B17),
      surface = Color(0xFFFEF8F1),
      onSurface = Color(0xFF000000),
      surfaceVariant = Color(0xFFEAE2D1),
      onSurfaceVariant = Color(0xFF000000),
      outline = Color(0xFF302C21),
      outlineVariant = Color(0xFF4D493D),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFF32302C),
      inverseOnSurface = Color(0xFFFFFFFF),
      inversePrimary = Color(0xFFD8C68B),
      surfaceDim = Color(0xFFBDB8B1),
      surfaceBright = Color(0xFFFEF8F1),
      surfaceContainerLowest = Color(0xFFFFFFFF),
      surfaceContainerLow = Color(0xFFF6F0E9),
      surfaceContainer = Color(0xFFE7E2DB),
      surfaceContainerHigh = Color(0xFFD9D4CD),
      surfaceContainerHighest = Color(0xFFCBC6BF),
    ),
    dark = darkColorScheme(
      primary = Color(0xFFFFF8EF),
      onPrimary = Color(0xFF000000),
      primaryContainer = Color(0xFFEFDC9F),
      onPrimaryContainer = Color(0xFF2F2500),
      secondary = Color(0xFFFFFFFF),
      onSecondary = Color(0xFF000000),
      secondaryContainer = Color(0xFFF3E48B),
      onSecondaryContainer = Color(0xFF302A00),
      tertiary = Color(0xFFF0FFD7),
      onTertiary = Color(0xFF000000),
      tertiaryContainer = Color(0xFFD0E4B2),
      onTertiaryContainer = Color(0xFF1C2A09),
      error = Color(0xFFFFECE9),
      onError = Color(0xFF000000),
      errorContainer = Color(0xFFFFAEA4),
      onErrorContainer = Color(0xFF220001),
      background = Color(0xFF15130F),
      onBackground = Color(0xFFE7E2DB),
      surface = Color(0xFF15130F),
      onSurface = Color(0xFFFFFFFF),
      surfaceVariant = Color(0xFF4B463B),
      onSurfaceVariant = Color(0xFFFFFFFF),
      outline = Color(0xFFF7EFDF),
      outlineVariant = Color(0xFFC9C2B2),
      scrim = Color(0xFF000000),
      inverseSurface = Color(0xFFE7E2DB),
      inverseOnSurface = Color(0xFF000000),
      inversePrimary = Color(0xFF534718),
      surfaceDim = Color(0xFF15130F),
      surfaceBright = Color(0xFF52504B),
      surfaceContainerLowest = Color(0xFF000000),
      surfaceContainerLow = Color(0xFF211F1B),
      surfaceContainer = Color(0xFF32302C),
      surfaceContainerHigh = Color(0xFF3D3B36),
      surfaceContainerHighest = Color(0xFF494641),
    ),
  ),
  ;

  /**
   * Utility functions for this enum class.
   *
   * @author marlonlom
   */
  companion object {

    /**
     * Returns the selected color contrast color scheme by name and if dark theme is applied.
     *
     * @param colorContrast Selected color contrast name.
     * @param darkTheme True/False is dark theme is applied.
     * @return Selected color contrast scheme.
     */
    fun findColorScheme(colorContrast: String, darkTheme: Boolean): ColorScheme = valueOf(colorContrast).let {
      if (darkTheme) it.dark else it.light
    }
  }
}
