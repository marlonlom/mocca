/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.util

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

/**
 * Window size utility class.
 *
 * @author marlonlom
 *
 * @property windowSizeClass Window size class.
 * @property isLandscape True/False if orientation is landscape.
 * @property isTabletWidth True/False if smallest width is for tablets.
 */
class WindowSizeUtil(
  private val windowSizeClass: WindowSizeClass,
  val isLandscape: Boolean,
  val isTabletWidth: Boolean
) {

  private val isCompactWidthSizeClass get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact
  private val isCompactHeightSizeClass get() = windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

  val isMobileLandscape get() = !isCompactWidthSizeClass and isCompactHeightSizeClass

  val isTabletLandscape get() = isTabletWidth and isLandscape

}
