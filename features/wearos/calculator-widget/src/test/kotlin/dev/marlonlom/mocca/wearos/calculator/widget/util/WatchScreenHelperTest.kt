/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.widget.util

import androidx.glance.wear.core.ContainerInfo
import androidx.glance.wear.core.WearWidgetParams
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

internal class WatchScreenHelperTest {

  @Test
  fun `Should return true when container type is small`() {
    val params = mockk<WearWidgetParams> {
      every { containerType } returns ContainerInfo.CONTAINER_TYPE_SMALL
      every { widthDp } returns 220f
    }
    assertFalse(WatchScreenHelper.isSmallScreen(params))
  }

  @Test
  fun `Should return true when screen width is less than 182dp`() {
    val params = mockk<WearWidgetParams> {
      every { containerType } returns ContainerInfo.CONTAINER_TYPE_LARGE
      every { widthDp } returns 170f
    }
    assertTrue(WatchScreenHelper.isSmallScreen(params))
  }

  @Test
  fun `Should return false when container type is large and width is equal to 182dp`() {
    val params = mockk<WearWidgetParams> {
      every { containerType } returns ContainerInfo.CONTAINER_TYPE_LARGE
      every { widthDp } returns 182f
    }
    assertFalse(WatchScreenHelper.isSmallScreen(params))
  }

  @Test
  fun `Should return false when container type is large and width is greater than 182dp`() {
    val params = mockk<WearWidgetParams> {
      every { containerType } returns ContainerInfo.CONTAINER_TYPE_LARGE
      every { widthDp } returns 220f
    }
    assertFalse(WatchScreenHelper.isSmallScreen(params))
  }
}
