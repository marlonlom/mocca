/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.mocca.core.preferences.repository.PreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

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
class MainViewModel(private val repository: PreferencesRepository) : ViewModel() {

  /** Main ui state. */
  val uiState: StateFlow<MainUiState> = repository.preferencesFlow
    .map { MainUiState.Success(it) }
    .stateIn(
      scope = viewModelScope,
      initialValue = MainUiState.Loading,
      started = SharingStarted.WhileSubscribed(5_000),
    )

  /**
   * Marks the onboarding process as complete by setting the "is_onboarding" preference to false.
   */
  fun setOnboardingComplete() {
    viewModelScope.launch {
      repository.toggleBooleanSetting("is_onboarding", false)
    }
  }
}
