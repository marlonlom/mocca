/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.settings.component

import android.content.res.Resources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.core.preferences.model.UserColorContrasts
import dev.marlonlom.mocca.mobile.settings.R

/**
 * Displays a selector for choosing a color contrast option in the settings.
 *
 * @author marlonlom
 *
 * @param selectedColorContrast The currently selected color contrast option.
 * @param slotEnabled Whether the selector is enabled and interactive.
 * @param onColorContrastSelected Callback invoked when a new color contrast option is selected.
 * @param resources The [Resources] instance used to access localized strings or assets.
 */
@Composable
internal fun ColorContrastSelector(
  selectedColorContrast: String,
  slotEnabled: Boolean,
  onColorContrastSelected: (String) -> Unit,
  resources: Resources = LocalResources.current,
) {
  val contrastLabels: Array<String> = remember {
    resources.getStringArray(R.array.array_color_contrasts)
  }
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 10.dp),
  ) {
    Text(
      modifier = Modifier.fillMaxWidth(),
      text = stringResource(R.string.text_color_contrast),
      color = MaterialTheme.colorScheme.onSurface
        .copy(alpha = if (slotEnabled) 1.0f else 0.38f),
    )
    Row(
      horizontalArrangement = Arrangement.spacedBy(10.dp),
      modifier = Modifier.fillMaxWidth(),
    ) {
      UserColorContrasts.entries.forEach { contrast ->
        ColorContrastFilterChip(
          modifier = Modifier.weight(1.0f),
          chipLabel = contrastLabels[contrast.ordinal],
          chipValue = contrast.name,
          chipEnabled = slotEnabled,
          chipSelected = selectedColorContrast == contrast.name,
          onChipClicked = { onColorContrastSelected(contrast.name) },
        )
      }
    }
  }
}
