/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import dev.marlonlom.apps.mocca.UnitTestsDispatcherRule
import dev.marlonlom.apps.mocca.calculator.model.CalculationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.roundToLong

@ExperimentalCoroutinesApi
internal class CalculatorViewModelTest {

  @get:Rule
  val mainDispatcherRule = UnitTestsDispatcherRule()

  private val viewModel = CalculatorViewModel()

  @Before
  fun setUp() {
    viewModel.reset()
  }

  @Test
  fun `Should return empty calculator ui state`() = runTest {
    val uiState = viewModel.uiState.first()
    assertNotNull(uiState)
    assertEquals(CalculatorUiState.Empty, uiState)
  }

  @Test
  fun `Should return success calculator ui state`() = runTest {
    mainDispatcherRule.testDispatcher.run {
      val amountText = "5000"
      with(viewModel) {
        calculate(amountText)
        val uiState = uiState.first()

        assertNotNull(uiState)
        when (uiState) {
          is CalculatorUiState.WithSuccess -> {
            assertEquals(amountText, uiState.amount)
            assertNotNull(uiState.response)
            assertEquals(9700L, uiState.response.total.roundToLong())
            assertEquals(
              4700L,
              uiState.response.fixedFee.roundToLong().plus(uiState.response.variableFee.roundToLong())
            )
          }

          else -> fail()

        }
      }
    }
  }

  @Test
  fun `Should return below range failure calculator ui state`() = runTest {
    mainDispatcherRule.testDispatcher.run {
      val amountText = "3000"
      with(viewModel) {
        calculate(amountText)
        val uiState = uiState.first()

        assertNotNull(uiState)
        when (uiState) {
          is CalculatorUiState.WithFailure -> {
            assertEquals(amountText, uiState.amount)
            assertNotNull(uiState.exception)
            assertTrue(uiState.exception is CalculationException.BelowQuantityRange)
          }

          else -> fail()

        }
      }
    }
  }

  @Test
  fun `Should return above range failure calculator ui state`() = runTest {
    mainDispatcherRule.testDispatcher.run {
      val amountText = "3000001"
      with(viewModel) {
        calculate(amountText)
        val uiState = uiState.first()

        assertNotNull(uiState)
        when (uiState) {
          is CalculatorUiState.WithFailure -> {
            assertEquals(amountText, uiState.amount)
            assertNotNull(uiState.exception)
            assertTrue(uiState.exception is CalculationException.AboveQuantityRange)
          }

          else -> fail()

        }
      }
    }
  }

  @Test
  fun `Should return negative numbers failure calculator ui state`() = runTest {
    mainDispatcherRule.testDispatcher.run {
      val amountText = "-1"
      with(viewModel) {
        calculate(amountText)
        val uiState = uiState.first()

        assertNotNull(uiState)
        when (uiState) {
          is CalculatorUiState.WithFailure -> {
            assertEquals(amountText, uiState.amount)
            assertNotNull(uiState.exception)
            assertTrue(uiState.exception is CalculationException.NegativeQuantity)
          }

          else -> fail()

        }
      }
    }
  }
}
