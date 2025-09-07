/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.feats.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.mocca.core.preferences.repository.PreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Settings view model class.
 *
 * @author marlonlom
 *
 * @property repository Settings repository.
 */
class SettingsViewModel(private val repository: PreferencesRepository) : ViewModel() {

  /** Settings ui state. */
  val settingsUiState: StateFlow<SettingsUiState> = repository.preferencesFlow
    .map { prefs -> SettingsUiState.Success(prefs) }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.Eagerly,
      initialValue = SettingsUiState.Loading,
    )

  /**
   * Updates boolean preference using provided key.
   *
   * @param preferenceKey Preference key text.
   * @param booleanValue true/false.
   */
  fun toggleBooleanInfo(preferenceKey: String, booleanValue: Boolean) {
    viewModelScope.launch {
      repository.toggleBooleanSetting(
        booleanKey = preferenceKey,
        newBooleanValue = booleanValue,
      )
    }
  }

  /**
   * Updates boolean preference using provided key.
   *
   * @param preferenceKey Preference key text.
   * @param stringValue String preference value for update.
   */
  fun updateStringInfo(preferenceKey: String, stringValue: String) {
    viewModelScope.launch {
      repository.updateStringSetting(
        stringKey = preferenceKey,
        newStringValue = stringValue,
      )
    }
  }

}
