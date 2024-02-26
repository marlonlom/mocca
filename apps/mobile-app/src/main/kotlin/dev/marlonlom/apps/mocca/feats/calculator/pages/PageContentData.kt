/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator.pages

import androidx.compose.runtime.MutableState
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorUiState
import dev.marlonlom.apps.mocca.ui.util.WindowSizeInfo

/**
 * Page content data for calculator pages.
 *
 * @author marlonlom
 *
 * @property windowSizeInfo
 * @property calculationTextState
 * @property calculatorUiState
 * @property numberTypingEnabledState True/False as MutableState indicating if number appending is enabled.
 * @property actions
 */
data class PageContentData(
  val windowSizeInfo: WindowSizeInfo,
  val calculationTextState: MutableState<String>,
  val calculatorUiState: CalculatorUiState,
  val numberTypingEnabledState: MutableState<Boolean>,
  val actions: PageContentActions
)

/**
 * Page content actions data for calculator pages.
 *
 * @author marlonlom
 *
 * @property onSlotClosedAction Action for slot closed.
 * @property onPerformCalculationAction Action for calculation started action.
 * @property onDeleteLastNumberAction Action for deleting last added digit when using the buttons.
 * @property onAppendNumberAction Action for adding selected digit when using the buttons.
 */
data class PageContentActions(
  val onSlotClosedAction: () -> Unit,
  val onPerformCalculationAction: () -> Unit,
  val onDeleteLastNumberAction: () -> Unit,
  val onAppendNumberAction: (String) -> Unit,
)
