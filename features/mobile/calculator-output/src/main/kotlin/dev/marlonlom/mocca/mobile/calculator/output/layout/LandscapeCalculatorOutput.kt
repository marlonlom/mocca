/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.output.component.CalculationOutputCloseButton
import dev.marlonlom.mocca.mobile.calculator.output.component.CalculationOutputDescription
import dev.marlonlom.mocca.mobile.calculator.output.component.CalculationOutputImage
import dev.marlonlom.mocca.mobile.calculator.output.component.CalculationOutputTitle
import dev.marlonlom.mocca.mobile.calculator.output.domain.CalculatorOutputState
import dev.marlonlom.mocca.mobile.calculator.output.slot.CalculationSuccessDetailsSlot
import dev.marlonlom.mocca.mobile.ui.component.spacer.FullWidthSpacer

/**
 * Displays the calculation output screen optimized for landscape mode.
 *
 * @param calculationState The state containing the result of the calculation.
 * @param onCloseButtonClicked The action to perform when the close button is clicked.
 */
@Composable
internal fun LandscapeCalculatorOutput(calculationState: CalculatorOutputState, onCloseButtonClicked: () -> Unit) =
  Column(
    modifier = Modifier
      .background(MaterialTheme.colorScheme.background)
      .padding(horizontal = 20.dp)
      .consumeWindowInsets(WindowInsets.systemBars),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Row(
      modifier = Modifier
        .fillMaxHeight()
        .widthIn(max = 720.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Column(modifier = Modifier.weight(1.0f)) {
        FullWidthSpacer()
        CalculationOutputImage(uiState = calculationState)
        if (calculationState.isSuccess()) {
          CalculationOutputTitle(uiState = calculationState)
        }
        FullWidthSpacer()
      }
      Spacer(Modifier.width(20.dp))
      Column(
        modifier = Modifier
          .weight(1.0f)
          .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceAround,
      ) {
        if (calculationState.isSuccess().not()) {
          CalculationOutputTitle(uiState = calculationState)
          CalculationOutputDescription(uiState = calculationState)
        }

        if (calculationState.isSuccess()) {
          val successState = calculationState as CalculatorOutputState.WithSuccess
          FullWidthSpacer()
          CalculationSuccessDetailsSlot(uiState = successState)
        }

        if (calculationState.isDefault().not()) {
          FullWidthSpacer()
          CalculationOutputCloseButton(
            uiState = calculationState,
            buttonEnabled = calculationState.isDefault().not(),
            onButtonClicked = { onCloseButtonClicked() },
          )
        }
      }
    }
  }
