/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.theme

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
internal object MoccaColorScheme {

  /** Dark color scheme. */
  val dark = darkColorScheme(
    primary = BrandColors.ronchi,
    onPrimary = BrandColors.cola2,
    primaryContainer = BrandColors.saddleBrown,
    onPrimaryContainer = BrandColors.salomie,
    secondary = BrandColors.akaroa,
    onSecondary = BrandColors.mikado,
    secondaryContainer = BrandColors.lisbonBrown,
    onSecondaryContainer = BrandColors.sidecar,
    tertiary = BrandColors.springRain,
    onTertiary = BrandColors.celtic,
    tertiaryContainer = BrandColors.tomThumb,
    onTertiaryContainer = BrandColors.fringyFlower,
    error = BrandColors.cornflowerLilac,
    errorContainer = BrandColors.sangria,
    onError = BrandColors.rosewood,
    onErrorContainer = BrandColors.peachSchnapps,
    background = BrandColors.zeus,
    onBackground = BrandColors.pearlBush,
    surface = BrandColors.zeus,
    onSurface = BrandColors.pearlBush,
    surfaceVariant = BrandColors.armadillo,
    onSurfaceVariant = BrandColors.softAmber,
    outline = BrandColors.paleOyster,
    inverseOnSurface = BrandColors.zeus,
    inverseSurface = BrandColors.pearlBush,
    inversePrimary = BrandColors.cinnamon,
    surfaceTint = BrandColors.ronchi,
    outlineVariant = BrandColors.armadillo,
    scrim = BrandColors.black,
  )

  /** Light color scheme. */
  val light = lightColorScheme(
    primary = BrandColors.cinnamon,
    onPrimary = BrandColors.white,
    primaryContainer = BrandColors.salomie,
    onPrimaryContainer = BrandColors.cola,
    secondary = BrandColors.tobaccoBrown,
    onSecondary = BrandColors.white,
    secondaryContainer = BrandColors.sidecar,
    onSecondaryContainer = BrandColors.jackoBean,
    tertiary = BrandColors.axolotl,
    onTertiary = BrandColors.white,
    tertiaryContainer = BrandColors.fringyFlower,
    onTertiaryContainer = BrandColors.englishHolly,
    error = BrandColors.thunderbird,
    errorContainer = BrandColors.peachSchnapps,
    onError = BrandColors.white,
    onErrorContainer = BrandColors.temptress,
    background = BrandColors.tutu,
    onBackground = BrandColors.zeus,
    surface = BrandColors.tutu,
    onSurface = BrandColors.zeus,
    surfaceVariant = BrandColors.athsSpecial,
    onSurfaceVariant = BrandColors.armadillo,
    outline = BrandColors.pablo,
    inverseOnSurface = BrandColors.merino,
    inverseSurface = BrandColors.dune,
    inversePrimary = BrandColors.ronchi,
    surfaceTint = BrandColors.cinnamon,
    outlineVariant = BrandColors.softAmber,
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
  content: @Composable () -> Unit,
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
    content = content,
  )
}
