/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil
import timber.log.Timber

/**
 * Calculator route screen content composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeUtil Window size utility.
 */
@Composable
fun CalculatorRoute(
  windowSizeUtil: WindowSizeUtil,
  calculatorViewModel: CalculatorViewModel = viewModel(factory = CalculatorViewModel.Factory)
) {

  val calculationState by calculatorViewModel.uiState.collectAsState()

  val calculationAmount = when (calculationState) {
    is CalculatorUiState.WithFailure -> (calculationState as CalculatorUiState.WithFailure).amount
    is CalculatorUiState.WithSuccess -> (calculationState as CalculatorUiState.WithSuccess).amount
    is CalculatorUiState.Empty -> ""
  }
  val amountTextState = rememberSaveable { mutableStateOf(calculationAmount) }

  when {
    windowSizeUtil.isMobileLandscape -> {
      CalculatorLandscapeScreen(
        windowSizeUtil = windowSizeUtil,
        amountTextState = amountTextState,
        calculationState = calculationState,
        doCalculation = calculatorViewModel::calculate,
        onClearedAmountText = calculatorViewModel::reset
      )
    }

    else -> {
      CalculatorPortraitScreen(
        windowSizeUtil = windowSizeUtil,
        amountTextState = amountTextState,
        calculationState = calculationState,
        doCalculation = calculatorViewModel::calculate,
        onClearedAmountText = calculatorViewModel::reset
      )
    }
  }
}

/**
 * Portrait orientation for calculator screen content composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeUtil Window size utility.
 * @param amountTextState Amount text ui state.
 * @param calculationState Calculation ui state.
 * @param doCalculation Action for performing calculation.
 * @param onClearedAmountText Action for clearing amount text.
 */
@Composable
fun CalculatorPortraitScreen(
  windowSizeUtil: WindowSizeUtil,
  amountTextState: MutableState<String>,
  calculationState: CalculatorUiState,
  doCalculation: (String) -> Unit,
  onClearedAmountText: () -> Unit
) {
  val contentHorizontalPadding = when {
    windowSizeUtil.isTabletLandscape -> PaddingValues(horizontal = 60.dp)
    windowSizeUtil.isTabletWidth -> PaddingValues(horizontal = 80.dp)
    else -> PaddingValues(20.dp)
  }
  val spacerWeight = when {
    windowSizeUtil.isTabletLandscape -> 0.3f
    else -> 1.0f
  }
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(contentHorizontalPadding),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    CalculatorTitleText(windowSizeUtil)
    RequiredAmountInputCard(windowSizeUtil, amountTextState, onClearedAmountText)
    when (calculationState) {
      is CalculatorUiState.WithFailure -> {
        CalculatorErrorCard(windowSizeUtil, calculationState)
      }

      is CalculatorUiState.WithSuccess -> {
        CalculatorResultsCard(windowSizeUtil, calculationState)
      }

      CalculatorUiState.Empty -> {}
    }

    Spacer(modifier = Modifier.weight(spacerWeight))
    CalculateButton(
      buttonEnabled = amountTextState.value.isNotEmpty(),
      buttonClicked = {
        Timber.d("[CalculatorRoute] requestedQuantity=${amountTextState.value}")
        doCalculation(amountTextState.value)
      }
    )
  }
}

/**
 * Landscape orientation for calculator screen content composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeUtil Window size utility.
 * @param amountTextState Amount text ui state.
 * @param calculationState Calculation ui state.
 * @param doCalculation Action for performing calculation.
 */
@Composable
fun CalculatorLandscapeScreen(
  windowSizeUtil: WindowSizeUtil,
  amountTextState: MutableState<String>,
  onClearedAmountText: () -> Unit,
  calculationState: CalculatorUiState,
  doCalculation: (String) -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 60.dp)
      .padding(bottom = 20.dp),
    horizontalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    Column(
      modifier = Modifier.fillMaxWidth(0.5f), verticalArrangement = Arrangement.Top
    ) {
      CalculatorTitleText(windowSizeUtil)
      Spacer(modifier = Modifier.weight(1.0f))
      CalculateButton(
        buttonEnabled = amountTextState.value.isNotEmpty(),
        buttonClicked = {
          Timber.d("[CalculatorRoute] requestedQuantity=${amountTextState.value}")
          doCalculation(amountTextState.value)
        }
      )
    }
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      RequiredAmountInputCard(windowSizeUtil, amountTextState, onClearedAmountText)
      when (calculationState) {
        is CalculatorUiState.WithFailure -> {
          CalculatorErrorCard(windowSizeUtil, calculationState)
        }

        is CalculatorUiState.WithSuccess -> {
          CalculatorResultsCard(windowSizeUtil, calculationState)
        }

        CalculatorUiState.Empty -> {}
      }
    }
  }
}

