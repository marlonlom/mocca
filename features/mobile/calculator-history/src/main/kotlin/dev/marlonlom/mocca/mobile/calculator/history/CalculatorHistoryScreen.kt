/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.mocca.mobile.calculator.history.domain.CalculatorHistoryUiState
import dev.marlonlom.mocca.mobile.calculator.history.domain.CalculatorHistoryViewModel
import dev.marlonlom.mocca.mobile.calculator.history.layout.PortraitHistory
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
fun CalculatorHistoryScreen(
  mobileWindowSize: MobileWindowSize,
  showCloseButton: Boolean,
  onCloseButtonClicked: () -> Unit,
  viewModel: CalculatorHistoryViewModel = koinViewModel(),
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val domainData = (uiState as? CalculatorHistoryUiState.Success)?.historicItems.orEmpty()
  PortraitHistory(
    historyData = { domainData },
    mobileWindowSize = mobileWindowSize,
    showCloseButton = showCloseButton,
    onCloseButtonClicked = onCloseButtonClicked,
    onClearHistoryButtonClicked = viewModel::clearAllHistory,
    onDeleteButtonClicked = viewModel::deleteHistoryItem,
  )
}
