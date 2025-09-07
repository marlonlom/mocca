/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.folding

import androidx.compose.material3.adaptive.HingeInfo
import androidx.compose.material3.adaptive.Posture
import androidx.compose.ui.geometry.Rect
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

internal class FoldablePostureTest {

  @Test
  fun `getFoldState should return Flat when hingeList is empty`() {
    val posture = mockk<Posture>()
    every { posture.hingeList } returns emptyList()
    val foldState = FoldablePosture.getFoldState(posture)
    assertEquals(FoldState.Flat, foldState)
  }

  @Test
  fun `getFoldState should return Flat when hinge is flat`() {
    val hingeInfo = mockk<HingeInfo>()
    every { hingeInfo.isFlat } returns true
    val posture = mockk<Posture>()
    every { posture.hingeList } returns listOf(hingeInfo)
    val foldState = FoldablePosture.getFoldState(posture)
    assertEquals(FoldState.Flat, foldState)
  }

  @Test
  fun `getFoldState should return Book when hinge is vertical and separating`() {
    val hingeInfo = mockk<HingeInfo>()
    every { hingeInfo.isFlat } returns false
    every { hingeInfo.isVertical } returns true
    every { hingeInfo.isSeparating } returns true
    every { hingeInfo.bounds } returns Rect(0.0f, 0.0f, 0.0f, 0.0f)
    val posture = mockk<Posture>()
    every { posture.hingeList } returns listOf(hingeInfo)
    val foldState = FoldablePosture.getFoldState(posture)
    assertEquals(FoldState.Book, foldState)
  }

  @Test
  fun `getFoldState should return Tabletop when hinge is not vertical and separating`() {
    val hingeInfo = mockk<HingeInfo>()
    every { hingeInfo.isFlat } returns false
    every { hingeInfo.isVertical } returns false
    every { hingeInfo.isSeparating } returns true
    every { hingeInfo.bounds } returns Rect(0.0f, 0.0f, 0.0f, 0.0f)
    val posture = mockk<Posture>()
    every { posture.hingeList } returns listOf(hingeInfo)
    val foldState = FoldablePosture.getFoldState(posture)
    assertEquals(FoldState.Tabletop, foldState)
  }

  @Test
  fun `getFoldState should return Flat for other hinge states`() {
    val hingeInfo = mockk<HingeInfo>()
    every { hingeInfo.isFlat } returns false
    every { hingeInfo.isVertical } returns true // or false
    every { hingeInfo.isSeparating } returns false // This is the key for this "other" case
    every { hingeInfo.bounds } returns Rect(0.0f, 0.0f, 0.0f, 0.0f)
    val posture = mockk<Posture>()
    every { posture.hingeList } returns listOf(hingeInfo)
    val foldState = FoldablePosture.getFoldState(posture)
    assertEquals(FoldState.Flat, foldState)
  }
}
