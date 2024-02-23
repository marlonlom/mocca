/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.settings.slots

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Feedback
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.feats.settings.UserPreferences
import dev.marlonlom.apps.mocca.feats.settings.parts.ClickableTextRow

@Composable
fun AboutSettingsSlot(
  userPreferences: UserPreferences,
  onFeedbackSettingLinkClicked: () -> Unit
) {
  DefaultSettingsSlot(
    title = R.string.text_settings_title_app_info
  ) {
    ClickableTextRow(
      title = R.string.text_settings_label_feedback,
      icon = {
        Icon(
          imageVector = Icons.TwoTone.Feedback,
          contentDescription = null,
          modifier = Modifier.size(24.dp),
          tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(
          Modifier
            .size(20.dp)
        )
      },
      onClicked = { onFeedbackSettingLinkClicked() },
    )
    ClickableTextRow(
      title = R.string.text_settings_label_app_version,
      subtitle = userPreferences.appVersion,
      icon = {
        Icon(
          imageVector = Icons.TwoTone.Info,
          contentDescription = null,
          modifier = Modifier.size(24.dp),
          tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(
          Modifier
            .size(20.dp)
        )
      },
    )
  }
}