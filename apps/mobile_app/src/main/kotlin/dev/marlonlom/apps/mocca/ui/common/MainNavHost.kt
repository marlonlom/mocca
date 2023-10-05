/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorRoute
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorUiState
import dev.marlonlom.apps.mocca.feats.settings.SettingsRoute
import dev.marlonlom.apps.mocca.feats.settings.UserPreferences
import dev.marlonlom.apps.mocca.feats.twopane.CalculatorAndSettingsRoute
import dev.marlonlom.apps.mocca.ui.navigation.AppRoute
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil

/**
 * Main navigation host composable ui.
 *
 * @author marlonlom
 *
 * @param navController Navigation controller.
 * @param windowSizeUtil Window size class.
 * @param calculationState Calculation ui state.
 * @param doCalculation Action for perform calculation event.
 * @param onClearedAmountText Action for reset calculation event.
 * @param settingsUiState Settings ui state.
 * @param onBooleanSettingChanged  Action for changing boolean setting event.
 * @param onOssLicencesSettingLinkClicked  Action for displaying oss licenses setting click event.
 * @param onOpeningExternalUrlSettingClicked  Action for opening external url setting click event.
 * @param onFeedbackSettingLinkClicked Action for feedback setting clicked.
 * @param startDestination Start destination route name.
 */
@Composable
fun MainNavHost(
  navController: NavHostController,
  windowSizeUtil: WindowSizeUtil,
  calculationState: CalculatorUiState,
  doCalculation: (String) -> Unit,
  onClearedAmountText: () -> Unit,
  settingsUiState: State<UserPreferences>,
  onBooleanSettingChanged: (String, Boolean) -> Unit,
  onOssLicencesSettingLinkClicked: () -> Unit,
  onOpeningExternalUrlSettingClicked: (String) -> Unit,
  onFeedbackSettingLinkClicked: () -> Unit,
  startDestination: String = AppRoute.Home.route
) {
  NavHost(
    navController = navController,
    startDestination = startDestination
  ) {
    calculatorScreen(
      windowSizeUtil = windowSizeUtil,
      calculationState = calculationState,
      doCalculation = doCalculation,
      onClearedAmountText = onClearedAmountText,
      settingsUiState = settingsUiState,
      onBooleanSettingChanged = onBooleanSettingChanged,
      onOssLicencesSettingLinkClicked = onOssLicencesSettingLinkClicked,
      onOpeningExternalUrlSettingClicked = onOpeningExternalUrlSettingClicked,
      onFeedbackSettingLinkClicked = onFeedbackSettingLinkClicked
    )
    settingsScreen(
      windowSizeUtil = windowSizeUtil,
      settingsUiState = settingsUiState,
      onBooleanSettingChanged = onBooleanSettingChanged,
      onOssLicencesSettingLinkClicked = onOssLicencesSettingLinkClicked,
      onOpeningExternalUrlSettingClicked = onOpeningExternalUrlSettingClicked,
      onFeedbackSettingLinkClicked = onFeedbackSettingLinkClicked
    )
  }
}

/**
 * Navigation graph builder extension for calculator screen composable route.
 *
 * @param windowSizeUtil Window size class.
 * @param calculationState Calculation ui state.
 * @param doCalculation Action for calculation event.
 * @param onClearedAmountText Action for calculation reset event.
 * @param settingsUiState Settings ui state.
 * @param onBooleanSettingChanged  Action for changing boolean setting event.
 * @param onOssLicencesSettingLinkClicked  Action for displaying oss licenses setting click event.
 * @param onOpeningExternalUrlSettingClicked  Action for opening external url setting click event.
 * @param onFeedbackSettingLinkClicked  Action for opening external url setting click event.
 */
private fun NavGraphBuilder.calculatorScreen(
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
  composable(AppRoute.Home.route) {
    if (windowSizeUtil.isTabletLandscape) {
      CalculatorAndSettingsRoute(
        windowSizeUtil = windowSizeUtil,
        calculationState = calculationState,
        doCalculation = doCalculation,
        onClearedAmountText = onClearedAmountText,
        settingsUiState = settingsUiState,
        onBooleanSettingChanged = onBooleanSettingChanged,
        onOssLicencesSettingLinkClicked = onOssLicencesSettingLinkClicked,
        onOpeningExternalUrlSettingClicked = onOpeningExternalUrlSettingClicked,
        onFeedbackSettingLinkClicked = onFeedbackSettingLinkClicked
      )
    } else {
      CalculatorRoute(
        windowSizeUtil = windowSizeUtil,
        calculationState = calculationState,
        doCalculation = doCalculation,
        onClearedAmountText = onClearedAmountText
      )
    }
  }
}

/**
 * Navigation graph builder extension for settings screen composable route.
 *
 * @param windowSizeUtil Window size class.
 * @param settingsUiState Settings ui state.
 * @param onBooleanSettingChanged  Action for changing boolean setting event.
 * @param onOssLicencesSettingLinkClicked  Action for displaying oss licenses setting click event.
 * @param onOpeningExternalUrlSettingClicked  Action for opening external url setting click event.
 * @param onFeedbackSettingLinkClicked Action for feedback setting clicked.
 */
private fun NavGraphBuilder.settingsScreen(
  windowSizeUtil: WindowSizeUtil,
  settingsUiState: State<UserPreferences>,
  onBooleanSettingChanged: (String, Boolean) -> Unit,
  onOssLicencesSettingLinkClicked: () -> Unit,
  onOpeningExternalUrlSettingClicked: (String) -> Unit,
  onFeedbackSettingLinkClicked: () -> Unit,
) {
  composable(AppRoute.Settings.route) {
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
