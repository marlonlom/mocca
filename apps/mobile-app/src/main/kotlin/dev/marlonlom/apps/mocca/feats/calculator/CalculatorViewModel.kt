/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.marlonlom.apps.mocca.calculator.Calculator
import dev.marlonlom.apps.mocca.calculator.RequestedQuantity
import dev.marlonlom.apps.mocca.calculator.model.CalculationException
import dev.marlonlom.apps.mocca.calculator.model.CalculationResult
import dev.marlonlom.apps.mocca.calculator.model.OrderResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Calculation ui state sealed class.
 *
 * @author marlonlom
 *
 */
sealed class CalculatorUiState {

  /** Empty calculation result single object. */
  data object Empty : CalculatorUiState()

  /**
   * Calculation failure data class.
   *
   * @author marlonlom
   *
   * @property amount Required amount as String.
   * @property exception Calculation exception.
   */
  data class WithFailure(
    val amount: String,
    val exception: CalculationException
  ) : CalculatorUiState()

  /**
   * Calculation failure data class.
   *
   * @author marlonlom
   *
   * @property amount Required amount as String.
   * @property response Calculation response.
   */
  data class WithSuccess(
    val amount: String,
    val response: CalculationResult
  ) : CalculatorUiState()
}

/**
 * Calculator view model class.
 *
 * @author marlonlom
 *
 */
class CalculatorViewModel : ViewModel() {

  /** UI state for calculator view model. */
  val uiState: MutableStateFlow<CalculatorUiState> = MutableStateFlow(CalculatorUiState.Empty)

  init {
    reset()
  }

  /**
   * Perform transfer cost calculation using the requested amount quantity.
   *
   * @param amountText Requested amount quantity as String.
   */
  fun calculate(amountText: String) {
    viewModelScope.launch {
      uiState.value = if (amountText.isEmpty()) {
        CalculatorUiState.Empty
      } else {
        val requestedQuantity = RequestedQuantity(amountText.toDouble())
        when (val response = Calculator.calculate(requestedQuantity)) {
          is OrderResponse.Failure -> CalculatorUiState.WithFailure(amountText, response.exception)
          is OrderResponse.Success -> CalculatorUiState.WithSuccess(amountText, response.item)
          else -> CalculatorUiState.Empty
        }
      }

      Timber.d("[CalculatorViewModel:calculate] uiState.value=${uiState.value}")
    }
  }

  /** Resets ui state to None. */
  fun reset() {
    uiState.value = CalculatorUiState.Empty
  }

  companion object {

    /** Factory for creating calculator view model instances. */
    val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
      @Suppress("UNCHECKED_CAST")
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CalculatorViewModel() as T
      }
    }
  }

}
