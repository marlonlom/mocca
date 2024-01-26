/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.calculator.model

import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.EIGHT_THOUSAND_THREE_HUNDRED
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.FIFTY_THOUSAND
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.FOUR_THOUSAND_SEVEN_HUNDRED
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.MAX_VALUE
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.MIN_VALUE
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.ONE
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.ONE_HUNDRED
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.ONE_HUNDRED_FIFTY_THOUSAND
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.ONE_HUNDRED_THOUSAND
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.SEVEN_THOUSAND_FIVE_HUNDRED
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.SIX_THOUSAND
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.TWO_HUNDRED_THOUSAND
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.ZERO
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.multiplyOrderValueByFour
import kotlin.math.max
import kotlin.math.min
import kotlin.math.round

/**
 * Money order fees enum class.
 *
 * @author marlonlom
 *
 * @property min initial amount for checking order value.
 * @property max final amount for checking order value.
 * @property fixedFee fixed fee amount.
 * @property haveVariableFee true/false if include variable fee amount.
 */
enum class OrderFees(
  val min: Double,
  val max: Double,
  val fixedFee: Double,
  val haveVariableFee: Boolean
) {

  /** First fee enum value */
  FIRST_FEE(
    min = MIN_VALUE,
    max = FIFTY_THOUSAND,
    fixedFee = FOUR_THOUSAND_SEVEN_HUNDRED,
    haveVariableFee = false
  ),

  /** Second fee enum value */
  SECOND_FEE(
    min = FIFTY_THOUSAND.plus(ONE),
    max = ONE_HUNDRED_THOUSAND,
    fixedFee = SIX_THOUSAND,
    haveVariableFee = false
  ),

  /** Third fee enum value */
  THIRD_FEE(
    min = ONE_HUNDRED_THOUSAND.plus(ONE),
    max = ONE_HUNDRED_FIFTY_THOUSAND,
    fixedFee = SEVEN_THOUSAND_FIVE_HUNDRED,
    haveVariableFee = false
  ),

  /** Fourth fee enum value */
  FOURTH_FEE(
    min = ONE_HUNDRED_FIFTY_THOUSAND.plus(ONE),
    max = TWO_HUNDRED_THOUSAND,
    fixedFee = EIGHT_THOUSAND_THREE_HUNDRED,
    haveVariableFee = false
  ),

  /** Fifth fee enum value */
  FIFTH_FEE(
    min = TWO_HUNDRED_THOUSAND.plus(ONE),
    max = MAX_VALUE,
    fixedFee = ZERO,
    haveVariableFee = true
  );

  companion object {

    /**
     * Finds a money order fee using order value.
     *
     * @param orderValue requested order value.
     * @return found enum value of money order fee.
     */
    fun forValue(orderValue: Double) = values().find {
      betweenInclusive(
        rangeStartValue = it.min,
        rangeFinalValue = it.max,
        orderValue = orderValue
      )
    }.let {
      when (it) {
        null -> OrderResponse.None
        else -> {
          val variableFeeAmount = getVariableFee(it, orderValue)
          OrderResponse.Success(
            CalculationResult(
              fixedFee = it.fixedFee,
              variableFee = variableFeeAmount,
              total = orderValue + it.fixedFee + variableFeeAmount
            )
          )
        }
      }
    }

    private fun getVariableFee(fee: OrderFees, orderValue: Double) =
      if (!fee.haveVariableFee) ZERO else calculateVariableFee(fee, orderValue)

    private fun calculateVariableFee(fee: OrderFees, orderValue: Double) =
      when (fee) {
        FIFTH_FEE -> round(multiplyOrderValueByFour(orderValue) / ONE_HUNDRED)
        else -> ZERO
      }

    private fun betweenInclusive(
      rangeStartValue: Double,
      rangeFinalValue: Double,
      orderValue: Double
    ): Boolean = (min(rangeStartValue, orderValue) == rangeStartValue)
      .and(max(rangeFinalValue, orderValue) == rangeFinalValue)

  }
}
