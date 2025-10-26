/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import dev.marlonlom.mocca.core.preferences.model.UserColorContrasts
import dev.marlonlom.mocca.core.preferences.model.UserSettings

/**
 * Main activity ui state sealed class.
 *
 * @author marlonlom
 */
sealed interface MainUiState {

  /** Main activity ui state: Loading */
  data object Loading : MainUiState

  /**
   * Main activity ui state: Success
   *
   * @property userData User preferences data.
   */
  data class Success(val userData: UserSettings) : MainUiState
}

/**
 * Returns whether dynamic theming should be used.
 * @return true/false
 */
@Composable
@ReadOnlyComposable
fun MainUiState.shouldUseDynamicTheming(): Boolean = when (this) {
  MainUiState.Loading -> false
  is MainUiState.Success -> userData.useDynamicColor
}

/**
 * Returns whether the dark theme should be used.
 * @return true/false.
 */
@Composable
@ReadOnlyComposable
fun MainUiState.shouldUseDarkTheme(): Boolean = if (isSystemInDarkTheme()) {
  true
} else {
  when (this) {
    MainUiState.Loading -> isSystemInDarkTheme()
    is MainUiState.Success -> userData.useDarkTheme
  }
}

/**
 * Returns the color contrast mode for the current UI state.<br/>
 * Defaults to standard if loading.
 * @return the name of the color contrast mode.
 */
@Composable
@ReadOnlyComposable
fun MainUiState.shouldUseColorContrast(): String = when (this) {
  MainUiState.Loading -> UserColorContrasts.STANDARD.name
  is MainUiState.Success -> userData.colorContrast
}
