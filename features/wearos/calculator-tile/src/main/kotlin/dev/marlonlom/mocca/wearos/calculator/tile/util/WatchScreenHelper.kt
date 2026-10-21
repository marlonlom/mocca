/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.util

import androidx.wear.protolayout.DeviceParametersBuilders

/**
 * Helper object providing screen size evaluation utilities for Wear OS tiles.
 *
 * @author marlonlom
 */
internal object WatchScreenHelper {

  /** Threshold in dp below which a Wear OS screen is considered compact/small. */
  private const val SMALL_SCREEN_WIDTH_THRESHOLD_DP = 200

  /**
   * Determines whether the Wear OS device has a small screen based on its width in DP.
   *
   * @param deviceParameters The device parameters containing screen metrics.
   * @return `true` if the screen width is less than 200dp, `false` otherwise.
   */
  fun isSmallScreen(deviceParameters: DeviceParametersBuilders.DeviceParameters): Boolean =
    deviceParameters.screenWidthDp < SMALL_SCREEN_WIDTH_THRESHOLD_DP
}
