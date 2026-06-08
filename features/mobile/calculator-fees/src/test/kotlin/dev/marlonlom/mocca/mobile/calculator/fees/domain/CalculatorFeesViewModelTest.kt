/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.fees.domain

import dev.marlonlom.mocca.calculator.model.OrderFees
import dev.marlonlom.mocca.mobile.calculator.fees.util.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class CalculatorFeesViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val calculatorFees = mockk<() -> List<OrderFees>>()
  private val calculatorVariableFeeFactor = mockk<() -> Double>()

  @Test
  fun `uiState should be Empty when no fees exist`() = runTest {
    every { calculatorFees.invoke() } returns emptyList()

    val viewModel = CalculatorFeesViewModel(
      calculatorFees = calculatorFees,
      calculatorVariableFeeFactor = calculatorVariableFeeFactor,
    )

    val result = viewModel.uiState.first()

    assertEquals(CalculatorFeesUiState.Empty, result)

    verify(exactly = 1) { calculatorFees.invoke() }
    verify(exactly = 0) { calculatorVariableFeeFactor.invoke() }
  }

  @Test
  fun `uiState should map variable fee factor when fee supports variable fee`() = runTest {
    val fee = OrderFees.FIFTH_FEE

    every { calculatorFees.invoke() } returns listOf(fee)
    every { calculatorVariableFeeFactor.invoke() } returns 2.5

    val viewModel = CalculatorFeesViewModel(
      calculatorFees = calculatorFees,
      calculatorVariableFeeFactor = calculatorVariableFeeFactor,
    )

    val result = viewModel.uiState.first()

    assertEquals(
      CalculatorFeesUiState.Success(
        fees = listOf(
          SuccessfulFeesDomainData(
            min = fee.min,
            max = fee.max,
            fixedFee = fee.fixedFee,
            haveVariableFee = fee.haveVariableFee,
            variableFeeFactor = 2.5,
          ),
        ),
      ),
      result,
    )

    verify(exactly = 1) { calculatorVariableFeeFactor.invoke() }
  }

  @Test
  fun `uiState should set variable fee factor to zero when fee does not support variable fee`() = runTest {
    val fee = OrderFees.FIRST_FEE

    every { calculatorFees.invoke() } returns listOf(fee)

    val viewModel = CalculatorFeesViewModel(
      calculatorFees = calculatorFees,
      calculatorVariableFeeFactor = calculatorVariableFeeFactor,
    )

    val result = viewModel.uiState.first()

    assertEquals(
      CalculatorFeesUiState.Success(
        fees = listOf(
          SuccessfulFeesDomainData(
            min = fee.min,
            max = fee.max,
            fixedFee = fee.fixedFee,
            haveVariableFee = fee.haveVariableFee,
            variableFeeFactor = 0.0,
          ),
        ),
      ),
      result,
    )

    verify(exactly = 0) { calculatorVariableFeeFactor.invoke() }
  }
}
