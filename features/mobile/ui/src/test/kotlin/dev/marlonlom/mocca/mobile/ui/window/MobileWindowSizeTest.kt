/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.window

import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

internal class MobileWindowSizeTest {

  @Test
  fun `fromWindowSizeClass returns MOBILE_PORTRAIT for Compact width and (Medium or Expanded) height`() {
    // Test with Medium height
    val windowSizeClassMediumHeight = WindowSizeClass.compute(
      360.0f,
      720.0f,
    )
    Assert.assertEquals(
      MobileWindowSize.MOBILE_PORTRAIT,
      MobileWindowSize.fromWindowSizeClass(windowSizeClassMediumHeight),
    )

    // Test with Expanded height
    val windowSizeClassExpandedHeight = WindowSizeClass.compute(
      360.0f,
      1024.0f,
    )
    Assert.assertEquals(
      MobileWindowSize.MOBILE_PORTRAIT,
      MobileWindowSize.fromWindowSizeClass(windowSizeClassExpandedHeight),
    )
  }

  @Test
  fun `fromWindowSizeClass returns MOBILE_LANDSCAPE for (Medium or Expanded) width and Compact height`() {
    // Test with Medium width
    val windowSizeClassMediumWidth = WindowSizeClass.compute(
      720.0f,
      360.0f,
    )
    Assert.assertEquals(
      MobileWindowSize.MOBILE_LANDSCAPE,
      MobileWindowSize.fromWindowSizeClass(windowSizeClassMediumWidth),
    )

    // Test with Expanded width
    val windowSizeClassExpandedWidth = WindowSizeClass.compute(
      960.0f,
      360.0f,
    )
    Assert.assertEquals(
      MobileWindowSize.MOBILE_LANDSCAPE,
      MobileWindowSize.fromWindowSizeClass(windowSizeClassExpandedWidth),
    )
  }

  @Test
  fun `fromWindowSizeClass returns TABLET_PORTRAIT for Medium width and (Medium or Expanded) height`() {
    // Test with Medium height
    val windowSizeClassMediumHeight = WindowSizeClass.compute(
      640.0f,
      720.0f,
    )
    Assert.assertEquals(
      MobileWindowSize.TABLET_PORTRAIT,
      MobileWindowSize.fromWindowSizeClass(windowSizeClassMediumHeight),
    )

    // Test with Expanded height
    val windowSizeClassExpandedHeight = WindowSizeClass.compute(
      640.0f,
      1024.0f,
    )
    Assert.assertEquals(
      MobileWindowSize.TABLET_PORTRAIT,
      MobileWindowSize.fromWindowSizeClass(windowSizeClassExpandedHeight),
    )
  }

  @Test
  fun `fromWindowSizeClass returns TABLET_LANDSCAPE for Expanded width and Medium height`() {
    val windowSizeClass = WindowSizeClass.compute(
      960.0f,
      720.0f,
    )
    Assert.assertEquals(
      MobileWindowSize.TABLET_LANDSCAPE,
      MobileWindowSize.fromWindowSizeClass(windowSizeClass),
    )
  }

  @Test
  fun `fromWindowSizeClass returns MOBILE_PORTRAIT for Compact width and Compact height`() {
    // This is one example of a case that should fall into DESKTOP
    val windowSizeClass = WindowSizeClass.compute(
      360.0f,
      480.0f,
    )
    Assert.assertEquals(
      MobileWindowSize.MOBILE_PORTRAIT,
      MobileWindowSize.fromWindowSizeClass(windowSizeClass),
    )
  }

  @Test
  fun `fromWindowSizeClass returns DESKTOP for Expanded width and Expanded height`() {
    // This is another example of a case that should fall into DESKTOP
    val windowSizeClass = WindowSizeClass.compute(
      960.0f,
      1024.0f,
    )
    Assert.assertEquals(
      MobileWindowSize.DESKTOP,
      MobileWindowSize.fromWindowSizeClass(windowSizeClass),
    )
  }

  @Test
  fun `fromWindowSizeClass returns MOBILE_PORTRAIT with mocked WindowSizeClass`() {
    val mockWindowSizeClass = mockk<WindowSizeClass>()
    every { mockWindowSizeClass.windowWidthSizeClass } returns WindowWidthSizeClass.COMPACT
    every { mockWindowSizeClass.windowHeightSizeClass } returns WindowHeightSizeClass.MEDIUM

    Assert.assertEquals(
      MobileWindowSize.MOBILE_PORTRAIT,
      MobileWindowSize.fromWindowSizeClass(mockWindowSizeClass),
    )
  }

  @Test
  fun `fromWindowSizeClass returns MOBILE_LANDSCAPE with mocked WindowSizeClass`() {
    val mockWindowSizeClass = mockk<WindowSizeClass>()
    every { mockWindowSizeClass.windowWidthSizeClass } returns WindowWidthSizeClass.MEDIUM
    every { mockWindowSizeClass.windowHeightSizeClass } returns WindowHeightSizeClass.COMPACT

    Assert.assertEquals(
      MobileWindowSize.MOBILE_LANDSCAPE,
      MobileWindowSize.fromWindowSizeClass(mockWindowSizeClass),
    )
  }

  @Test
  fun `fromWindowSizeClass returns TABLET_PORTRAIT with mocked WindowSizeClass`() {
    val mockWindowSizeClass = mockk<WindowSizeClass>()
    every { mockWindowSizeClass.windowWidthSizeClass } returns WindowWidthSizeClass.MEDIUM
    every { mockWindowSizeClass.windowHeightSizeClass } returns WindowHeightSizeClass.MEDIUM

    Assert.assertEquals(
      MobileWindowSize.TABLET_PORTRAIT,
      MobileWindowSize.fromWindowSizeClass(mockWindowSizeClass),
    )
  }

  @Test
  fun `fromWindowSizeClass returns TABLET_LANDSCAPE with mocked WindowSizeClass`() {
    val mockWindowSizeClass = mockk<WindowSizeClass>()
    every { mockWindowSizeClass.windowWidthSizeClass } returns WindowWidthSizeClass.EXPANDED
    every { mockWindowSizeClass.windowHeightSizeClass } returns WindowHeightSizeClass.MEDIUM

    Assert.assertEquals(
      MobileWindowSize.TABLET_LANDSCAPE,
      MobileWindowSize.fromWindowSizeClass(mockWindowSizeClass),
    )
  }

  @Test
  fun `fromWindowSizeClass returns DESKTOP with mocked WindowSizeClass for undefined combination`() {
    val mockWindowSizeClass = mockk<WindowSizeClass>()
    every { mockWindowSizeClass.windowWidthSizeClass } returns WindowWidthSizeClass.COMPACT
    every { mockWindowSizeClass.windowHeightSizeClass } returns WindowHeightSizeClass.COMPACT

    Assert.assertEquals(
      MobileWindowSize.DESKTOP,
      MobileWindowSize.fromWindowSizeClass(mockWindowSizeClass),
    )
  }
}
