/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.input.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.input.R
import dev.marlonlom.mocca.mobile.calculator.input.component.CalculatorTextInput
import dev.marlonlom.mocca.mobile.calculator.input.domain.CalculatorAmountUiState
import dev.marlonlom.mocca.mobile.calculator.input.slot.CalculatorButtonsGrid
import dev.marlonlom.mocca.mobile.ui.component.button.WideCalculateButton
import dev.marlonlom.mocca.mobile.ui.component.iconbutton.HistoryIconButton
import dev.marlonlom.mocca.mobile.ui.component.iconbutton.RatesIconButton
import dev.marlonlom.mocca.mobile.ui.component.iconbutton.SettingsIconButton
import dev.marlonlom.mocca.mobile.ui.component.topbar.TopBar

/**
 * A composable function for the calculator input UI in landscape orientation.
 *
 * @author
 *
 * @param amountUiState The UI state for the calculator amount display.
 * @param onCalculationButtonClicked Action invoked when the calculation button is clicked.
 * @param onCalculationReady Action invoked when a valid calculation is ready.
 * @param onHistoryButtonClicked Action invoked when the history button is clicked.
 * @param onRatesButtonClicked Action invoked when the rates button is clicked.
 * @param onSettingsButtonClicked Action invoked when the settings button is clicked.
 * @param modifier The modifier to be applied to the layout.
 */
@Composable
internal fun LandscapeCalculatorInput(
  amountUiState: CalculatorAmountUiState,
  onCalculationButtonClicked: (String) -> Unit,
  onCalculationReady: (String) -> Unit,
  onHistoryButtonClicked: () -> Unit,
  onRatesButtonClicked: () -> Unit,
  onSettingsButtonClicked: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .background(MaterialTheme.colorScheme.background)
      .padding(20.dp)
      .consumeWindowInsets(WindowInsets.systemBars),
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
      isMedium = false,
      navigationActions = {
        HistoryIconButton(onButtonClicked = { onHistoryButtonClicked() })
        RatesIconButton(onButtonClicked = { onRatesButtonClicked() })
        SettingsIconButton(onButtonClicked = { onSettingsButtonClicked() })
      },
    )
    Row(
      modifier = Modifier.fillMaxHeight(),
    ) {
      Column(modifier = Modifier.weight(1.0f)) {
        CalculatorTextInput(amountText = amountUiState.formattedAmount)
        WideCalculateButton(
          buttonEnabled = amountUiState.isValidRange,
          onButtonClicked = {
            onCalculationReady(amountUiState.plainAmount)
          },
        )
      }
      Spacer(Modifier.width(20.dp))
      Column(modifier = Modifier.weight(1.0f)) {
        CalculatorButtonsGrid(onButtonClicked = { onCalculationButtonClicked(it) })
      }
    }
  }
}
