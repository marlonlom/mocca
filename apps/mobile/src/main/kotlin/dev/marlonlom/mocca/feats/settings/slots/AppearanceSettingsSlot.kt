/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.feats.settings.slots

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import dev.marlonlom.mocca.R
import dev.marlonlom.mocca.core.preferences.model.UserSettings
import dev.marlonlom.mocca.feats.settings.parts.BooleanSettingSwitch

/**
 * Appearance settings slot composable ui.
 *
 * @author marlonlom
 *
 * @param userPreferences User preferences.
 * @param onBooleanSettingChanged Action for boolean setting value changed.
 */
@Composable
fun AppearanceSettingsSlot(userPreferences: UserSettings, onBooleanSettingChanged: (String, Boolean) -> Unit) {
  DefaultSettingsSlot(
    title = R.string.text_settings_title_appearance,
  ) {
    // Switch: dark theme
    BooleanSettingSwitch(
      title = R.string.text_settings_label_dark_theme,
      subtitle = R.string.text_settings_hint_system_dark_theme,
      checked = userPreferences.useDarkTheme,
      onChecked = { checked ->
        onBooleanSettingChanged("dark_theme", checked)
      },
      enabled = !isSystemInDarkTheme(),
      showSubtitle = isSystemInDarkTheme(),
    )
    // Switch: dynamic colors
    BooleanSettingSwitch(
      title = R.string.text_settings_label_dynamic_color,
      subtitle = R.string.text_settings_hint_dynamic_color,
      checked = userPreferences.useDynamicColor,
      onChecked = { checked ->
        onBooleanSettingChanged("dynamic_colors", checked)
      },
    )
  }
}
