/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.util

import androidx.wear.protolayout.DimensionBuilders
import androidx.wear.protolayout.DimensionBuilders.WrappedDimensionProp

/**
 * Utility object providing predefined dimension properties for wearos layout components.
 *
 * @author marlonlom
 *
 */
internal object DpProps {

  /**
   * Returns a wrapped dimension property with a minimum size of 24 density-independent pixels.
   */
  fun dp24(): DimensionBuilders.ContainerDimension =
    WrappedDimensionProp.Builder().setMinimumSize(DimensionBuilders.DpProp.Builder(24f).build()).build()
}
