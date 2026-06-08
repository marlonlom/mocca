/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.fees.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.mocca.calculator.model.OrderFees
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel responsible for managing and exposing calculator fees UI state.
 *
 * @author marlonlom
 *
 * @property calculatorFees Function to retrieve a list of order fees.
 * @property calculatorVariableFeeFactor Function to retrieve the variable fee factor.
 *
 */
class CalculatorFeesViewModel(
  private val calculatorFees: () -> List<OrderFees>,
  private val calculatorVariableFeeFactor: () -> Double,
) : ViewModel() {

  /**
   * Represents the UI state of the settings screen.
   *
   * It emits [CalculatorFeesUiState.Success] with the current settings,
   * or [CalculatorFeesUiState.Empty] while initially loading.
   *
   */
  val uiState: StateFlow<CalculatorFeesUiState> = flowOf(obtainCalculationFees()).stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5000L),
    initialValue = CalculatorFeesUiState.Empty,
  )

  /**
   * Maps a list of [OrderFees] to a corresponding [CalculatorFeesUiState].
   *
   * Returns [CalculatorFeesUiState.Empty] if the list is empty,
   * otherwise returns [CalculatorFeesUiState.Success] with mapped domain items.
   *
   * @return [CalculatorFeesUiState] representing the UI state.
   */
  private fun obtainCalculationFees(): CalculatorFeesUiState = calculatorFees().let {
    if (it.isEmpty()) return CalculatorFeesUiState.Empty
    CalculatorFeesUiState.Success(
      fees = it.map { fee ->
        SuccessfulFeesDomainData(
          min = fee.min,
          max = fee.max,
          fixedFee = fee.fixedFee,
          haveVariableFee = fee.haveVariableFee,
          variableFeeFactor = if (fee.haveVariableFee) calculatorVariableFeeFactor() else 0.0,
        )
      },
    )
  }
}
