/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.wearos.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors

/**
 * Branded colors single object.
 *
 * @author marlonlom
 */
internal object BrandColors {

  /** Brand color value: Cinnamon Satin. */
  private val cinnamonSatin = Color(0xFFCF6679)

  /** Brand color value: Flavescent. */
  private val flavescent = Color(0xFFFFE08D)

  /** Brand color value: Maize (Crayola). */
  private val maizeCrayola = Color(0xFFEBC349)

  /** Brand color value: 	Turquoise Green. */
  private val turquoiseGreen = Color(0xFFADCFAE)

  /** Brand color value: White Coffee. */
  private val whiteCoffee = Color(0xFFE8E1D9)

  /** Wear colors palette. */
  val wearColorPalette: Colors = Colors(

    primary = flavescent,
    primaryVariant = maizeCrayola,
    onPrimary = Color.Black,

    secondary = turquoiseGreen,
    secondaryVariant = turquoiseGreen,
    onSecondary = Color.Black,

    error = cinnamonSatin,
    onError = Color.Black,

    background = Color.Black,
    onBackground = whiteCoffee,

    surface = Color.Black,
    onSurface = whiteCoffee
  )
}
