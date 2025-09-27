/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.output.component.CalculationOutputCloseButton
import dev.marlonlom.mocca.mobile.calculator.output.component.CalculationOutputDescription
import dev.marlonlom.mocca.mobile.calculator.output.component.CalculationOutputImage
import dev.marlonlom.mocca.mobile.calculator.output.component.CalculationOutputTitle
import dev.marlonlom.mocca.mobile.calculator.output.domain.CalculatorOutputState
import dev.marlonlom.mocca.mobile.calculator.output.slot.CalculationSuccessDetailsSlot
import dev.marlonlom.mocca.mobile.ui.component.spacer.FullWidthSpacer

@Composable
internal fun PortraitCalculatorOutput(
  calculationState: CalculatorOutputState, onCloseButtonClicked: () -> Unit
) = Column(
  modifier = Modifier
    .background(MaterialTheme.colorScheme.surface)
    .fillMaxSize()
    .padding(horizontal = 20.dp),
  verticalArrangement = Arrangement.Top,
) {
  FullWidthSpacer()

  CalculationOutputImage(uiState = calculationState)

  Spacer(
    Modifier
      .fillMaxWidth()
      .height(48.dp),
  )

  CalculationOutputTitle(uiState = calculationState)
  CalculationOutputDescription(uiState = calculationState)

  if (calculationState.isSuccess()) {
    val successState = calculationState as CalculatorOutputState.WithSuccess
    CalculationSuccessDetailsSlot(uiState = successState)
  }

  FullWidthSpacer()

  if (calculationState.isDefault().not()) {
    CalculationOutputCloseButton(
      uiState = calculationState,
      buttonEnabled = calculationState.isDefault().not(),
      onButtonClicked = { onCloseButtonClicked() },
    )
  }
}
