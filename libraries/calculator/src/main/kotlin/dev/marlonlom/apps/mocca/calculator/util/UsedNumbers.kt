/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.calculator.util

/**
 * Used numbers single object
 *
 * @author marlonlom
 */
object UsedNumbers {

  const val ZERO = 0.0
  const val ONE = 1.0
  private const val FOUR = 4.0
  const val ONE_HUNDRED = 100

  const val MIN_VALUE = 5_000.0
  const val MAX_VALUE = 3_000_000.0

  const val FIFTY_THOUSAND = 50_000.0
  const val ONE_HUNDRED_THOUSAND = 100_000.0
  const val ONE_HUNDRED_FIFTY_THOUSAND = 150_000.0
  const val TWO_HUNDRED_THOUSAND = 200_000.0

  const val FOUR_THOUSAND_SEVEN_HUNDRED = 4_700.0
  const val SIX_THOUSAND = 6_000.0
  const val SEVEN_THOUSAND_FIVE_HUNDRED = 7_500.0
  const val EIGHT_THOUSAND_THREE_HUNDRED = 8_300.0

  /**
   * Multiples four by money order value.
   *
   * @param orderValue money order value
   * @return multiplication result
   */
  fun multiplyOrderValueByFour(orderValue: Double) = FOUR * orderValue
}
