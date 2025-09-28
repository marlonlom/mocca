/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.mocca.mobile.calculator.output.domain.CalculatorOutputViewModel
import dev.marlonlom.mocca.mobile.calculator.output.layout.LandscapeCalculatorOutput
import dev.marlonlom.mocca.mobile.calculator.output.layout.PortraitCalculatorOutput
import dev.marlonlom.mocca.mobile.ui.window.MobileWindowSize
import org.koin.compose.viewmodel.koinViewModel

/**
 * Displays the calculation output screen.
 *
 * @author marlonlom
 *
 * @param mobileWindowSize The size class of the window, used for adaptive layouts.
 * @param requestedAmount The amount to be calculated.
 * @param onCloseButtonClicked The action to perform when the close button is clicked.
 * @param viewModel The ViewModel that provides the screen's logic and state.
 */
@Composable
fun CalculatorOutputScreen(
  mobileWindowSize: MobileWindowSize,
  requestedAmount: String,
  onCloseButtonClicked: () -> Unit,
  viewModel: CalculatorOutputViewModel = koinViewModel(),
) {
  val calculationState by viewModel.uiState.collectAsStateWithLifecycle()

  LaunchedEffect(key1 = requestedAmount) {
    if (requestedAmount.isNotEmpty()) {
      viewModel.onCalculate(requestedAmount)
    } else {
      viewModel.onReset()
    }
  }

  when (mobileWindowSize) {
    MobileWindowSize.MOBILE_LANDSCAPE -> LandscapeCalculatorOutput(
      calculationState = calculationState,
      onCloseButtonClicked = {
        viewModel.onReset()
        onCloseButtonClicked()
      },
    )

    else -> PortraitCalculatorOutput(
      calculationState = calculationState,
      onCloseButtonClicked = {
        viewModel.onReset()
        onCloseButtonClicked()
      },
    )
  }
}
