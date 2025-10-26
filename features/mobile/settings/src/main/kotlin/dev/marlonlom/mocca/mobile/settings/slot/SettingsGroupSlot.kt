/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.settings.slot

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

/**
 * Displays a grouped section within the settings screen, with a header and custom content.
 *
 * @author marlonlom
 *
 * @param headerText The string resource ID for the group header title.
 * @param content The composable content to be displayed within the group.
 */
@Composable
internal fun SettingsGroupSlot(@StringRes headerText: Int, content: @Composable (ColumnScope.() -> Unit)) = Column {
  Text(
    modifier = Modifier
      .padding(top = 20.dp)
      .fillMaxWidth(),
    color = MaterialTheme.colorScheme.onSurface,
    text = stringResource(headerText).uppercase(),
    style = MaterialTheme.typography.titleSmall,
  )
  content()
  Spacer(Modifier.height(20.dp))
}
