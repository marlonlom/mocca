/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.window

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
internal class MobileWindowSizeTest {

  @Test
  fun `fromWindowSizeClass returns MOBILE_PORTRAIT for Compact width and (Medium or Expanded) height`() {
    // Test with Medium height
    val windowSizeClassMediumHeight = WindowSizeClass.calculateFromSize(DpSize(360.dp, 720.dp))
    Assert.assertEquals(
      MobileWindowSize.MOBILE_PORTRAIT,
      MobileWindowSize.fromWindowSizeClass(windowSizeClassMediumHeight),
    )

    // Test with Expanded height
    val windowSizeClassExpandedHeight = WindowSizeClass.calculateFromSize(
      DpSize(
        360.dp,
        1024.dp,
      ),
    )
    Assert.assertEquals(
      MobileWindowSize.MOBILE_PORTRAIT,
      MobileWindowSize.fromWindowSizeClass(windowSizeClassExpandedHeight),
    )
  }

  @Test
  fun `fromWindowSizeClass returns MOBILE_LANDSCAPE for (Medium or Expanded) width and Compact height`() {
    // Test with Medium width
    val windowSizeClassMediumWidth = WindowSizeClass.calculateFromSize(
      DpSize(
        720.dp,
        360.dp,
      ),
    )
    Assert.assertEquals(
      MobileWindowSize.MOBILE_LANDSCAPE,
      MobileWindowSize.fromWindowSizeClass(windowSizeClassMediumWidth),
    )

    // Test with Expanded width
    val windowSizeClassExpandedWidth = WindowSizeClass.calculateFromSize(
      DpSize(
        960.dp,
        360.dp,
      ),
    )
    Assert.assertEquals(
      MobileWindowSize.MOBILE_LANDSCAPE,
      MobileWindowSize.fromWindowSizeClass(windowSizeClassExpandedWidth),
    )
  }

  @Test
  fun `fromWindowSizeClass returns TABLET_PORTRAIT for Medium width and (Medium or Expanded) height`() {
    // Test with Medium height
    val windowSizeClassMediumHeight = WindowSizeClass.calculateFromSize(
      DpSize(
        640.dp,
        720.dp,
      ),
    )
    Assert.assertEquals(
      MobileWindowSize.TABLET_PORTRAIT,
      MobileWindowSize.fromWindowSizeClass(windowSizeClassMediumHeight),
    )

    // Test with Expanded height
    val windowSizeClassExpandedHeight = WindowSizeClass.calculateFromSize(
      DpSize(
        640.dp,
        1024.dp,
      ),
    )
    Assert.assertEquals(
      MobileWindowSize.TABLET_PORTRAIT,
      MobileWindowSize.fromWindowSizeClass(windowSizeClassExpandedHeight),
    )
  }

  @Test
  fun `fromWindowSizeClass returns TABLET_LANDSCAPE for Expanded width and Medium height`() {
    val windowSizeClass = WindowSizeClass.calculateFromSize(
      DpSize(
        960.dp,
        720.dp,
      ),
    )
    Assert.assertEquals(
      MobileWindowSize.TABLET_LANDSCAPE,
      MobileWindowSize.fromWindowSizeClass(windowSizeClass),
    )
  }

  @Test
  fun `fromWindowSizeClass returns MOBILE_PORTRAIT for Compact width and Compact height`() {
    // This is one example of a case that should fall into DESKTOP
    val windowSizeClass = WindowSizeClass.calculateFromSize(
      DpSize(
        360.dp,
        480.dp,
      ),
    )
    Assert.assertEquals(
      MobileWindowSize.MOBILE_PORTRAIT,
      MobileWindowSize.fromWindowSizeClass(windowSizeClass),
    )
  }

  @Test
  fun `fromWindowSizeClass returns DESKTOP for Expanded width and Expanded height`() {
    // This is another example of a case that should fall into DESKTOP
    val windowSizeClass = WindowSizeClass.calculateFromSize(
      DpSize(
        960.dp,
        1024.dp,
      ),
    )
    Assert.assertEquals(
      MobileWindowSize.DESKTOP,
      MobileWindowSize.fromWindowSizeClass(windowSizeClass),
    )
  }

  @Test
  fun `fromWindowSizeClass returns MOBILE_PORTRAIT with mocked WindowSizeClass`() {
    val mockWindowSizeClass = mockk<WindowSizeClass>()
    every { mockWindowSizeClass.widthSizeClass } returns WindowWidthSizeClass.Compact
    every { mockWindowSizeClass.heightSizeClass } returns WindowHeightSizeClass.Medium

    Assert.assertEquals(
      MobileWindowSize.MOBILE_PORTRAIT,
      MobileWindowSize.fromWindowSizeClass(mockWindowSizeClass),
    )
  }

  @Test
  fun `fromWindowSizeClass returns MOBILE_LANDSCAPE with mocked WindowSizeClass`() {
    val mockWindowSizeClass = mockk<WindowSizeClass>()
    every { mockWindowSizeClass.widthSizeClass } returns WindowWidthSizeClass.Medium
    every { mockWindowSizeClass.heightSizeClass } returns WindowHeightSizeClass.Compact

    Assert.assertEquals(
      MobileWindowSize.MOBILE_LANDSCAPE,
      MobileWindowSize.fromWindowSizeClass(mockWindowSizeClass),
    )
  }

  @Test
  fun `fromWindowSizeClass returns TABLET_PORTRAIT with mocked WindowSizeClass`() {
    val mockWindowSizeClass = mockk<WindowSizeClass>()
    every { mockWindowSizeClass.widthSizeClass } returns WindowWidthSizeClass.Medium
    every { mockWindowSizeClass.heightSizeClass } returns WindowHeightSizeClass.Medium

    Assert.assertEquals(
      MobileWindowSize.TABLET_PORTRAIT,
      MobileWindowSize.fromWindowSizeClass(mockWindowSizeClass),
    )
  }

  @Test
  fun `fromWindowSizeClass returns TABLET_LANDSCAPE with mocked WindowSizeClass`() {
    val mockWindowSizeClass = mockk<WindowSizeClass>()
    every { mockWindowSizeClass.widthSizeClass } returns WindowWidthSizeClass.Expanded
    every { mockWindowSizeClass.heightSizeClass } returns WindowHeightSizeClass.Medium

    Assert.assertEquals(
      MobileWindowSize.TABLET_LANDSCAPE,
      MobileWindowSize.fromWindowSizeClass(mockWindowSizeClass),
    )
  }

  @Test
  fun `fromWindowSizeClass returns DESKTOP with mocked WindowSizeClass for undefined combination`() {
    val mockWindowSizeClass = mockk<WindowSizeClass>()
    every { mockWindowSizeClass.widthSizeClass } returns WindowWidthSizeClass.Compact
    every { mockWindowSizeClass.heightSizeClass } returns WindowHeightSizeClass.Compact

    Assert.assertEquals(
      MobileWindowSize.DESKTOP,
      MobileWindowSize.fromWindowSizeClass(mockWindowSizeClass),
    )
  }
}
