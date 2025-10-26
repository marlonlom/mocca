/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.mocca.mobile.settings.domain.SettingActions
import dev.marlonlom.mocca.mobile.settings.domain.SettingsUiState
import dev.marlonlom.mocca.mobile.settings.domain.SettingsViewModel
import dev.marlonlom.mocca.mobile.settings.layout.PortraitSettings
import dev.marlonlom.mocca.mobile.ui.window.MobileWindowSize
import org.koin.androidx.compose.koinViewModel

/**
 * Composable function that displays the Settings screen UI.
 *
 * @author marlonlom
 *
 * @param actions The available setting actions that can be performed.
 * @param mobileWindowSize Defines the current size class of the mobile window for responsive layout.
 * @param showCloseButton Whether to display the close button in the UI.
 * @param onCloseButtonClicked Callback invoked when the close button is clicked.
 * @param settingsViewModel ViewModel that holds the settings state and logic.
 */
@Composable
fun SettingsScreen(
  actions: SettingActions,
  mobileWindowSize: MobileWindowSize,
  showCloseButton: Boolean,
  onCloseButtonClicked: () -> Unit,
  settingsViewModel: SettingsViewModel = koinViewModel(),
) {
  val uiState by settingsViewModel.uiState.collectAsStateWithLifecycle()
  when (uiState) {
    is SettingsUiState.Success -> {
      PortraitSettings(
        mobileWindowSize = mobileWindowSize,
        userSettings = (uiState as SettingsUiState.Success).settings,
        actions = actions,
        showCloseButton = showCloseButton,
        onCloseButtonClicked = onCloseButtonClicked,
        onBooleanSettingToggled = settingsViewModel::updateBooleanSetting,
        onStringSettingToggled = settingsViewModel::updateStringSetting,
      )
    }

    else -> {}
  }
}
