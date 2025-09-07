/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.marlonlom.mocca.mobile.calculator.input.component.CalculatorTextInput
import dev.marlonlom.mocca.mobile.calculator.input.domain.CalculatorInputViewModel
import dev.marlonlom.mocca.mobile.calculator.input.slot.CalculatorButtonsGrid
import dev.marlonlom.mocca.mobile.ui.component.button.WideCalculateButton
import dev.marlonlom.mocca.mobile.ui.component.iconbutton.HistoryIconButton
import dev.marlonlom.mocca.mobile.ui.component.iconbutton.RatesIconButton
import dev.marlonlom.mocca.mobile.ui.component.iconbutton.SettingsIconButton
import dev.marlonlom.mocca.mobile.ui.component.spacer.FullWidthSpacer
import dev.marlonlom.mocca.mobile.ui.component.topbar.TopBar
import org.koin.androidx.compose.koinViewModel

/**
 * Composable screen for entering and validating calculator input.
 *
 * @author marlonlom
 *
 * @param onCalculationReady Called when the input is ready for calculation.
 * @param onHistoryButtonClicked Called when the history button is pressed.
 * @param onRatesButtonClicked Called when the rates button is pressed.
 * @param onSettingsButtonClicked Called when the settings button is pressed.
 * @param viewModel The ViewModel handling input state (injected by default).
 */
@Composable
fun CalculatorInputScreen(
  onCalculationReady: (String) -> Unit,
  onHistoryButtonClicked: () -> Unit,
  onRatesButtonClicked: () -> Unit,
  onSettingsButtonClicked: () -> Unit,
  viewModel: CalculatorInputViewModel = koinViewModel(),
) {
  val amountUiState by viewModel.amountUiState.collectAsStateWithLifecycle()

  Column(
    modifier = Modifier
      .background(MaterialTheme.colorScheme.background)
      .padding(20.dp)
      .fillMaxSize()
      .consumeWindowInsets(WindowInsets.systemBars),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    TopBar(
      title = {
        Text(
          text = stringResource(R.string.text_headline),
          overflow = TextOverflow.Ellipsis,
          maxLines = 1,
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
    CalculatorButtonsGrid(viewModel::appendToAmount)
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
}
