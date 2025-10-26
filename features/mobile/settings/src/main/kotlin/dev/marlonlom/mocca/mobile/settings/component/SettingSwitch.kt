/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.settings.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

/**
 * Displays a settings item with a title, optional subtitle, and a toggle switch.
 *
 * @author marlonlom
 *
 * @param title The main text label describing the setting.
 * @param subtitle Additional descriptive text shown below the title.
 * @param checked Whether the switch is currently turned on.
 * @param onChecked Callback invoked when the switch state changes.
 * @param enabled Whether the switch is interactive or disabled.
 */
@Composable
internal fun SettingSwitch(
  title: String,
  subtitle: String,
  checked: Boolean,
  onChecked: (Boolean) -> Unit,
  enabled: Boolean = true,
) = Row(
  modifier = Modifier
    .fillMaxWidth()
    .padding(top = 10.dp),
  verticalAlignment = Alignment.CenterVertically,
  horizontalArrangement = Arrangement.SpaceBetween,
) {
  SettingLabel(
    title = title,
    subtitle = subtitle,
    modifier = Modifier.weight(1 / 3f),
  )
  Switch(
    modifier = Modifier.testTag("settingSwitch"),
    enabled = enabled,
    checked = checked,
    onCheckedChange = { onChecked(it) },
  )
}
