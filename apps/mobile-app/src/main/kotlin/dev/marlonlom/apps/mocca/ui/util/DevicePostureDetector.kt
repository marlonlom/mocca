/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.util

import android.graphics.Rect
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowLayoutInfo
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Information about the posture of the device.
 *
 * @author marlonlom
 */
sealed interface DevicePosture {

  /**
   * Normal posture of the device.
   *
   * @author marlonlom
   */
  data object NormalPosture : DevicePosture

  /**
   * Book posture of the device.
   *
   * @author marlonlom
   *
   * @property bounds folding feature rectangle bounds.
   * @property hingeRatio hinge ratio for screen separation.
   * @property orientation folding feature axis orientation indication.
   */
  data class BookPosture(
    val bounds: Rect,
    val hingeRatio: Float,
    val orientation: FoldingFeature.Orientation,
  ) : DevicePosture

  /**
   * Closed Book posture of the device.
   *
   * @author marlonlom
   *
   * @property bounds folding feature rectangle bounds.
   * @property hingeRatio hinge ratio for screen separation.
   * @property orientation folding feature axis orientation indication.
   */
  data class ClosedBookPosture(
    val bounds: Rect,
    val hingeRatio: Float,
    val orientation: FoldingFeature.Orientation,
  ) : DevicePosture

  /**
   * Table top posture of the device.
   *
   * @author marlonlom
   *
   * @property bounds folding feature rectangle bounds.
   * @property hingeRatio hinge ratio for screen separation.
   * @property orientation folding feature axis orientation indication.
   */
  data class TableTopPosture(
    val bounds: Rect,
    val hingeRatio: Float,
    val orientation: FoldingFeature.Orientation,
  ) : DevicePosture

  /**
   * Closed flip posture of the device.
   *
   * @author marlonlom
   *
   * @property bounds folding feature rectangle bounds.
   * @property hingeRatio hinge ratio for screen separation.
   * @property orientation folding feature axis orientation indication.
   */
  data class ClosedFlipPosture(
    val bounds: Rect,
    val hingeRatio: Float,
    val orientation: FoldingFeature.Orientation,
  ) : DevicePosture

}

/**
 * Device posture detector single object that uses [WindowLayoutInfo] information.
 *
 * @author marlonlom
 */
@ExperimentalContracts
object DevicePostureDetector {

  /**
   * Returns the device posture for selected layout information.
   *
   * @param layoutInfo Window layout information.
   */
  @JvmStatic
  fun fromLayoutInfo(layoutInfo: WindowLayoutInfo): DevicePosture {
    val foldingFeature =
      layoutInfo.displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()

    return when {

      isBookPosture(foldingFeature) -> {
        DevicePosture.BookPosture(
          bounds = foldingFeature.bounds,
          hingeRatio = getHingeRatio(foldingFeature),
          orientation = foldingFeature.orientation,
        )
      }

      isTableTopPosture(foldingFeature) -> {
        DevicePosture.TableTopPosture(
          bounds = foldingFeature.bounds,
          hingeRatio = getHingeRatio(foldingFeature),
          orientation = foldingFeature.orientation,
        )
      }

      isClosedBookPosture(foldingFeature) -> {
        DevicePosture.ClosedBookPosture(
          bounds = foldingFeature.bounds,
          hingeRatio = getHingeRatio(foldingFeature),
          orientation = foldingFeature.orientation,
        )
      }

      isClosedFlipPosture(foldingFeature) -> {
        DevicePosture.ClosedFlipPosture(
          bounds = foldingFeature.bounds,
          hingeRatio = getHingeRatio(foldingFeature),
          orientation = foldingFeature.orientation,
        )
      }

      else -> DevicePosture.NormalPosture
    }
  }

  private fun getHingeRatio(
    foldFeature: FoldingFeature
  ): Float = when (foldFeature.orientation) {
    FoldingFeature.Orientation.VERTICAL -> {
      val screenWidth = foldFeature.bounds.left + foldFeature.bounds.right
      foldFeature.bounds.left.toFloat() / screenWidth.toFloat()
    }

    else -> {
      val screenHeight = foldFeature.bounds.top + foldFeature.bounds.bottom
      foldFeature.bounds.top.toFloat() / screenHeight.toFloat()
    }
  }

  private fun isBookPosture(foldFeature: FoldingFeature?): Boolean {
    contract { returns(true) implies (foldFeature != null) }
    return foldFeature?.state == FoldingFeature.State.FLAT && foldFeature.isSeparating &&
      foldFeature.orientation == FoldingFeature.Orientation.VERTICAL
  }

  private fun isTableTopPosture(foldFeature: FoldingFeature?): Boolean {
    contract { returns(true) implies (foldFeature != null) }
    return foldFeature?.state == FoldingFeature.State.FLAT && foldFeature.isSeparating &&
      foldFeature.orientation == FoldingFeature.Orientation.HORIZONTAL
  }

  private fun isClosedBookPosture(foldFeature: FoldingFeature?): Boolean {
    contract { returns(true) implies (foldFeature != null) }
    return foldFeature?.state == FoldingFeature.State.HALF_OPENED &&
      foldFeature.orientation == FoldingFeature.Orientation.VERTICAL
  }

  private fun isClosedFlipPosture(foldFeature: FoldingFeature?): Boolean {
    contract { returns(true) implies (foldFeature != null) }
    return foldFeature?.state == FoldingFeature.State.HALF_OPENED &&
      foldFeature.orientation == FoldingFeature.Orientation.HORIZONTAL
  }
}
