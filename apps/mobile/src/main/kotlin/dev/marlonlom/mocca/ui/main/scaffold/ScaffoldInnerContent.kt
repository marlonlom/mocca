/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.main.scaffold

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import dev.marlonlom.mocca.ui.util.DevicePosture

/**
 * Scaffold inner content type sealed interface definition.
 *
 * @author marlonlom
 *
 */
sealed interface ScaffoldInnerContentType {

  /**
   * Single pane scaffold inner content type data object.
   *
   * @author marlonlom
   *
   */
  data object SinglePane : ScaffoldInnerContentType

  /**
   * Two pane scaffold inner content type data object.
   *
   * @author marlonlom
   *
   * @property hingeRatio Hinge ratio as percentage number.
   */
  data class TwoPane(
    val hingeRatio: Float = 0.5f,
  ) : ScaffoldInnerContentType
}

val WindowSizeClass.isExpandedWidth: Boolean
  get() = this.widthSizeClass == WindowWidthSizeClass.Expanded

val WindowSizeClass.isMediumWidth: Boolean
  get() = this.widthSizeClass == WindowWidthSizeClass.Medium

val WindowSizeClass.isCompactWidth: Boolean
  get() = this.widthSizeClass == WindowWidthSizeClass.Compact

val WindowSizeClass.isCompactHeight: Boolean
  get() = this.heightSizeClass == WindowHeightSizeClass.Compact

/**
 * Scaffold inner content classifier single object.
 *
 * @author marlonlom
 *
 */
object ScaffoldInnerContents {

  /**
   * Indicated scaffold inner content type by window size information and device posture.
   *
   * @param windowSizeClass
   * @param devicePosture
   *
   * @return Scaffold inner content type.
   */
  @JvmStatic
  fun indicateInnerContent(windowSizeClass: WindowSizeClass, devicePosture: DevicePosture): ScaffoldInnerContentType =
    when {
      windowSizeClass.isExpandedWidth.and(windowSizeClass.isCompactHeight.not()) -> when (devicePosture) {
        DevicePosture.NormalPosture -> ScaffoldInnerContentType.TwoPane()
        is DevicePosture.ClosedBookPosture -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
        is DevicePosture.ClosedFlipPosture -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
        is DevicePosture.TableTopPosture -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
        is DevicePosture.BookPosture -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
      }

      windowSizeClass.isMediumWidth.and(windowSizeClass.isCompactHeight.not()) -> when (devicePosture) {
        DevicePosture.NormalPosture -> ScaffoldInnerContentType.SinglePane
        is DevicePosture.ClosedBookPosture -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
        is DevicePosture.ClosedFlipPosture -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
        is DevicePosture.TableTopPosture -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
        is DevicePosture.BookPosture -> ScaffoldInnerContentType.TwoPane(devicePosture.hingeRatio)
      }

      windowSizeClass.isCompactHeight.and(
        devicePosture is DevicePosture.NormalPosture,
      ) -> ScaffoldInnerContentType.SinglePane

      else -> ScaffoldInnerContentType.SinglePane
    }
}
