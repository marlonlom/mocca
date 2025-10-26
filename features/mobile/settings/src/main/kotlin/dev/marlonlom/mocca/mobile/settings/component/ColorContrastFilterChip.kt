/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.settings.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.BrightnessAuto
import androidx.compose.material.icons.twotone.BrightnessHigh
import androidx.compose.material.icons.twotone.BrightnessMedium
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.core.preferences.model.UserColorContrasts

/**
 * A composable filter chip used for selecting a color contrast option.
 *
 * @author marlonlom
 *
 * @param chipLabel The text displayed on the chip.
 * @param chipValue A value associated with the chip (not shown visually).
 * @param chipEnabled Whether the chip is enabled or disabled.
 * @param chipSelected Whether the chip is currently selected.
 * @param onChipClicked Callback invoked when the chip is clicked.
 * @param modifier Modifier to be applied to the chip layout.
 */
@Composable
internal fun ColorContrastFilterChip(
  chipLabel: String,
  chipValue: String,
  chipEnabled: Boolean,
  chipSelected: Boolean,
  onChipClicked: () -> Unit,
  modifier: Modifier = Modifier,
) = FilterChip(
  modifier = modifier,
  selected = chipSelected,
  enabled = chipEnabled,
  onClick = onChipClicked,
  leadingIcon = {
    Icon(
      imageVector = when (UserColorContrasts.valueOf(chipValue)) {
        UserColorContrasts.HIGH -> Icons.TwoTone.BrightnessHigh
        UserColorContrasts.MEDIUM -> Icons.TwoTone.BrightnessMedium
        else -> Icons.TwoTone.BrightnessAuto
      },
      contentDescription = null,
    )
  },
  label = { Text(text = chipLabel) },
  border = FilterChipDefaults.filterChipBorder(
    selected = chipSelected,
    enabled = chipEnabled,
    borderColor = MaterialTheme.colorScheme.onSurface,
    borderWidth = 1.dp,
    selectedBorderColor = MaterialTheme.colorScheme.onPrimaryContainer,
    selectedBorderWidth = 1.dp,
    disabledBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    disabledSelectedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
  ),
  colors = FilterChipDefaults.filterChipColors(
    iconColor = MaterialTheme.colorScheme.onSurface,
    labelColor = MaterialTheme.colorScheme.onSurface,
    containerColor = MaterialTheme.colorScheme.surface,
    selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
    selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
    selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    disabledLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
    disabledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.38f),
  ),
)
