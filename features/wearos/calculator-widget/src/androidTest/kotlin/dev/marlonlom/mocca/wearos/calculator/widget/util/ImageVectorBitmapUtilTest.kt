/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.widget.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.TableChart
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.unit.dp
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertSame
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class ImageVectorBitmapUtilTest {

  @get:Rule
  var rule = createComposeRule()

  @Test
  fun shouldRememberVectorBitmapWithDefaultSizeAndNoTint() {
    with(rule) {
      var imageBitmap: ImageBitmap? = null
      var expectedPx = 0
      setContent {
        val density = LocalDensity.current
        expectedPx = with(density) { 24.dp.toPx() }.toInt()

        imageBitmap = rememberVectorBitmap(
          imageVector = Icons.Rounded.AttachMoney,
        )
      }

      assertNotNull(imageBitmap)
      assertEquals(expectedPx, imageBitmap?.width)
      assertEquals(expectedPx, imageBitmap?.height)
    }
  }

  @Test
  fun shouldRememberVectorBitmapWithCustomSizeAndTint() {
    with(rule) {
      var imageBitmap: ImageBitmap? = null
      var expectedPx = 0
      setContent {
        val density = LocalDensity.current
        expectedPx = with(density) { 32.dp.toPx() }.toInt()

        imageBitmap = rememberVectorBitmap(
          imageVector = Icons.Rounded.TableChart,
          size = 32.dp,
          tint = Color.Red,
        )
      }

      assertNotNull(imageBitmap)
      assertEquals(expectedPx, imageBitmap?.width)
      assertEquals(expectedPx, imageBitmap?.height)
    }
  }

  @Test
  fun shouldRememberVectorBitmapWithSmallWidgetSize() {
    with(rule) {
      var imageBitmap: ImageBitmap? = null
      var expectedPx = 0
      setContent {
        val density = LocalDensity.current
        expectedPx = with(density) { 16.dp.toPx() }.toInt()

        imageBitmap = rememberVectorBitmap(
          imageVector = Icons.Rounded.AttachMoney,
          size = 16.dp,
          tint = Color.Green,
        )
      }

      assertNotNull(imageBitmap)
      assertEquals(expectedPx, imageBitmap?.width)
      assertEquals(expectedPx, imageBitmap?.height)
    }
  }

  @Test
  fun shouldRememberVectorBitmapInstanceAcrossRecompositions() {
    with(rule) {
      var firstBitmap: ImageBitmap? = null
      var secondBitmap: ImageBitmap? = null
      val triggerRecomposition = mutableStateOf(false)

      setContent {
        val trigger = triggerRecomposition.value
        trigger.hashCode()

        firstBitmap = rememberVectorBitmap(
          imageVector = Icons.Rounded.AttachMoney,
          size = 24.dp,
        )
      }

      runOnIdle {
        secondBitmap = firstBitmap
        triggerRecomposition.value = true
      }

      runOnIdle {
        assertSame(firstBitmap, secondBitmap)
      }
    }
  }
}
