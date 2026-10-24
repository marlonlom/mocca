/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.domain

import dev.marlonlom.mocca.calculator.model.CalculationException
import dev.marlonlom.mocca.calculator.model.CalculationResult
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

internal class CalculatorOutputStateTest {

  @Test
  fun `isSuccess returns true when state is WithSuccess and holds correct properties`() {
    val response = mockk<CalculationResult>()
    val state = CalculatorOutputState.WithSuccess(
      amount = "100.00",
      response = response,
    )

    assertTrue(state.isSuccess())
    assertFalse(state.isFailure())
    assertFalse(state.isDefault())
    assertEquals("100.00", state.amount)
    assertEquals(response, state.response)
  }

  @Test
  fun `isFailure returns true when state is WithFailure and holds correct properties`() {
    val exception = mockk<CalculationException>()
    val state = CalculatorOutputState.WithFailure(
      amount = "0.00",
      exception = exception,
    )

    assertFalse(state.isSuccess())
    assertTrue(state.isFailure())
    assertFalse(state.isDefault())
    assertEquals("0.00", state.amount)
    assertEquals(exception, state.exception)
  }

  @Test
  fun `isDefault returns true when state is Empty`() {
    val state = CalculatorOutputState.Empty
    assertFalse(state.isSuccess())
    assertFalse(state.isFailure())
    assertTrue(state.isDefault())
  }
}
