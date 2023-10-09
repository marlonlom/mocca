/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.smartwatch.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors


/**
 * Brand colors single object.
 *
 * @author marlonlom
 */
object BrandColors {

  /** Brand color value: Cape Honey. */
  val capeHoney = Color(0xFFFFDEA3)

  /** Brand color value: Deep Bronze. */
  val deepBronze = Color(0xFF53452A)

  /** Brand color value: Maire. */
  val maire = Color(0xFF1E1B16)

  /** Brand color value: Maroon4. */
  val maroon4 = Color(0xFF690005)

  /** Brand color value: Olive. */
  val olive = Color(0xFF5C4200)

  /** Brand color value: Raffia. */
  val raffia = Color(0xFFD9C4A0)

  /** Brand color value: Saffron. */
  val saffron = Color(0xFFFABC27)

  /** Brand color value: Sangria. */
  val sangria = Color(0xFF93000A)

  /** Brand color value: Spring Wood. */
  val springWood = Color(0xFFE9E1D9)

  /** Brand color value: Stark White. */
  val starkWhite = Color(0xFFD1C5B4)

  /** Brand color value: Wheat. */
  val wheat = Color(0xFFF6E0BB)

}

/** Wear color palette single object. */
internal val wearColorPalette = Colors(
  primary = BrandColors.saffron,
  primaryVariant = BrandColors.olive,
  secondary = BrandColors.deepBronze,
  secondaryVariant = BrandColors.raffia,
  background = BrandColors.maire,
  surface = BrandColors.maire,
  error = BrandColors.sangria,
  onPrimary = BrandColors.capeHoney,
  onSecondary = BrandColors.wheat,
  onBackground = BrandColors.springWood,
  onSurface = BrandColors.springWood,
  onSurfaceVariant = BrandColors.starkWhite,
  onError = BrandColors.maroon4,
)
