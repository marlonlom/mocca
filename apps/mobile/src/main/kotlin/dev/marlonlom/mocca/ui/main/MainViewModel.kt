/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.marlonlom.mocca.feats.settings.SettingsRepository
import dev.marlonlom.mocca.feats.settings.UserPreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Main activity view model.
 *
 * @author marlonlom
 *
 * @constructor
 * Constructs an instance of main activity view model.
 *
 * @param repository Settings repository.
 */
class MainViewModel(
  repository: SettingsRepository,
) : ViewModel() {

  /** Main ui state. */
  val uiState: StateFlow<MainActivityUiState> = repository.settingsFlow
    .map { MainActivityUiState.Success(it) }
    .stateIn(
      scope = viewModelScope,
      initialValue = MainActivityUiState.Loading,
      started = SharingStarted.WhileSubscribed(5_000),
    )

  companion object {

    /**
     * Factory for creating main view model instances.
     *
     * @param repository Settings repository.
     */
    fun factory(repository: SettingsRepository) = object : ViewModelProvider.Factory {
      @Suppress("UNCHECKED_CAST")
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
      }
    }
  }
}

/**
 * Main activity ui state sealed class.
 *
 * @author marlonlom
 */
sealed interface MainActivityUiState {

  /** Main activity ui state: Loading */
  data object Loading : MainActivityUiState

  /**
   * Main activity ui state: Success
   *
   * @property userData User preferences data.
   */
  data class Success(
    val userData: UserPreferences,
  ) : MainActivityUiState
}
