/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.settings

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Settings view model class.
 *
 * @author marlonlom
 *
 * @property repository Settings repository.
 */
class SettingsViewModel(
  private val repository: SettingsRepository
) : ViewModel() {

  /** Settings ui state. */
  val settingsUiState: StateFlow<UserPreferences> = repository.settingsFlow.stateIn(
    scope = viewModelScope,
    started = SharingStarted.Eagerly,
    initialValue = Default
  )

  /**
   * Updates boolean preference using provided key.
   *
   * @param preferenceKey Preference key text.
   * @param booleanValue true/false.
   */
  fun toggleBooleanPreference(
    preferenceKey: String,
    booleanValue: Boolean
  ) {
    viewModelScope.launch {
      repository.toggleBooleanPreference(
        preferenceKey = preferenceKey,
        newValue = booleanValue
      )
    }
  }

  companion object {

    /** Default user preferences instance. */
    val Default = UserPreferences(
      aboutEfectyUrl = "",
      appVersion = "",
      darkTheme = false,
      dynamicColors = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    )

    /**
     * Factory for creating calculator view model instances.
     *
     * @param repository Settings repository.
     */
    fun factory(repository: SettingsRepository) = object : ViewModelProvider.Factory {
      @Suppress("UNCHECKED_CAST")
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SettingsViewModel(repository) as T
      }
    }
  }

}
