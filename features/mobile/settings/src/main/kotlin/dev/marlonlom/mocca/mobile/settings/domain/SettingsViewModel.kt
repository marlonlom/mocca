/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.settings.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.mocca.core.preferences.repository.PreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel for managing user settings.
 *
 * @author marlonlom
 *
 * @param repository The repository for accessing and modifying user preferences.
 */
class SettingsViewModel(private val repository: PreferencesRepository) : ViewModel() {

  /**
   * Represents the UI state of the settings screen.
   *
   * It emits [SettingsUiState.Success] with the current settings,
   * or [SettingsUiState.Loading] while initially loading.
   */
  val uiState: StateFlow<SettingsUiState> = repository.preferencesFlow
    .map { settings -> SettingsUiState.Success(settings) }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = SettingsUiState.Loading,
    )

  /**
   * Updates a boolean preference.
   *
   * @param preferenceKey The key of the preference to update.
   * @param booleanValue The new boolean value to set.
   */
  fun updateBooleanSetting(preferenceKey: String, booleanValue: Boolean) {
    viewModelScope.launch { repository.toggleBooleanSetting(preferenceKey, booleanValue) }
  }

  /**
   * Updates a string preference.
   *
   * @param preferenceKey The key of the preference to update.
   * @param stringValue The new string value to set.
   */
  fun updateStringSetting(preferenceKey: String, stringValue: String) {
    viewModelScope.launch { repository.updateStringSetting(preferenceKey, stringValue) }
  }
}
