/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.mocca.mobile.calculator.input.domain.CalculatorInputViewModel
import dev.marlonlom.mocca.mobile.calculator.input.layout.LandscapeCalculatorInput
import dev.marlonlom.mocca.mobile.calculator.input.layout.PortraitCalculatorInput
import dev.marlonlom.mocca.mobile.ui.window.MobileWindowSize
import org.koin.androidx.compose.koinViewModel

/**
 * Composable screen for entering and validating calculator input.
 *
 * @author marlonlom
 *
 * @param onCalculationReady Called when the input is ready for calculation.
 * @param onHistoryButtonClicked Called when the history button is pressed.
 * @param onRatesButtonClicked Called when the rates button is pressed.
 * @param onSettingsButtonClicked Called when the settings button is pressed.
 * @param viewModel The ViewModel handling input state (injected by default).
 */
@Composable
fun CalculatorInputScreen(
  mobileWindowSize: MobileWindowSize,
  onCalculationReady: (String) -> Unit,
  onHistoryButtonClicked: () -> Unit,
  onRatesButtonClicked: () -> Unit,
  onSettingsButtonClicked: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: CalculatorInputViewModel = koinViewModel(),
) {
  val amountUiState by viewModel.amountUiState.collectAsStateWithLifecycle()

  when (mobileWindowSize) {
    MobileWindowSize.MOBILE_LANDSCAPE -> {
      LandscapeCalculatorInput(
        amountUiState = amountUiState,
        onCalculationButtonClicked = viewModel::appendToAmount,
        onHistoryButtonClicked = onHistoryButtonClicked,
        onRatesButtonClicked = onRatesButtonClicked,
        onSettingsButtonClicked = onSettingsButtonClicked,
        onCalculationReady = {
          onCalculationReady(amountUiState.plainAmount)
        },
      )
    }

    else -> {
      PortraitCalculatorInput(
        amountUiState = amountUiState,
        onCalculationButtonClicked = viewModel::appendToAmount,
        onHistoryButtonClicked = onHistoryButtonClicked,
        onRatesButtonClicked = onRatesButtonClicked,
        onSettingsButtonClicked = onSettingsButtonClicked,
        onCalculationReady = {
          onCalculationReady(amountUiState.plainAmount)
        },
      )
    }
  }
}
