/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.folding

import androidx.compose.material3.adaptive.HingeInfo
import androidx.compose.material3.adaptive.Posture

/**
 * Represents the distinct fold states of a foldable device.
 *
 * @author marlonlom
 */
sealed class FoldState {
  /**
   * The device is fully open and flat, or fully closed.
   *
   * @author marlonlom
   */
  data object Flat : FoldState()

  /**
   * The device is half-opened in a horizontal (tent or tabletop) orientation.
   *
   * @author marlonlom
   */
  data object Tabletop : FoldState()

  /**
   * The device is half-opened in a vertical (book) orientation.
   *
   * @author marlonlom
   */
  data object Book : FoldState()
}

/**
 * A utility object for determining the posture of a foldable device.
 *
 * @author marlonlom
 */
object FoldablePosture {

  /**
   * Determines the current [FoldState] of a device based on its [Posture].
   *
   * @param windowPosture The current posture information of the device.
   * @return The [FoldState] representing the device's posture (Flat, Tabletop, or Book).
   */
  fun getFoldState(windowPosture: Posture): FoldState {
    val firstHinge: HingeInfo? = windowPosture.hingeList.firstOrNull()
    return when {
      firstHinge == null -> FoldState.Flat
      firstHinge.isFlat -> FoldState.Flat
      firstHinge.isVertical && firstHinge.isSeparating -> FoldState.Book
      firstHinge.isVertical.not() && firstHinge.isSeparating -> FoldState.Tabletop
      else -> FoldState.Flat
    }
  }
}
