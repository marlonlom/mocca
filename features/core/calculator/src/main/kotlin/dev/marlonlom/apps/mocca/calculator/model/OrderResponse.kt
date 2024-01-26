/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.calculator.model

/**
 * Money order response sealed class.
 *
 * @author marlonlom
 * @param T
 */
sealed class OrderResponse<out T> {

  /**
   * Money order response object - None.
   *
   * @author marlonlom
   */
  data object None : OrderResponse<Nothing>()

  /**
   * Failure representation of money order response sealed class.
   *
   * @author marlonlom
   *
   * @property exception exception detail
   */
  data class Failure(val exception: CalculationException) : OrderResponse<Nothing>()

  /**
   * Success representation of money order response sealed class.
   *
   * @author marlonlom
   *
   * @param T
   * @property item value related to the successful response.
   */
  data class Success<T>(val item: T) : OrderResponse<T>()

}

/**
 * Money order calculation result data class.
 *
 * @author marlonlom
 *
 * @property total total value for calculation
 * @property fixedFee fixed fee applied in calculation
 * @property variableFee variable fee applied in calculation
 */
data class CalculationResult(
  val total: Double,
  val fixedFee: Double,
  val variableFee: Double
)
