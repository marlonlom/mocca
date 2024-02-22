/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.marlonlom.apps.mocca.feats.calculator.buttons.ButtonsSection
import dev.marlonlom.apps.mocca.ui.main.scaffold.ScaffoldInnerContentType
import dev.marlonlom.apps.mocca.ui.util.WindowSizeInfo

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
  calculatorViewModel: CalculatorViewModel = viewModel(factory = CalculatorViewModel.Factory),
) {

  val calculationUiState by calculatorViewModel.uiState.collectAsState()
  val calculationAmount = when (calculationUiState) {
    is CalculatorUiState.WithFailure -> (calculationUiState as CalculatorUiState.WithFailure).amount
    is CalculatorUiState.WithSuccess -> (calculationUiState as CalculatorUiState.WithSuccess).amount
    is CalculatorUiState.Empty -> "0"
  }
  val numberPattern = remember { Regex("^\\d+\$") }
  val calculationTextState = rememberSaveable { mutableStateOf(calculationAmount) }
  val numberTypingEnabledState = rememberSaveable { mutableStateOf(true) }

  when {
    windowSizeInfo.indicateInnerContent == ScaffoldInnerContentType.SinglePane && windowSizeInfo.isMobileLandscape -> {
      Row(modifier = Modifier.fillMaxSize()) {
        TopContentSection(
          windowSizeInfo = windowSizeInfo,
          calculationTextState = calculationTextState,
          calculatorUiState = calculationUiState,
          onSlotClosedAction = {
            numberTypingEnabledState.value = true
            calculatorViewModel.reset()
          },
          isLandscapeSinglePane = true
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          ButtonsSection(
            numberTypingEnabledState = numberTypingEnabledState,
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
          )
        }
      }
    }

    else -> {
      Column(modifier = Modifier.fillMaxSize()) {
        TopContentSection(
          windowSizeInfo = windowSizeInfo,
          calculationTextState = calculationTextState,
          calculatorUiState = calculationUiState,
          onSlotClosedAction = {
            numberTypingEnabledState.value = true
            calculatorViewModel.reset()
          },
        )

        ButtonsSection(
          numberTypingEnabledState = numberTypingEnabledState,
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
        )
      }
    }
  }


}
