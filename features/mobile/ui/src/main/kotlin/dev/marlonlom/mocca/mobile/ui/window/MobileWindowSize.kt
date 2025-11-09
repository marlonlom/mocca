/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.window

import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

/**
 * Defines common device layout profiles based on window width and height size classes.
 *
 * The [MobileWindowSize] enum is used to categorize UI layout needs by interpreting
 * a device's current window dimensions into form factor types such as mobile, tablet, or desktop,
 * with consideration for orientation (portrait vs. landscape).
 *
 * @author marlonlom
 */
enum class MobileWindowSize {
  /** Mobile device in portrait orientation (e.g., smartphone held vertically). */
  MOBILE_PORTRAIT,

  /** Mobile device in landscape orientation (e.g., smartphone held horizontally). */
  MOBILE_LANDSCAPE,

  /** Tablet device in portrait orientation. */
  TABLET_PORTRAIT,

  /** Tablet device in landscape orientation. */
  TABLET_LANDSCAPE,

  /** Desktop or large-screen device, or fallback for uncategorized layouts. */
  DESKTOP,

  ;

  companion object {

    /**
     * Maps a [WindowSizeClass] to a [MobileWindowSize] based on width and height size classes.
     *
     * @param windowSizeClass The classification of the current window dimensions.
     * @return The matching [MobileWindowSize] for the given size classes.
     */
    fun fromWindowSizeClass(
      windowSizeClass: androidx.compose.material3.windowsizeclass.WindowSizeClass,
    ): MobileWindowSize = windowSizeClass.let {
      val widthClass = it.widthSizeClass
      val heightClass = it.heightSizeClass
      return when {
        widthClass == androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Compact &&
          arrayOf(
            androidx.compose.material3.windowsizeclass.WindowHeightSizeClass.Medium,
            androidx.compose.material3.windowsizeclass.WindowHeightSizeClass.Expanded,
          ).contains(heightClass) -> MOBILE_PORTRAIT

        arrayOf(
          androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Medium,
          androidx.compose.material3.windowsizeclass.WindowHeightSizeClass.Expanded,
        ).contains(widthClass) &&
          heightClass == androidx.compose.material3.windowsizeclass.WindowHeightSizeClass.Compact
        -> MOBILE_LANDSCAPE

        widthClass == androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Medium &&
          arrayOf(
            androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Medium,
            androidx.compose.material3.windowsizeclass.WindowHeightSizeClass.Expanded,
          ).contains(heightClass) -> TABLET_PORTRAIT

        widthClass == androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Expanded &&
          heightClass == androidx.compose.material3.windowsizeclass.WindowHeightSizeClass.Medium -> TABLET_LANDSCAPE

        else -> DESKTOP
      }
    }

    /**
     * Maps a [WindowSizeClass] to a [MobileWindowSize] based on width and height size classes.
     *
     * @param windowSizeClass The classification of the current window dimensions.
     * @return The matching [MobileWindowSize] for the given size classes.
     */
    fun fromWindowSizeClass(windowSizeClass: WindowSizeClass): MobileWindowSize = windowSizeClass.let {
      val widthClass = it.windowWidthSizeClass
      val heightClass = it.windowHeightSizeClass
      return when {
        widthClass == WindowWidthSizeClass.COMPACT &&
          arrayOf(
            WindowHeightSizeClass.MEDIUM,
            WindowHeightSizeClass.EXPANDED,
          ).contains(heightClass) -> MOBILE_PORTRAIT

        arrayOf(
          WindowWidthSizeClass.MEDIUM,
          WindowWidthSizeClass.EXPANDED,
        ).contains(widthClass) &&
          heightClass == WindowHeightSizeClass.COMPACT
        -> MOBILE_LANDSCAPE

        widthClass == WindowWidthSizeClass.MEDIUM &&
          arrayOf(
            WindowHeightSizeClass.MEDIUM,
            WindowHeightSizeClass.EXPANDED,
          ).contains(heightClass) -> TABLET_PORTRAIT

        widthClass == WindowWidthSizeClass.EXPANDED && heightClass == WindowHeightSizeClass.MEDIUM -> TABLET_LANDSCAPE

        else -> DESKTOP
      }
    }
  }
}
