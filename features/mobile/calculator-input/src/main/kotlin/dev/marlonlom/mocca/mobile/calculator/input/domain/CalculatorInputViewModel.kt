/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.input.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.mocca.mobile.calculator.input.domain.CalculatorInputViewModel.Companion.BACKSPACE
import dev.marlonlom.mocca.mobile.calculator.input.domain.CalculatorInputViewModel.Companion.ZERO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.NumberFormat
import java.util.Locale

/**
 * ViewModel that manages calculator input state and validates amounts within a given range.
 *
 * @author marlonlom
 *
 * @param amountRange The valid range of amounts for calculation.
 */
class CalculatorInputViewModel(private val amountRange: ClosedFloatingPointRange<Double>) : ViewModel() {

  /** Calculation plain amount as private Ui State. */
  private val plainAmount = MutableStateFlow(ZERO)

  /** Calculation amount as Ui State. */
  val amountUiState = plainAmount
    .map { amountStr -> buildAmountUiState(amountStr) }
    .stateIn(
      scope = viewModelScope,
      started = WhileSubscribed(5_000),
      initialValue = CalculatorAmountUiState(),
    )

  /**
   * Appends input to the current amount or handles backspace ([BACKSPACE]).
   * Replaces [ZERO] with input unless deleting characters, then resets to [ZERO] if empty.
   *
   * @param input The input string or [BACKSPACE] to delete the last character.
   */

  fun appendToAmount(input: String) {
    val current = plainAmount.value
    plainAmount.value = when {
      input == CLEAR -> ZERO

      input == BACKSPACE -> {
        if (current.length > 1) current.dropLast(1) else ZERO
      }

      current == ZERO -> input
      else -> {
        val nextAmount = current + input
        if (nextAmount.length >= MAX_DIGITS) current else nextAmount
      }
    }
  }

  /**
   * Builds a [CalculatorAmountUiState] from the given amount string.
   *
   * Parses the string to a Double, formats it with no decimal places,
   * and checks if it falls within the valid amount range.
   *
   * @param amountStr The amount as a plain string.
   * @return A UI state containing the original string, formatted amount, and validity flag.
   */
  private fun buildAmountUiState(amountStr: String): CalculatorAmountUiState {
    val amount = amountStr.toDoubleOrNull() ?: 0.0
    val numberFormat = NumberFormat.getNumberInstance(Locale.US).also {
      it.maximumFractionDigits = 0
    }
    val formatted = numberFormat.format(amount)
    val isValid = amount >= amountRange.start

    return CalculatorAmountUiState(
      plainAmount = amountStr,
      formattedAmount = formatted,
      isValidRange = isValid,
    )
  }

  /**
   * Holds constants used for input handling in the calculator.
   *
   * @author marlonlom
   */
  companion object {

    /** The default value for the amount input when it is empty or reset. */
    const val ZERO = "0"

    /** The symbol used to represent a backspace/delete input. */
    const val BACKSPACE = "<"

    /** Symbol representing a clear entry (reset input) action. */
    const val CLEAR = "CE"

    /** The maximum number of digits allowed in the amount input. */
    const val MAX_DIGITS = 9
  }
}
