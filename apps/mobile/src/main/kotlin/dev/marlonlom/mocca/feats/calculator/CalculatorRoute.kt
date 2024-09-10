/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.feats.calculator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.marlonlom.mocca.feats.calculator.pages.CompactLandscapeCalculatorPage
import dev.marlonlom.mocca.feats.calculator.pages.DefaultVerticalCalculatorPage
import dev.marlonlom.mocca.feats.calculator.pages.PageContentActions
import dev.marlonlom.mocca.feats.calculator.pages.PageContentData
import dev.marlonlom.mocca.ui.main.scaffold.ScaffoldInnerContentType
import dev.marlonlom.mocca.ui.util.DevicePosture
import dev.marlonlom.mocca.ui.util.WindowSizeInfo

/**
 * Calculator route composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeInfo Window size information utility.
 * @param calculatorViewModel Calculator viewmodel default instance.
 */
@Composable
fun CalculatorRoute(
  windowSizeInfo: WindowSizeInfo,
  calculatorViewModel: CalculatorViewModel = viewModel(
    factory = CalculatorViewModel.Factory,
  ),
) {
  val calculationUiState by calculatorViewModel.uiState.collectAsState()
  val calculationAmount = when (calculationUiState) {
    is CalculatorUiState.WithFailure -> (calculationUiState as CalculatorUiState.WithFailure).amount
    is CalculatorUiState.WithSuccess -> (calculationUiState as CalculatorUiState.WithSuccess).amount
    is CalculatorUiState.Empty -> "0"
  }
  val numberPattern = remember { Regex("^\\d{1,7}\$") }
  val calculationTextState = rememberSaveable { mutableStateOf(calculationAmount) }
  val numberTypingEnabledState = rememberSaveable { mutableStateOf(true) }

  if (calculationTextState.value.matches(numberPattern).not()) {
    calculatorViewModel.calculate(calculationTextState.value)
    numberTypingEnabledState.value = false
  }

  val pageContentData = PageContentData(
    windowSizeInfo = windowSizeInfo,
    calculationTextState = calculationTextState,
    calculatorUiState = calculationUiState,
    numberTypingEnabledState = numberTypingEnabledState,
    actions = PageContentActions(
      onSlotClosedAction = {
        numberTypingEnabledState.value = true
        calculatorViewModel.reset()
        calculationTextState.value = "0"
      },
      onPerformCalculationAction = {
        numberTypingEnabledState.value = false
        calculatorViewModel.calculate(calculationTextState.value)
      },
      onDeleteLastNumberAction = {
        val previousValue = calculationTextState.value
        calculationTextState.value = when (previousValue.length) {
          1 -> "0"
          else -> previousValue.substring(0, previousValue.length - 1)
        }
      },
      onAppendNumberAction = { numTxt ->
        val previousValue = calculationTextState.value
        calculationTextState.value = when {
          previousValue == "0" -> numTxt
          previousValue.matches(numberPattern) -> previousValue.plus(numTxt)
          else -> previousValue
        }
      },
    ),
  )

  when {
    (windowSizeInfo.scaffoldInnerContentType is ScaffoldInnerContentType.TwoPane)
      .and(windowSizeInfo.isLandscape)
      .and(windowSizeInfo.devicePosture is DevicePosture.TableTopPosture)
      .or(windowSizeInfo.devicePosture is DevicePosture.ClosedFlipPosture) -> {
      CompactLandscapeCalculatorPage(pageData = pageContentData)
    }

    (windowSizeInfo.scaffoldInnerContentType == ScaffoldInnerContentType.SinglePane)
      .and(windowSizeInfo.isMobileLandscape) -> {
      CompactLandscapeCalculatorPage(pageData = pageContentData)
    }

    else -> {
      DefaultVerticalCalculatorPage(pageData = pageContentData)
    }
  }
}
