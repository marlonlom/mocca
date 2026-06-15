/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.input.domain

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class CalculatorInputStateTest {

  private lateinit var state: CalculatorInputState

  @Before
  fun setUp() {
    state = CalculatorInputState()
  }

  @Test
  fun `Should have initial text value as zero`() {
    assertEquals("0", state.textValue)
  }

  @Test
  fun `Should update text value with a digit`() {
    state.update("1")
    assertEquals("1", state.textValue)
  }

  @Test
  fun `Should update text value with multiple digits`() {
    state.update("1")
    state.update("2")
    state.update("3")
    assertEquals("123", state.textValue)
  }

  @Test
  fun `Should not update text value beyond max length of 7`() {
    "1234567".forEach { state.update(it.toString()) }
    assertEquals("1234567", state.textValue)
    state.update("8")
    assertEquals("1234567", state.textValue)
  }

  @Test
  fun `Should handle backspace by removing last character`() {
    state.update("1")
    state.update("2")
    state.update("⌫")
    assertEquals("1", state.textValue)
  }

  @Test
  fun `Should handle backspace on single digit by resetting to zero`() {
    state.update("1")
    state.update("⌫")
    assertEquals("0", state.textValue)
  }

  @Test
  fun `Should handle backspace on zero by staying zero`() {
    state.update("⌫")
    assertEquals("0", state.textValue)
  }

  @Test
  fun `Should ignore done button when value is zero`() {
    state.update("✔")
    assertEquals("0", state.textValue)
  }

  @Test
  fun `Should ignore done button when value has digits`() {
    state.update("1")
    state.update("2")
    state.update("✔")
    assertEquals("12", state.textValue)
  }

  @Test
  fun `Should handle zero digit when value is zero`() {
    state.update("0")
    assertEquals("0", state.textValue)
  }

  @Test
  fun `Should handle zero digit when value has digits`() {
    state.update("1")
    state.update("0")
    assertEquals("10", state.textValue)
  }

  @Test
  fun `Should reset to zero if backspace is used until empty`() {
    state.update("1")
    state.update("2")
    state.update("⌫")
    state.update("⌫")
    assertEquals("0", state.textValue)
  }
}
