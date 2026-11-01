/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.glance.wear.GlanceWearWidget
import androidx.glance.wear.WearWidgetData
import androidx.glance.wear.WearWidgetDocument
import androidx.glance.wear.core.WearWidgetParams
import androidx.glance.wear.tooling.preview.SquircleAllWidgetPreviewParams
import androidx.glance.wear.tooling.preview.WearWidgetPreview
import androidx.wear.compose.remote.material3.RemoteMaterialTheme
import dev.marlonlom.mocca.wearos.calculator.widget.layout.MoccaCalculatorWidgetContent
import dev.marlonlom.mocca.wearos.calculator.widget.theme.MoccaCalculatorWidgetTheme
import dev.marlonlom.mocca.wearos.calculator.widget.util.WatchScreenHelper

/**
 * Glance wear widget implementation for Mocca calculator on Wear OS.
 *
 * @author marlonlom
 *
 * @see GlanceWearWidget
 *
 */
internal class MoccaCalculatorWidget : GlanceWearWidget() {

  /**
   * Provides widget data including background brush and layout content for the Wear OS widget.
   *
   * @param context Android context.
   * @param params Wear widget parameters (such as container type).
   * @return [WearWidgetData] containing document structure and background.
   */
  override suspend fun provideWidgetData(context: Context, params: WearWidgetParams): WearWidgetData {
    val isSmallScreen = WatchScreenHelper.isSmallScreen(params)
    return WearWidgetDocument(
      background = MoccaCalculatorWidgetTheme.brush,
      content = {
        RemoteMaterialTheme(
          colorScheme = MoccaCalculatorWidgetTheme.colorScheme,
          content = {
            MoccaCalculatorWidgetContent(isSmallScreen = isSmallScreen)
          },
        )
      },
    )
  }
}

@Preview
@Composable
internal fun MoccaCalculatorWidgetPreview(
  @PreviewParameter(SquircleAllWidgetPreviewParams::class) params: WearWidgetParams,
) = WearWidgetPreview(widget = MoccaCalculatorWidget(), params = params)
