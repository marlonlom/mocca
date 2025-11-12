/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.window

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

/**
 * Defines layout profiles based on window width and height size classes.
 *
 * [MobileWindowSize] categorizes UI layouts into form factors such as mobile, tablet, or desktop,
 * considering both screen size and orientation.
 *
 * @property matches Predicate that checks whether the given size classes match this layout profile.
 *
 * @author marlonlom
 */
enum class MobileWindowSize(val matches: (WindowWidthSizeClass, WindowHeightSizeClass) -> Boolean) {
  /** Mobile device in portrait orientation (e.g., smartphone held vertically). */
  MOBILE_PORTRAIT({ w, h ->
    w == WindowWidthSizeClass.Compact && h in WindowHeightSizeClass.Medium..WindowHeightSizeClass.Expanded
  }),

  /** Mobile device in landscape orientation (e.g., smartphone held horizontally). */
  MOBILE_LANDSCAPE({ w, h ->
    w in WindowWidthSizeClass.Medium..WindowWidthSizeClass.Expanded && h == WindowHeightSizeClass.Compact
  }),

  /** Tablet device in portrait orientation. */
  TABLET_PORTRAIT({ w, h ->
    w == WindowWidthSizeClass.Medium && h in WindowHeightSizeClass.Medium..WindowHeightSizeClass.Expanded
  }),

  /** Tablet device in landscape orientation. */
  TABLET_LANDSCAPE({ w, h ->
    w == WindowWidthSizeClass.Expanded && h == WindowHeightSizeClass.Medium
  }),

  /** Desktop or large-screen device, or fallback for uncategorized layouts. */
  DESKTOP({ w, h ->
    w == WindowWidthSizeClass.Expanded && h == WindowHeightSizeClass.Expanded
  }),

  ;

  companion object {

    /**
     * Maps a [WindowSizeClass] to a [MobileWindowSize] based on width and height size classes.
     *
     * @param windowSizeClass The classification of the current window dimensions.
     * @return The matching [MobileWindowSize] for the given size classes.
     */
    fun fromWindowSizeClass(windowSizeClass: WindowSizeClass): MobileWindowSize = windowSizeClass.let { item ->
      entries.firstOrNull { entry ->
        entry.matches(item.widthSizeClass, item.heightSizeClass)
      } ?: DESKTOP
    }
  }
}
