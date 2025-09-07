/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.calculator

import dev.marlonlom.mocca.calculator.util.UsedNumbers
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

internal class CalculatorAmountRangeTest {

  @Test
  fun `Should return false for using max number plus 1 in validation range`() {
    assertFalse(UsedNumbers.MAX_VALUE.plus(1) in Calculator.calculatorAmountRange())
  }

  @Test
  fun `Should return false for using zero in validation range`() {
    assertFalse(0.0 in Calculator.calculatorAmountRange())
  }

  @Test
  fun `Should return false for validation above range`() {
    assertFalse(42_000_000.0 in Calculator.calculatorAmountRange())
  }

  @Test
  fun `Should return false for validation below range`() {
    assertFalse(-100.0 in Calculator.calculatorAmountRange())
  }

  @Test
  fun `Should return true for using max number minus 1 in validation range`() {
    assertTrue(UsedNumbers.MAX_VALUE.minus(1) in Calculator.calculatorAmountRange())
  }

  @Test
  fun `Should return true for using random value for validation range`() {
    Calculator.calculatorAmountRange().also {
      Random.nextDouble(it.start, it.endInclusive)
      assertTrue(UsedNumbers.ONE in it)
    }
  }

  @Test
  fun `Should return true for using value one in validation range`() {
    assertTrue(UsedNumbers.ONE in Calculator.calculatorAmountRange())
  }

}
