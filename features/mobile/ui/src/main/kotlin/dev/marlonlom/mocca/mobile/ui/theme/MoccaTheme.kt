/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Applies the Mocca theme to your composable content.
 *
 * @author marlonlom
 *
 * @param darkTheme Whether to use the dark theme. Defaults to system setting.
 * @param dynamicColor Whether to use dynamic colors (Android 12+). Defaults to `true`.
 * @param colorContrast The color contrast setting. Defaults to [MoccaColors.STANDARD].
 * @param content The content to be themed.
 */
@Composable
fun MoccaTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  colorContrast: String = MoccaColors.STANDARD.name,
  content: @Composable () -> Unit,
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    else -> MoccaColors.findColorScheme(colorContrast, darkTheme)
  }

  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      @Suppress("DEPRECATION")
      window.statusBarColor = colorScheme.surface.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = MoccaTypography().appTypography,
    content = content,
  )
}
