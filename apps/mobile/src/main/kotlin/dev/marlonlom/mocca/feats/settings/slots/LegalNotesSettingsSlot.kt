/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.feats.settings.slots

import androidx.compose.runtime.Composable
import dev.marlonlom.mocca.R
import dev.marlonlom.mocca.core.preferences.model.UserSettings
import dev.marlonlom.mocca.feats.settings.parts.ClickableTextRow

@Composable
fun LegalNotesSettingsSlot(userPreferences: UserSettings, onOssLicencesSettingLinkClicked: () -> Unit) {
  DefaultSettingsSlot(
    title = R.string.text_settings_title_legal,
  ) {
    ClickableTextRow(
      title = R.string.text_settings_label_oss_licences,
      onClicked = { onOssLicencesSettingLinkClicked() },
    )
  }
}
