/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.input.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.input.R
import dev.marlonlom.mocca.mobile.calculator.input.component.CalculatorTextInput
import dev.marlonlom.mocca.mobile.calculator.input.domain.CalculatorAmountUiState
import dev.marlonlom.mocca.mobile.calculator.input.slot.CalculatorButtonsGrid
import dev.marlonlom.mocca.mobile.ui.component.button.WideCalculateButton
import dev.marlonlom.mocca.mobile.ui.component.iconbutton.HistoryIconButton
import dev.marlonlom.mocca.mobile.ui.component.iconbutton.RatesIconButton
import dev.marlonlom.mocca.mobile.ui.component.iconbutton.SettingsIconButton
import dev.marlonlom.mocca.mobile.ui.component.spacer.FullWidthSpacer
import dev.marlonlom.mocca.mobile.ui.component.topbar.TopBar

@Composable
internal fun PortraitCalculatorInput(
  amountUiState: CalculatorAmountUiState,
  onCalculationButtonClicked: (String) -> Unit,
  onCalculationReady: (String) -> Unit,
  onHistoryButtonClicked: () -> Unit,
  onRatesButtonClicked: () -> Unit,
  onSettingsButtonClicked: () -> Unit,
  modifier: Modifier = Modifier,
) = Column(
  modifier = modifier
    .fillMaxWidth()
    .background(MaterialTheme.colorScheme.background)
    .padding(horizontal = 20.dp)
    .consumeWindowInsets(WindowInsets.systemBars),
  verticalArrangement = Arrangement.Center,
  horizontalAlignment = Alignment.CenterHorizontally,
) {
  TopBar(
    title = {
      Text(
        text = stringResource(R.string.text_headline),
      )
    },
    isMedium = true,
    navigationActions = {
      HistoryIconButton(onButtonClicked = { onHistoryButtonClicked() })
      RatesIconButton(onButtonClicked = { onRatesButtonClicked() })
      SettingsIconButton(onButtonClicked = { onSettingsButtonClicked() })
    },
  )
  FullWidthSpacer()
  CalculatorTextInput(amountText = amountUiState.formattedAmount)
  CalculatorButtonsGrid(onButtonClicked = { onCalculationButtonClicked(it) })
  Spacer(
    Modifier
      .fillMaxWidth()
      .padding(vertical = 10.dp),
  )
  WideCalculateButton(
    buttonEnabled = amountUiState.isValidRange,
    onButtonClicked = {
      onCalculationReady(amountUiState.plainAmount)
    },
  )
}
