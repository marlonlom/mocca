/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.history.domain

/**
 * Represents the various states of the Calculation history UI.
 *
 * @author marlonlom
 */
sealed interface CalculatorHistoryUiState {

  /**
   * Represents the state where the historical data is currently being loaded.
   *
   * @author marlonlom
   */
  data object Loading : CalculatorHistoryUiState

  /**
   * Represents the state where the historical data is empty.
   *
   * @author marlonlom
   */
  data object Empty : CalculatorHistoryUiState

  /**
   * Represents the state where the historical data have been successfully loaded.
   *
   * @author marlonlom
   *
   * @property historicItems Calculation history data from data layer.
   */
  data class Success(val historicItems: List<SuccessfulCalculationDomainData>) : CalculatorHistoryUiState

  /**
   * Returns true if this state represents [Success].
   *
   * @return true/false
   */
  fun isSuccess(): Boolean = this is Success
}
