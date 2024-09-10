/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.util

import android.graphics.Rect
import androidx.window.layout.FoldingFeature
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.contracts.ExperimentalContracts

@ExperimentalContracts
internal class DevicePostureDetectorTest {

  private fun getFoldingFeature(
    state: FoldingFeature.State = FoldingFeature.State.FLAT,
    orientation: FoldingFeature.Orientation = FoldingFeature.Orientation.VERTICAL,
  ) = object : FoldingFeature {
    override val bounds: Rect get() = Rect()
    override val isSeparating: Boolean get() = true
    override val occlusionType: FoldingFeature.OcclusionType get() = FoldingFeature.OcclusionType.NONE
    override val orientation: FoldingFeature.Orientation get() = orientation
    override val state: FoldingFeature.State get() = state
  }

  @Test
  fun `Should return a normal posture`() {
    val devicePosture = DevicePostureDetector.fromLayoutInfo(null)
    assertNotNull(devicePosture)
    assertEquals(DevicePosture.NormalPosture, devicePosture)
  }

  @Test
  fun `Should return a book device posture`() {
    val foldingFeature = getFoldingFeature()
    val devicePosture = DevicePostureDetector.fromLayoutInfo(foldingFeature)
    assertNotNull(devicePosture)
    assertTrue(devicePosture is DevicePosture.BookPosture)
  }

  @Test
  fun `Should return a closed book device posture`() {
    val foldingFeature = getFoldingFeature(state = FoldingFeature.State.HALF_OPENED)
    val devicePosture = DevicePostureDetector.fromLayoutInfo(foldingFeature)
    assertNotNull(devicePosture)
    assertTrue(devicePosture is DevicePosture.ClosedBookPosture)
  }

  @Test
  fun `Should return a table top device posture`() {
    val foldingFeature = getFoldingFeature(
      orientation = FoldingFeature.Orientation.HORIZONTAL,
    )
    val devicePosture = DevicePostureDetector.fromLayoutInfo(foldingFeature)
    assertNotNull(devicePosture)
    assertTrue(devicePosture is DevicePosture.TableTopPosture)
  }

  @Test
  fun `Should return a closed flip device posture`() {
    val foldingFeature = getFoldingFeature(
      state = FoldingFeature.State.HALF_OPENED,
      orientation = FoldingFeature.Orientation.HORIZONTAL,
    )
    val devicePosture = DevicePostureDetector.fromLayoutInfo(foldingFeature)
    assertNotNull(devicePosture)
    assertTrue(devicePosture is DevicePosture.ClosedFlipPosture)
  }
}
