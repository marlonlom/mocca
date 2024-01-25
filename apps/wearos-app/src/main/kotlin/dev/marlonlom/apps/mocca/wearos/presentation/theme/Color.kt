/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.wearos.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors

/**
 * Branded colors single object.
 *
 * @author marlonlom
 */
internal object BrandColors {

  /** Brand color value: Cape Honey. */
  private val capeHoney = Color(0xFFFFDEA3)

  /** Brand color value: Maire. */
  private val maire = Color(0xFF1E1B16)

  /** Brand color value: Maroon2. */
  private val maroon2 = Color(0xFF402D00)

  /** Brand color value: Maroon4. */
  private val maroon4 = Color(0xFF690005)

  /** Brand color value: Melon. */
  private val melon = Color(0xFFFFB4AB)

  /** Brand color value: Myrtle. */
  private val myrtle = Color(0xFF1E361B)

  /** Brand color value: Pixie Green. */
  private val pixieGreen = Color(0xFFB1CFA8)

  /** Brand color value: Saffron. */
  private val saffron = Color(0xFFFABC27)

  /** Brand color value: Spring Wood. */
  private val springWood = Color(0xFFE9E1D9)

  /** Brand color value: Stark White. */
  private val starkWhite = Color(0xFFD1C5B4)

  /** Brand color value: Wheat. */
  private val wheat = Color(0xFFF6E0BB)

  val wearColorPalette: Colors = Colors(
    primary = saffron,
    primaryVariant = capeHoney,
    onPrimary = maroon2,

    secondary = pixieGreen,
    secondaryVariant = wheat,
    onSecondary = myrtle,

    error = melon,
    onError = maroon4,

    background = maire,
    onBackground = springWood,

    surface = maire,
    onSurface = springWood,
    onSurfaceVariant = starkWhite,
  )
}
