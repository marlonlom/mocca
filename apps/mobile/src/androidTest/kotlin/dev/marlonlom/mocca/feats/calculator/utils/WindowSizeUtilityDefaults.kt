/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.feats.calculator.utils

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.ui.util.DevicePosture
import dev.marlonlom.mocca.ui.util.WindowSizeInfo

/**
 * Window size utility defaults for ui testing.
 *
 * @author marlonlom
 */
@ExperimentalMaterial3WindowSizeClassApi
object WindowSizeUtilityDefaults {

  /** Mobile portrait reference for window size utility. */
  val mobilePortrait
    get() = WindowSizeInfo(
      windowSizeClass = WindowSizeClass.calculateFromSize(
        DpSize(360.dp, 640.dp)
      ),
      devicePosture = DevicePosture.NormalPosture,
      isLandscape = false,
      isTabletWidth = false,
    )
}
