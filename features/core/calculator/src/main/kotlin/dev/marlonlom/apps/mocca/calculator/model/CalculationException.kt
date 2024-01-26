/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.calculator.model

/**
 * Money order calculation exception sealed class
 *
 *  @author marlonlom
 */
sealed class CalculationException : RuntimeException() {

  /**
   * Money order calculation exception subclass: above quantity range
   *
   * @author marlonlom
   */
  class AboveQuantityRange : CalculationException()

  /**
   * Money order calculation exception subclass: below quantity range
   *
   * @author marlonlom
   */
  class BelowQuantityRange : CalculationException()

  /**
   * Money order calculation exception subclass: negative quantity
   *
   * @author marlonlom
   */
  class NegativeQuantity : CalculationException()
}
