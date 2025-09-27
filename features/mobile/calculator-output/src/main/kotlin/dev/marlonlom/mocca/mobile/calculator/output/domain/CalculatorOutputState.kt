/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.domain

import dev.marlonlom.mocca.calculator.model.CalculationException
import dev.marlonlom.mocca.calculator.model.CalculationResult

/**
 * Calculation result state sealed class.
 *
 * @author marlonlom
 *
 */
sealed class CalculatorOutputState {

  /** Empty calculation result single object. */
  data object Empty : CalculatorOutputState()

  /**
   * Calculation failure data class.
   *
   * @author marlonlom
   *
   * @property amount Required amount as String.
   * @property exception Calculation exception.
   */
  data class WithFailure(val amount: String, val exception: CalculationException) : CalculatorOutputState()

  /**
   * Calculation failure data class.
   *
   * @author marlonlom
   *
   * @property amount Required amount as String.
   * @property response Calculation response.
   */
  data class WithSuccess(val amount: String, val response: CalculationResult) : CalculatorOutputState()

  fun isSuccess(): Boolean = this is WithSuccess
  fun isFailure(): Boolean = this is WithFailure
  fun isDefault(): Boolean = this == Empty
}
