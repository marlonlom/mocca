/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.util

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import dev.marlonlom.mocca.ui.main.scaffold.ScaffoldInnerContents

/**
 * Window size information utility class.
 *
 * @author marlonlom
 *
 * @property windowSizeClass Window size class.
 * @property devicePosture Device posture.
 * @property isLandscape True/False is device is in landscape orientation.
 * @property isTabletWidth True/False is device width is for tablets.
 */
data class WindowSizeInfo(
  val windowSizeClass: WindowSizeClass,
  val devicePosture: DevicePosture,
  val isLandscape: Boolean,
  val isTabletWidth: Boolean,
) {
  private val isCompactWidthSizeClass get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

  private val isCompactHeightSizeClass get() = windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

  val isMobileLandscape get() = !isCompactWidthSizeClass and isCompactHeightSizeClass

  val isTabletLandscape get() = isTabletWidth and isLandscape

  val scaffoldInnerContentType get() = ScaffoldInnerContents.indicateInnerContent(windowSizeClass, devicePosture)
}
