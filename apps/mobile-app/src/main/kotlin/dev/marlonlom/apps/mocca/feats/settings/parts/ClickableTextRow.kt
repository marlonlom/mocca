/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.settings.parts

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

/**
 * Clickable Row with text and icon composable ui.
 *
 * @author marlonlom
 *
 * @param title Title text as string resource.
 * @param subtitle Subtitle text.
 * @param icon Optional icon composable ui.
 * @param onClicked Action for row clicked.
 */
@Composable
fun ClickableTextRow(
  @StringRes title: Int,
  subtitle: String = "",
  icon: @Composable () -> Unit = {},
  onClicked: () -> Unit = {}
) {

  Row(
    modifier = Modifier
      .testTag("ClickableTextRow")
      .fillMaxWidth()
      .clickable { onClicked() }
      .padding(10.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(ButtonDefaults.IconSpacing),
  ) {
    icon()
    SettingLabelText(
      title = title,
      subtitle = subtitle,
    )
  }
}
