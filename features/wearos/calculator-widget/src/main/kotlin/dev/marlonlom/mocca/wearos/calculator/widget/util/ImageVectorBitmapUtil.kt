/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.widget.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Canvas as ComposeCanvas

/**
 * Remembers and renders an [ImageBitmap] from an [ImageVector] with optional size and tint.
 *
 * @author marlonlom
 *
 * @param imageVector The vector graphic to rasterize.
 * @param size The size of the resulting bitmap in [Dp].
 * @param tint Optional color filter tint to apply.
 * @return The rendered [ImageBitmap].
 */
@Composable
internal fun rememberVectorBitmap(
  imageVector: ImageVector,
  size: Dp = 24.dp,
  tint: androidx.compose.ui.graphics.Color? = null,
): ImageBitmap {
  val density = LocalDensity.current
  val sizePx = with(density) { size.toPx() }.toInt()
  val painter = rememberVectorPainter(imageVector)

  return remember(imageVector, sizePx, tint) {
    val imageBitmap = ImageBitmap(sizePx, sizePx)
    val composeCanvas = ComposeCanvas(imageBitmap)

    val colorFilter = tint?.let {
      androidx.compose.ui.graphics.ColorFilter.tint(it)
    }

    val drawScope = CanvasDrawScope()
    drawScope.draw(
      density = density,
      layoutDirection = LayoutDirection.Ltr,
      canvas = composeCanvas,
      size = androidx.compose.ui.geometry.Size(sizePx.toFloat(), sizePx.toFloat()),
    ) {
      with(painter) {
        draw(size = this@draw.size, colorFilter = colorFilter)
      }
    }

    imageBitmap
  }
}
