/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.fees.domain

import dev.marlonlom.mocca.calculator.Calculator
import dev.marlonlom.mocca.calculator.model.OrderFees

/**
 * Calculator fees domain data provider object.
 *
 * @author marlonlom
 */
object CalculatorFeesProvider {

  /** Zero value for fees. */
  private const val ZERO = 0.0

  /** Lambda for retrieving the variable fee factor. */
  private val variableFeeFactor: () -> Double = { Calculator.VARIABLE_FEE_FACTOR }

  /**
   * Provides the complete list of successful domain fees.
   *
   * @return A list of [CalculatingFeesDomainData] with computed fee factors.
   */
  fun provideFees(): List<CalculatingFeesDomainData> = OrderFees.entries.toList().map { fee ->
    CalculatingFeesDomainData(
      min = fee.min,
      max = fee.max,
      fixedFee = fee.fixedFee,
      haveVariableFee = fee.haveVariableFee,
      variableFeeFactor = if (fee.haveVariableFee) variableFeeFactor() else ZERO,
    )
  }
}
