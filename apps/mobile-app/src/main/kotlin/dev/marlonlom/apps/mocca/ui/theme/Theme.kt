/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Brand color schemes single object.
 *
 * @author marlonlom
 */
object MoccaColorScheme {

  /** Dark color scheme. */
  val dark = darkColorScheme(
    primary = BrandColors.saffron,
    onPrimary = BrandColors.maroon2,
    primaryContainer = BrandColors.olive,
    onPrimaryContainer = BrandColors.capeHoney,
    secondary = BrandColors.raffia,
    onSecondary = BrandColors.brownBramble,
    secondaryContainer = BrandColors.deepBronze,
    onSecondaryContainer = BrandColors.wheat,
    tertiary = BrandColors.pixieGreen,
    onTertiary = BrandColors.myrtle,
    tertiaryContainer = BrandColors.palmLeaf,
    onTertiaryContainer = BrandColors.snowyMint,
    error = BrandColors.melon,
    errorContainer = BrandColors.sangria,
    onError = BrandColors.maroon4,
    onErrorContainer = BrandColors.mistyRose,
    background = BrandColors.maire,
    onBackground = BrandColors.springWood,
    surface = BrandColors.maire,
    onSurface = BrandColors.springWood,
    surfaceVariant = BrandColors.spaceShuttle,
    onSurfaceVariant = BrandColors.starkWhite,
    outline = BrandColors.heatheredGrey,
    inverseOnSurface = BrandColors.maire,
    inverseSurface = BrandColors.springWood,
    inversePrimary = BrandColors.olive2,
    surfaceTint = BrandColors.saffron,
    outlineVariant = BrandColors.spaceShuttle,
    scrim = BrandColors.black,
  )

  /** Light color scheme. */
  val light = lightColorScheme(
    primary = BrandColors.olive2,
    onPrimary = BrandColors.white,
    primaryContainer = BrandColors.capeHoney,
    onPrimaryContainer = BrandColors.maroon,
    secondary = BrandColors.tobaccoBrown,
    onSecondary = BrandColors.white,
    secondaryContainer = BrandColors.wheat,
    onSecondaryContainer = BrandColors.bakersChocolate,
    tertiary = BrandColors.tomThumb,
    onTertiary = BrandColors.white,
    tertiaryContainer = BrandColors.snowyMint,
    onTertiaryContainer = BrandColors.zuccini,
    error = BrandColors.fireBrick,
    errorContainer = BrandColors.mistyRose,
    onError = BrandColors.white,
    onErrorContainer = BrandColors.maroon3,
    background = BrandColors.lavenderBlush,
    onBackground = BrandColors.maire,
    surface = BrandColors.lavenderBlush,
    onSurface = BrandColors.maire,
    surfaceVariant = BrandColors.bleachWhite,
    onSurfaceVariant = BrandColors.spaceShuttle,
    outline = BrandColors.arrowtown,
    inverseOnSurface = BrandColors.fairPink,
    inverseSurface = BrandColors.acadia,
    inversePrimary = BrandColors.saffron,
    surfaceTint = BrandColors.olive2,
    outlineVariant = BrandColors.starkWhite,
    scrim = BrandColors.black,
  )
}

/**
 * Application theme composable function.
 *
 * @author marlonlom
 *
 * @param darkTheme true/false if the app is in dark theme.
 * @param dynamicColor true/false if the app is using dynamic colors (default: true).
 * @param content Composable app ui contents.
 */
@ExperimentalMaterial3Api
@Composable
fun MoccaTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    darkTheme -> MoccaColorScheme.dark
    else -> MoccaColorScheme.light
  }
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = colorScheme.surface.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = MoccaFont.appTypography,
    shapes = AppShapes,
    content = content
  )
}
