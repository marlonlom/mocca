/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.twopane

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorPortraitScreen
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorUiState
import dev.marlonlom.apps.mocca.feats.settings.SettingsRoute
import dev.marlonlom.apps.mocca.feats.settings.UserPreferences
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil

/**
 * Two pane layout that contains Calculator and Settings screen combination composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeUtil Window size utility.
 * @param calculationState Calculation ui state.
 * @param doCalculation Action for performing calculation.
 * @param onClearedAmountText Action for clearing amount text.
 * @param settingsUiState Settings ui state.
 * @param onBooleanSettingChanged Action for updating boolean setting.
 * @param onOssLicencesSettingLinkClicked Action for oss licenses display.
 * @param onOpeningExternalUrlSettingClicked Action for external url opening.
 * @param onFeedbackSettingLinkClicked Action for feedback setting clicked.
 */
@Composable
fun CalculatorAndSettingsRoute(
  windowSizeUtil: WindowSizeUtil,
  calculationState: CalculatorUiState,
  doCalculation: (String) -> Unit,
  onClearedAmountText: () -> Unit,
  settingsUiState: State<UserPreferences>,
  onBooleanSettingChanged: (String, Boolean) -> Unit,
  onOssLicencesSettingLinkClicked: () -> Unit,
  onOpeningExternalUrlSettingClicked: (String) -> Unit,
  onFeedbackSettingLinkClicked: () -> Unit,
) {
  val calculationAmount = when (calculationState) {
    is CalculatorUiState.Empty -> ""
    is CalculatorUiState.WithFailure -> calculationState.amount
    is CalculatorUiState.WithSuccess -> calculationState.amount
  }
  val amountTextState = remember { mutableStateOf(calculationAmount) }

  Row(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier
        .fillMaxWidth(0.4f)
        .padding(bottom = 20.dp)
    ) {
      CalculatorPortraitScreen(
        windowSizeUtil = windowSizeUtil,
        amountTextState = amountTextState,
        calculationState = calculationState,
        doCalculation = doCalculation,
        onClearedAmountText = onClearedAmountText
      )
    }

    SettingsRoute(
      windowSizeUtil = windowSizeUtil,
      userPreferences = settingsUiState.value,
      onBooleanSettingChanged = onBooleanSettingChanged,
      onOssLicencesSettingLinkClicked = onOssLicencesSettingLinkClicked,
      onOpeningExternalUrlSettingClicked = onOpeningExternalUrlSettingClicked,
      onFeedbackSettingLinkClicked = onFeedbackSettingLinkClicked
    )
  }
}
