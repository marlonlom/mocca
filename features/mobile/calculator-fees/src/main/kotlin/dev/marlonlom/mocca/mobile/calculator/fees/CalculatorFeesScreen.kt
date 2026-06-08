/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.fees

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.mocca.mobile.calculator.fees.domain.CalculatorFeesUiState
import dev.marlonlom.mocca.mobile.calculator.fees.domain.CalculatorFeesViewModel
import dev.marlonlom.mocca.mobile.calculator.fees.layout.PortraitHistory
import dev.marlonlom.mocca.mobile.ui.window.MobileWindowSize
import org.koin.androidx.compose.koinViewModel

/**
 * A screen that displays a list of previous calculations.
 *
 * @author marlonlom
 *
 * @param mobileWindowSize The adaptive layout size used to adjust UI spacing.
 * @param showCloseButton Toggles the visibility of the exit icon.
 * @param onCloseButtonClicked Callback invoked when the user exits the history view.
 * @param viewModel The state holder for history data, defaulted via Koin.
 */
@Composable
fun CalculatorFeesScreen(
  mobileWindowSize: MobileWindowSize,
  showCloseButton: Boolean,
  onCloseButtonClicked: () -> Unit,
  viewModel: CalculatorFeesViewModel = koinViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val domainData = (uiState as? CalculatorFeesUiState.Success)?.fees.orEmpty()
  PortraitHistory(
    feesData = { domainData },
    mobileWindowSize = mobileWindowSize,
    showCloseButton = showCloseButton,
    onCloseButtonClicked = onCloseButtonClicked,
  )
}
