/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.calculator

import dev.marlonlom.mocca.calculator.model.CalculationException.AboveQuantityRange
import dev.marlonlom.mocca.calculator.model.CalculationException.BelowQuantityRange
import dev.marlonlom.mocca.calculator.model.CalculationException.NegativeQuantity
import dev.marlonlom.mocca.calculator.model.OrderFees
import dev.marlonlom.mocca.calculator.model.OrderResponse
import dev.marlonlom.mocca.calculator.util.UsedNumbers.MAX_VALUE
import dev.marlonlom.mocca.calculator.util.UsedNumbers.MIN_VALUE
import dev.marlonlom.mocca.calculator.util.UsedNumbers.ZERO
import java.lang.Double.max
import java.lang.Double.min

/**
 * Money order requested value class.
 *
 * @author marlonlom
 *
 * @property orderValue requested order value quantity
 */
@JvmInline
value class RequestedQuantity(val orderValue: Double)

/**
 * Money order calculator single object definition.
 *
 * @author marlonlom
 */
object Calculator {
  private fun orderValueIsNegative(request: RequestedQuantity) = request.orderValue < ZERO

  private fun orderValueIsBelowRange(request: RequestedQuantity) = request.orderValue < MIN_VALUE

  private fun orderValueIsAboveRange(request: RequestedQuantity) = request.orderValue > MAX_VALUE

  private fun orderValueIsInsideRange(request: RequestedQuantity) = request.orderValue.let {
    min(MIN_VALUE, it) == MIN_VALUE && max(MAX_VALUE, it) == MAX_VALUE
  }

  /**
   * Performs money order response values calculation using order quantity.
   *
   * @param request money order request.
   * @return a money order response based on order value.
   */
  fun calculate(request: RequestedQuantity) = when {
    orderValueIsNegative(request) -> OrderResponse.Failure(NegativeQuantity())
    orderValueIsBelowRange(request) -> OrderResponse.Failure(BelowQuantityRange())
    orderValueIsAboveRange(request) -> OrderResponse.Failure(AboveQuantityRange())
    orderValueIsInsideRange(request) -> OrderFees.forValue(request.orderValue)
    else -> OrderResponse.None
  }
}
