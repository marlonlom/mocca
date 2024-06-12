/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.feats.settings.parts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.R
import dev.marlonlom.mocca.ui.main.scaffold.ScaffoldInnerContentType
import dev.marlonlom.mocca.ui.main.scaffold.isExpandedWidth
import dev.marlonlom.mocca.ui.util.WindowSizeInfo

/**
 * Settings header title text composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeInfo Windows size info utility data.
 */
@Composable
fun SettingsHeaderText(
  windowSizeInfo: WindowSizeInfo
) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(
        top = when {
          (windowSizeInfo.scaffoldInnerContentType is ScaffoldInnerContentType.TwoPane)
            .and(windowSizeInfo.windowSizeClass.isExpandedWidth)
            .and(windowSizeInfo.isTabletLandscape) -> 80.dp

          windowSizeInfo.isLandscape -> 20.dp

          else -> 40.dp
        },
        bottom = 20.dp
      ),
    text = stringResource(R.string.text_settings_headline),
    style = MaterialTheme.typography.headlineLarge,
    fontWeight = FontWeight.Bold
  )
}
