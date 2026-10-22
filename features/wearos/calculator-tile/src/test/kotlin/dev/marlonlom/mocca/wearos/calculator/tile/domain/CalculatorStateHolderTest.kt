/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.tile.domain

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class CalculatorStateHolderTest {

  @Before
  fun setUp() {
    CalculatorStateHolder.reset()
  }

  @Test
  fun `Should return zero amount when initial input is null`() {
    val state = CalculatorStateHolder.from(null)
    assertEquals(0L, state.amount)
  }

  @Test
  fun `Should return correct amount when single digit is input`() {
    val state = CalculatorStateHolder.from("5")
    assertEquals(5L, state.amount)
  }

  @Test
  fun `Should accumulate multiple digits correctly`() {
    CalculatorStateHolder.from("1")
    CalculatorStateHolder.from("2")
    val state = CalculatorStateHolder.from("3")
    assertEquals(123L, state.amount)
  }

  @Test
  fun `Should handle zero input when initial value is zero`() {
    CalculatorStateHolder.from("0")
    val state = CalculatorStateHolder.from("0")
    assertEquals(0L, state.amount)
  }

  @Test
  fun `Should append zero when initial value has digits`() {
    CalculatorStateHolder.from("1")
    val state = CalculatorStateHolder.from("0")
    assertEquals(10L, state.amount)
  }

  @Test
  fun `Should handle delete action by removing last character`() {
    CalculatorStateHolder.from("1")
    CalculatorStateHolder.from("2")
    CalculatorStateHolder.from("3")
    val state = CalculatorStateHolder.from("-")
    assertEquals(12L, state.amount)
  }

  @Test
  fun `Should reset to zero when delete action is used on single digit`() {
    CalculatorStateHolder.from("5")
    val state = CalculatorStateHolder.from("-")
    assertEquals(0L, state.amount)
  }

  @Test
  fun `Should stay zero when delete action is used on zero`() {
    val state = CalculatorStateHolder.from("-")
    assertEquals(0L, state.amount)
  }

  @Test
  fun `Should ignore non-digit input values`() {
    CalculatorStateHolder.from("5")
    val state = CalculatorStateHolder.from("a")
    assertEquals(5L, state.amount)
  }

  @Test
  fun `Should respect maximum amount text limit of 8 characters`() {
    "12345678".forEach { CalculatorStateHolder.from(it.toString()) }
    val state = CalculatorStateHolder.from("9")
    assertEquals(12345678L, state.amount)
  }
}
