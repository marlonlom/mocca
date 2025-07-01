/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.settings.domain

import dev.marlonlom.mocca.core.preferences.model.UserSettings

/**
 * Represents the various states of the Settings UI.
 *
 * @author marlonlom
 *
 */
sealed interface SettingsUiState {

  /**
   * Represents the state where the settings are currently being loaded.
   *
   * @author marlonlom
   *
   */
  data object Loading : SettingsUiState

  /**
   * Represents the state where the settings have been successfully loaded.
   *
   * @author marlonlom
   *
   * @property settings The user settings data.
   */
  data class Success(val settings: UserSettings) : SettingsUiState
}
