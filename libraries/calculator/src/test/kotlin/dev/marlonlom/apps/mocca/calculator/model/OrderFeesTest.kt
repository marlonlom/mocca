/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.calculator.model

import dev.marlonlom.apps.mocca.calculator.model.OrderResponse.Success
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers
import dev.marlonlom.apps.mocca.calculator.util.UsedNumbers.ZERO
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test

internal class OrderFeesTest {

  @Test
  fun `Should obtain none response for value outside fee ranges`() {
    val response = OrderFees.forValue(25.0)
    assertTrue(response is OrderResponse.None)
  }

  @Test
  fun `Should return first fee based response for amount 7_450`() {
    when (val response = OrderFees.forValue(7_450.0)) {
      is Success -> {
        assertEquals(ZERO, response.item.variableFee, ZERO)
        assertEquals(UsedNumbers.FOUR_THOUSAND_SEVEN_HUNDRED, response.item.fixedFee, ZERO)
        assertEquals(12_150.0, response.item.total, ZERO)
      }

      else -> {
        fail()
      }
    }

  }

  @Test
  fun `Should return second fee based response for amount 70_000`() {
    when (val response = OrderFees.forValue(70_000.0)) {
      is Success -> {
        assertEquals(ZERO, response.item.variableFee, ZERO)
        assertEquals(UsedNumbers.SIX_THOUSAND, response.item.fixedFee, ZERO)
        assertEquals(76_000.0, response.item.total, ZERO)
      }

      else -> {
        fail()
      }
    }
  }

  @Test
  fun `Should return third fee based response for amount 120_000`() {
    when (val response = OrderFees.forValue(120_000.0)) {
      is Success -> {
        assertEquals(ZERO, response.item.variableFee, ZERO)
        assertEquals(UsedNumbers.SEVEN_THOUSAND_FIVE_HUNDRED, response.item.fixedFee, ZERO)
        assertEquals(127_500.0, response.item.total, ZERO)
      }

      else -> {
        fail()
      }
    }
  }

  @Test
  fun `Should return fourth fee based response for amount 185_000`() {
    when (val response = OrderFees.forValue(185_000.0)) {
      is Success -> {
        assertEquals(ZERO, response.item.variableFee, ZERO)
        assertEquals(UsedNumbers.EIGHT_THOUSAND_THREE_HUNDRED, response.item.fixedFee, ZERO)
        assertEquals(193_300.0, response.item.total, ZERO)
      }

      else -> {
        fail()
      }
    }
  }

  @Test
  fun `Should return fifth fee based response for amount 250_450`() {
    when (val response = OrderFees.forValue(250_450.0)) {
      is Success -> {
        assertEquals(10_018.0, response.item.variableFee, ZERO)
        assertEquals(ZERO, response.item.fixedFee, ZERO)
        assertEquals(260_468.0, response.item.total, ZERO)
      }

      else -> {
        fail()
      }
    }
  }

  @Test
  fun `Should return fifth fee based response for amount 750_125`() {
    when (val response = OrderFees.forValue(750_125.0)) {
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
  fun `Should return fifth fee based response for amount value above one million`() {
    when (val response = OrderFees.forValue(1_550_001.0)) {
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
  fun `Should return fifth fee based response for amount value above two million`() {
    when (val response = OrderFees.forValue(2_999_991.0)) {
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
}
