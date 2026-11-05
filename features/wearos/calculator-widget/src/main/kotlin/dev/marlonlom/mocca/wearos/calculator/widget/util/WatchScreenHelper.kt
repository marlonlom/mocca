/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.widget.util

import androidx.glance.wear.core.ContainerInfo
import androidx.glance.wear.core.WearWidgetParams

/**
 * Helper object providing screen size and container evaluation utilities for Wear OS widgets.
 *
 * @author marlonlom
 */
internal object WatchScreenHelper {

  /** Threshold in dp below which a Wear OS screen is considered compact/small. */
  private const val SMALL_SCREEN_WIDTH_THRESHOLD_DP = 182f

  /**
   * Determines whether the Wear OS widget has a small screen/container based on [WearWidgetParams].
   *
   * @param params The wear widget parameters containing container type and metrics.
   * @return `true` if the container type is small or width is less than 182dp, `false` otherwise.
   */
  fun isSmallScreen(params: WearWidgetParams): Boolean =
    params.containerType == ContainerInfo.CONTAINER_TYPE_SMALL || params.widthDp < SMALL_SCREEN_WIDTH_THRESHOLD_DP
}
