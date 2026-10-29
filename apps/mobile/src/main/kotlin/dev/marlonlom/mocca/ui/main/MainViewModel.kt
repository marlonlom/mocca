/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.mocca.core.preferences.repository.PreferencesRepository
import dev.marlonlom.mocca.ui.shortcut.AppShortcut
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
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

  /** Internal flow for tracking whether fees screen should be opened via shortcut. */
  private val shouldOpenFees = MutableStateFlow(false)

  /** Main ui state. */
  val uiState: StateFlow<MainUiState> = combine(
    repository.preferencesFlow,
    shouldOpenFees,
  ) { prefs, openFees ->
    MainUiState.Success(userData = prefs, shouldOpenFees = openFees)
  }.stateIn(
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

  /**
   * Handles the app shortcut action based on the given [shortcutId].
   *
   * @param shortcutId The shortcut identifier string received from intent.
   */
  fun handleShortcut(shortcutId: String?) {
    when (AppShortcut.fromId(shortcutId)) {
      AppShortcut.VIEW_FEES -> {
        shouldOpenFees.value = true
      }

      null -> Unit
    }
  }

  /**
   * Consumes the shortcut action, resetting the flag that should open fees flag to false.
   */
  fun consumeShortcut() {
    shouldOpenFees.value = false
  }
}
