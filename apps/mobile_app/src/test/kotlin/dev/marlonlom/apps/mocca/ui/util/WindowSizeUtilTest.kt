/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.util

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalMaterial3WindowSizeClassApi
internal class WindowSizeUtilTest {

  @Test
  fun `Should contain window size for mobile portrait`() {
    val windowSizeUtility = WindowSizeUtil(
      windowSizeClass = WindowSizeClass.calculateFromSize(
        DpSize(360.dp, 640.dp)
      ),
      isLandscape = false,
      isTabletWidth = false
    )

    assertFalse(windowSizeUtility.isMobileLandscape)
    assertFalse(windowSizeUtility.isTabletLandscape)
  }

  @Test
  fun `Should contain window size for mobile landscape`() {
    val windowSizeUtility = WindowSizeUtil(
      windowSizeClass = WindowSizeClass.calculateFromSize(
        DpSize(720.dp, 360.dp)
      ),
      isLandscape = true,
      isTabletWidth = false
    )

    assertTrue(windowSizeUtility.isMobileLandscape)
    assertFalse(windowSizeUtility.isTabletLandscape)
  }

  @Test
  fun `Should contain window size for tablet portrait`() {
    val windowSizeUtility = WindowSizeUtil(
      windowSizeClass = WindowSizeClass.calculateFromSize(
        DpSize(640.dp, 1024.dp)
      ),
      isLandscape = false,
      isTabletWidth = true
    )

    assertFalse(windowSizeUtility.isMobileLandscape)
    assertFalse(windowSizeUtility.isTabletLandscape)
  }

  @Test
  fun `Should contain window size for tablet landscape`() {
    val windowSizeUtility = WindowSizeUtil(
      windowSizeClass = WindowSizeClass.calculateFromSize(
        DpSize(1024.dp, 640.dp)
      ),
      isLandscape = true,
      isTabletWidth = true
    )

    assertFalse(windowSizeUtility.isMobileLandscape)
    assertTrue(windowSizeUtility.isTabletLandscape)
  }
}
