/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.settings.component

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

@Composable
internal fun SettingLink(
  title: String,
  subtitle: String = "",
  icon: @Composable () -> Unit = {},
  onClicked: () -> Unit = {},
) = Row(
  modifier = Modifier
    .testTag("settingLink")
    .fillMaxWidth()
    .clickable { onClicked() }
    .padding(vertical = 10.dp),
  verticalAlignment = Alignment.CenterVertically,
  horizontalArrangement = Arrangement.spacedBy(ButtonDefaults.IconSpacing),
) {
  icon()
  SettingLabel(
    title = title,
    subtitle = subtitle,
  )
}
