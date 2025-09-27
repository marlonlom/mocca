/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.domain

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.mocca.calculator.model.CalculationResult
import dev.marlonlom.mocca.calculator.model.OrderResponse
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * A [ViewModel] that handles the business logic and UI state for the calculation output screen.
 *
 * This ViewModel uses a [SavedStateHandle] to persist the user's input amount, ensuring the state
 * survives process death and configuration changes. It performs a calculation and exposes the
 * result through a [StateFlow] of [CalculatorOutputState], which the UI can observe.
 *
 * @author marlonlom
 *
 * @param savedStateHandle The handle to save and restore UI state.
 * @param doCalculation The use case function that performs the calculation.
 */
class CalculatorOutputViewModel(
  private val savedStateHandle: SavedStateHandle,
  private val doCalculation: (Double) -> OrderResponse<CalculationResult>,
) : ViewModel() {

  /**
   * Represents the observable UI state of the calculation output screen.
   *
   * The UI collects from this [StateFlow] to get real-time updates on the calculation status
   * (e.g., success, failure, or empty). The state is mapped from the saved amount text.
   */
  val uiState: StateFlow<CalculatorOutputState> = savedStateHandle.getStateFlow(
    key = UI_STATE_KEY,
    initialValue = NO_AMOUNT,
  ).map { amountText ->
    when (val orderResponse = doCalculation(amountText.toDouble())) {
      is OrderResponse.Success<CalculationResult> -> CalculatorOutputState.WithSuccess(
        amount = amountText,
        response = orderResponse.item,
      )

      is OrderResponse.Failure -> CalculatorOutputState.WithFailure(
        amount = amountText,
        exception = orderResponse.exception,
      )

      else -> CalculatorOutputState.Empty
    }
  }.stateIn(
    scope = viewModelScope,
    started = SharingStarted.WhileSubscribed(5_000),
    initialValue = CalculatorOutputState.Empty,
  )

  /**
   * Triggers a new calculation by updating the saved amount text.
   *
   * @param amountText The new amount entered by the user.
   */
  fun onCalculate(amountText: String) {
    savedStateHandle[UI_STATE_KEY] = amountText.ifEmpty { NO_AMOUNT }
  }

  /**
   * Resets the calculation state by clearing the saved amount text.
   */
  fun onReset() {
    savedStateHandle[UI_STATE_KEY] = NO_AMOUNT
  }

  /**
   * Contains constants used within the ViewModel.
   *
   * @author marlonlom
   *
   */
  companion object {
    /** A key for the UI state stored in [SavedStateHandle]. */
    private const val UI_STATE_KEY = "calculatingAmount"

    /** Represents an empty or no-amount state. */
    private const val NO_AMOUNT = ""
  }
}
