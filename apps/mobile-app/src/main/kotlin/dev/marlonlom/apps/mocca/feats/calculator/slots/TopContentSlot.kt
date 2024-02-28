/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator.slots

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorUiState
import dev.marlonlom.apps.mocca.feats.calculator.input.MoneyAmountInput
import dev.marlonlom.apps.mocca.feats.calculator.output.FailureResultSlot
import dev.marlonlom.apps.mocca.feats.calculator.output.SuccessResultSlot
import dev.marlonlom.apps.mocca.ui.main.scaffold.isCompactHeight
import dev.marlonlom.apps.mocca.ui.main.scaffold.isCompactWidth
import dev.marlonlom.apps.mocca.ui.util.DevicePosture
import dev.marlonlom.apps.mocca.ui.util.WindowSizeInfo

/**
 * Top calculator content section composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeInfo Window size information utility.
 * @param calculationTextState Calculation amount text state.
 * @param calculatorUiState Calculator ui state.
 * @param onSlotClosedAction Action for slot closed.
 * @param isLandscapeSinglePane True/False if showing single pane layout in landscape orientation.
 */
@Composable
internal fun TopContentSlot(
  windowSizeInfo: WindowSizeInfo,
  calculationTextState: MutableState<String>,
  calculatorUiState: CalculatorUiState,
  onSlotClosedAction: () -> Unit,
  isLandscapeSinglePane: Boolean = false
) {
  val contentModifier = when {
    isLandscapeSinglePane.and(windowSizeInfo.windowSizeClass.isCompactHeight.not())
      .and(windowSizeInfo.devicePosture is DevicePosture.TableTopPosture)
    -> Modifier
      .fillMaxWidth(0.5f)
      .fillMaxHeight()

    isLandscapeSinglePane -> Modifier
      .fillMaxWidth(0.5f)
      .fillMaxHeight()

    windowSizeInfo.windowSizeClass.isCompactWidth
      .and(windowSizeInfo.devicePosture is DevicePosture.TableTopPosture)
    -> Modifier
      .fillMaxWidth()
      .fillMaxHeight((windowSizeInfo.devicePosture as DevicePosture.TableTopPosture).hingeRatio)
      .padding(bottom = 20.dp)

    else -> Modifier
      .fillMaxWidth()
      .fillMaxHeight(0.5f)
  }

  Column(
    modifier = contentModifier
      .padding(bottom = 20.dp)
      .fillMaxHeight(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Bottom
  ) {

    MoneyAmountInput(
      windowSizeInfo = windowSizeInfo,
      amountTextState = calculationTextState
    )

    when (calculatorUiState) {
      is CalculatorUiState.WithFailure -> {
        FailureResultSlot(
          failureState = calculatorUiState,
          onSlotClosedAction = onSlotClosedAction,
        )
      }

      is CalculatorUiState.WithSuccess -> {
        SuccessResultSlot(
          successState = calculatorUiState,
          onSlotClosedAction = onSlotClosedAction,
        )
      }

      else -> {}
    }
  }
}
