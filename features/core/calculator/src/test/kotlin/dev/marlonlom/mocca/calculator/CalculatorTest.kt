/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.calculator

import dev.marlonlom.mocca.calculator.model.CalculationException.AboveQuantityRange
import dev.marlonlom.mocca.calculator.model.CalculationException.BelowQuantityRange
import dev.marlonlom.mocca.calculator.model.CalculationException.NegativeQuantity
import dev.marlonlom.mocca.calculator.model.OrderResponse.Failure
import dev.marlonlom.mocca.calculator.model.OrderResponse.None
import dev.marlonlom.mocca.calculator.model.OrderResponse.Success
import dev.marlonlom.mocca.calculator.util.UsedNumbers.EIGHT_THOUSAND_THREE_HUNDRED
import dev.marlonlom.mocca.calculator.util.UsedNumbers.FOUR_THOUSAND_SEVEN_HUNDRED
import dev.marlonlom.mocca.calculator.util.UsedNumbers.SEVEN_THOUSAND_FIVE_HUNDRED
import dev.marlonlom.mocca.calculator.util.UsedNumbers.SIX_THOUSAND
import dev.marlonlom.mocca.calculator.util.UsedNumbers.ZERO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import kotlin.math.min

internal class CalculatorTest {
  @Test
  fun `Should return none response`() {
    val requestedValue = RequestedQuantity(Double.NaN)
    val response = Calculator.calculate(requestedValue)
    assertTrue(response is None)
  }

  @Test
  fun `Should return negative amount response`() {
    val requestedValue = RequestedQuantity(-1.0)
    val response = Calculator.calculate(requestedValue)
    assertTrue(min(requestedValue.orderValue, ZERO) == requestedValue.orderValue)
    when (response) {
      is Failure -> {
        assertTrue(response.exception is NegativeQuantity)
      }

      else -> {
        fail()
      }
    }
  }

  @Test
  fun `Should return amount below range exception`() {
    val requestedValue = RequestedQuantity(420.0)
    when (val response = Calculator.calculate(requestedValue)) {
      is Failure -> {
        assertTrue(response.exception is BelowQuantityRange)
      }

      else -> {
        fail()
      }
    }
  }

  @Test
  fun `Should return above range response`() {
    val requestedValue = RequestedQuantity(42_000_000.0)
    when (val response = Calculator.calculate(requestedValue)) {
      is Failure -> {
        assertTrue(response.exception is AboveQuantityRange)
      }

      else -> {
        fail()
      }
    }
  }

  @Test
  fun `Should return first fee calculation response for order value`() {
    val requestedValue = RequestedQuantity(7_450.0)
    when (val response = Calculator.calculate(requestedValue)) {
      is Success -> {
        assertEquals(ZERO, response.item.variableFee, ZERO)
        assertEquals(FOUR_THOUSAND_SEVEN_HUNDRED, response.item.fixedFee, ZERO)
        assertEquals(12_150.0, response.item.total, ZERO)
      }

      else -> {
        fail()
      }
    }
  }

  @Test
  fun `Should return second fee calculation response for order value`() {
    val requestedValue = RequestedQuantity(70_000.0)
    when (val response = Calculator.calculate(requestedValue)) {
      is Success -> {
        assertEquals(ZERO, response.item.variableFee, ZERO)
        assertEquals(SIX_THOUSAND, response.item.fixedFee, ZERO)
        assertEquals(76_000.0, response.item.total, ZERO)
      }

      else -> {
        fail()
      }
    }
  }

  @Test
  fun `Should return third fee calculation response for order value`() {
    val requestedValue = RequestedQuantity(120_000.0)
    when (val response = Calculator.calculate(requestedValue)) {
      is Success -> {
        assertEquals(ZERO, response.item.variableFee, ZERO)
        assertEquals(SEVEN_THOUSAND_FIVE_HUNDRED, response.item.fixedFee, ZERO)
        assertEquals(127_500.0, response.item.total, ZERO)
      }

      else -> {
        fail()
      }
    }
  }

  @Test
  fun `Should return fourth fee calculation response for order value`() {
    val requestedValue = RequestedQuantity(185_000.0)
    when (val response = Calculator.calculate(requestedValue)) {
      is Success -> {
        assertEquals(ZERO, response.item.variableFee, ZERO)
        assertEquals(EIGHT_THOUSAND_THREE_HUNDRED, response.item.fixedFee, ZERO)
        assertEquals(193_300.0, response.item.total, ZERO)
      }

      else -> {
        fail()
      }
    }
  }

  @Test
  fun `Should return five fee calculation response for amount 750_125`() {
    val requestedValue = RequestedQuantity(750_125.0)
    when (val response = Calculator.calculate(requestedValue)) {
      is Success -> {
        assertEquals(30_005.0, response.item.variableFee, ZERO)
        assertEquals(ZERO, response.item.fixedFee, ZERO)
        assertEquals(780_130.0, response.item.total, ZERO)
      }

      else -> {
        fail()
      }
    }
  }

  @Test
  fun `Should return fifth fee calculation response for amount value above one million`() {
    val requestedValue = RequestedQuantity(1_550_001.0)
    when (val response = Calculator.calculate(requestedValue)) {
      is Success -> {
        assertEquals(62_000.0, response.item.variableFee, ZERO)
        assertEquals(ZERO, response.item.fixedFee, ZERO)
        assertEquals(1_612_001.0, response.item.total, ZERO)
      }

      else -> {
        fail()
      }
    }
  }

  @Test
  fun `Should return fifth fee calculation response for amount value above two million`() {
    val requestedValue = RequestedQuantity(2_999_991.0)
    when (val response = Calculator.calculate(requestedValue)) {
      is Success -> {
        assertEquals(120_000.0, response.item.variableFee, ZERO)
        assertEquals(ZERO, response.item.fixedFee, ZERO)
        assertEquals(3_119_991.0, response.item.total, ZERO)
      }

      else -> {
        fail()
      }
    }
  }

  @Test
  fun `Should return amount above range exception for amount value above three million`() {
    val requestedValue = RequestedQuantity(3_000_009.0)
    when (val response = Calculator.calculate(requestedValue)) {
      is Failure -> {
        assertTrue(response.exception is AboveQuantityRange)
      }

      else -> {
        fail()
      }
    }
  }
}
