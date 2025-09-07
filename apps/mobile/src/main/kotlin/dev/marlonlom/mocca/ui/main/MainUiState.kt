/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.ui.main

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
