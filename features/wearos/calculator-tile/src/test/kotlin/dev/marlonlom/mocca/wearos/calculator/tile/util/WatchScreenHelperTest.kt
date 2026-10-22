/*
 * Copyright 2026 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.util

import androidx.wear.protolayout.DeviceParametersBuilders
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

internal class WatchScreenHelperTest {

  @Test
  fun `Should return true when screen width is less than 200dp`() {
    val deviceParameters = mockk<DeviceParametersBuilders.DeviceParameters> {
      every { screenWidthDp } returns 190
    }
    assertTrue(WatchScreenHelper.isSmallScreen(deviceParameters))
  }

  @Test
  fun `Should return false when screen width is equal to 200dp`() {
    val deviceParameters = mockk<DeviceParametersBuilders.DeviceParameters> {
      every { screenWidthDp } returns 200
    }
    assertFalse(WatchScreenHelper.isSmallScreen(deviceParameters))
  }

  @Test
  fun `Should return false when screen width is greater than 200dp`() {
    val deviceParameters = mockk<DeviceParametersBuilders.DeviceParameters> {
      every { screenWidthDp } returns 220
    }
    assertFalse(WatchScreenHelper.isSmallScreen(deviceParameters))
  }
}
