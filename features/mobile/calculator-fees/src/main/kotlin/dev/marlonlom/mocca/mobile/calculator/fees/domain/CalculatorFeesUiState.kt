/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.fees.domain

/**
 * Represents the various states of the Calculation fees UI.
 *
 * @author marlonlom
 */
sealed interface CalculatorFeesUiState {

  /**
   * Represents the state where the fees data is empty.
   *
   * @author marlonlom
   */
  data object Empty : CalculatorFeesUiState

  /**
   * Represents the state where the fees data have been successfully loaded.
   *
   * @author marlonlom
   *
   * @property fees Calculation fees data from data layer.
   */
  data class Success(val fees: List<SuccessfulFeesDomainData>) : CalculatorFeesUiState
}
