/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.settings.slots

import androidx.compose.runtime.Composable
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.feats.settings.UserPreferences
import dev.marlonlom.apps.mocca.feats.settings.parts.ClickableTextRow

@Composable
fun LegalNotesSettingsSlot(
  userPreferences: UserPreferences,
  onOssLicencesSettingLinkClicked: () -> Unit
) {
  DefaultSettingsSlot(
    title = R.string.text_settings_title_legal
  ) {
    ClickableTextRow(
      title = R.string.text_settings_label_oss_licences,
    ) { onOssLicencesSettingLinkClicked() }
  }
}

