/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.domain

import androidx.lifecycle.SavedStateHandle
import dev.marlonlom.mocca.calculator.model.CalculationException
import dev.marlonlom.mocca.calculator.model.CalculationResult
import dev.marlonlom.mocca.calculator.model.OrderResponse
import dev.marlonlom.mocca.core.database.datasource.LocalDataSource
import dev.marlonlom.mocca.mobile.calculator.output.util.MainDispatcherRule
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class CalculatorOutputViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val historyDataSource = mockk<LocalDataSource>(relaxed = true)

  @Test
  fun `uiState should be Empty when initialized with empty saved state`() = runTest {
    val savedStateHandle = SavedStateHandle()
    val doCalculation: (Double) -> OrderResponse<CalculationResult> = mockk()

    val viewModel = CalculatorOutputViewModel(
      savedStateHandle = savedStateHandle,
      historyDataSource = historyDataSource,
      doCalculation = doCalculation,
    )

    val state = viewModel.uiState.first()
    assertTrue(state is CalculatorOutputState.Empty)
  }

  @Test
  fun `uiState should be WithSuccess when calculation succeeds`() = runTest {
    val savedStateHandle = SavedStateHandle()
    val calculationResult = CalculationResult(
      total = 1050.0,
      fixedFee = 50.0,
      variableFee = 0.0,
    )
    val doCalculation: (Double) -> OrderResponse<CalculationResult> = { amount ->
      if (amount == 1000.0) OrderResponse.Success(calculationResult) else OrderResponse.None
    }

    val viewModel = CalculatorOutputViewModel(
      savedStateHandle = savedStateHandle,
      historyDataSource = historyDataSource,
      doCalculation = doCalculation,
    )

    val results = mutableListOf<CalculatorOutputState>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.uiState.toList(results)
    }

    viewModel.onCalculate("1000")
    advanceUntilIdle()

    val latestState = results.last()
    assertTrue(latestState is CalculatorOutputState.WithSuccess)
    val successState = latestState as CalculatorOutputState.WithSuccess
    assertEquals("1000", successState.amount)
    assertEquals(calculationResult, successState.response)

    coVerify(exactly = 1) {
      historyDataSource.insertSuccessCalculationHistory(
        match { history ->
          history.amount == 1000L &&
            history.fixedFee == 50L &&
            history.variableFee == 0L &&
            history.total == 1050L
        },
      )
    }

    job.cancel()
  }

  @Test
  fun `uiState should be WithFailure when calculation fails`() = runTest {
    val savedStateHandle = SavedStateHandle()
    val exception = CalculationException.NegativeQuantity()
    val doCalculation: (Double) -> OrderResponse<CalculationResult> = { _ ->
      OrderResponse.Failure(exception)
    }

    val viewModel = CalculatorOutputViewModel(
      savedStateHandle = savedStateHandle,
      historyDataSource = historyDataSource,
      doCalculation = doCalculation,
    )

    val results = mutableListOf<CalculatorOutputState>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.uiState.toList(results)
    }

    viewModel.onCalculate("50")
    advanceUntilIdle()

    val latestState = results.last()
    assertTrue(latestState is CalculatorOutputState.WithFailure)
    val failureState = latestState as CalculatorOutputState.WithFailure
    assertEquals("50", failureState.amount)
    assertEquals(exception, failureState.exception)

    job.cancel()
  }

  @Test
  fun `onReset should set uiState to Empty`() = runTest {
    val savedStateHandle = SavedStateHandle(mapOf("calculatingAmount" to "1000"))
    val calculationResult = CalculationResult(1050.0, 50.0, 0.0)
    val doCalculation: (Double) -> OrderResponse<CalculationResult> = {
      OrderResponse.Success(calculationResult)
    }

    val viewModel = CalculatorOutputViewModel(
      savedStateHandle = savedStateHandle,
      historyDataSource = historyDataSource,
      doCalculation = doCalculation,
    )

    val results = mutableListOf<CalculatorOutputState>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.uiState.toList(results)
    }

    advanceUntilIdle()
    assertTrue(results.last() is CalculatorOutputState.WithSuccess)

    viewModel.onReset()
    advanceUntilIdle()

    assertTrue(results.last() is CalculatorOutputState.Empty)

    job.cancel()
  }

  @Test
  fun `onCalculate with empty string should set uiState to Empty`() = runTest {
    val savedStateHandle = SavedStateHandle(mapOf("calculatingAmount" to "1000"))
    val calculationResult = CalculationResult(1050.0, 50.0, 0.0)
    val doCalculation: (Double) -> OrderResponse<CalculationResult> = {
      OrderResponse.Success(calculationResult)
    }

    val viewModel = CalculatorOutputViewModel(
      savedStateHandle = savedStateHandle,
      historyDataSource = historyDataSource,
      doCalculation = doCalculation,
    )

    val results = mutableListOf<CalculatorOutputState>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.uiState.toList(results)
    }

    advanceUntilIdle()
    assertTrue(results.last() is CalculatorOutputState.WithSuccess)

    viewModel.onCalculate("")
    advanceUntilIdle()

    assertTrue(results.last() is CalculatorOutputState.Empty)

    job.cancel()
  }
}
