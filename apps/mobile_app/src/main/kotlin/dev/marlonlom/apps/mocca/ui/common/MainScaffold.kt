/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.common

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.calculator.model.CalculationException
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorUiState
import dev.marlonlom.apps.mocca.feats.settings.UserPreferences
import dev.marlonlom.apps.mocca.ui.navigation.AppRoute
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Main scaffold container composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeUtil Window size class.
 * @param calculationState Calculation ui state.
 * @param doCalculation Action for performing calculation.
 * @param onClearedAmountText Action for clearing amount calculation.
 * @param settingsUiState Settings ui state.
 * @param onBooleanSettingChanged Action for boolean settings value changes.
 * @param onOssLicencesSettingLinkClicked Action for oss licences setting clicked.
 * @param onOpeningExternalUrlSettingClicked Action for external url link opening.
 * @param onFeedbackSettingLinkClicked Action for feedback setting clicked.
 */
@ExperimentalMaterial3Api
@Composable
fun MainScaffold(
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

  val navController = rememberNavController()
  val localContext = LocalContext.current
  val coroutineScope = rememberCoroutineScope()

  val snackbarHostState = remember { SnackbarHostState() }

  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination?.route ?: AppRoute.Home.route

  Timber.d("[MainScaffold] calculationUiState=$calculationState")

  Scaffold(
    modifier = Modifier.fillMaxWidth(),
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
    topBar = {
      if (!windowSizeUtil.isTabletLandscape) {
        MainTopBar(
          navigationIconVisible = currentDestination != AppRoute.Home.route,
          onNavigationIconClicked = {
            Timber.d("[MainScaffold] onNavigationIconClicked")
            navController.popBackStack()
          },
          onSettingsIconClicked = {
            Timber.d("[MainScaffold] onSettingsIconClicked")
            navController.navigate(AppRoute.Settings.route)
          }
        )
      }
    },
    snackbarHost = {
      SnackbarHost(hostState = snackbarHostState) { snackbarData ->
        Snackbar(
          snackbarData = snackbarData,
          containerColor = MaterialTheme.colorScheme.errorContainer,
          contentColor = MaterialTheme.colorScheme.onErrorContainer
        )
      }
    },
    content = { paddingValues ->
      Box(
        modifier = Modifier
          .padding(paddingValues)
          .fillMaxWidth(),
        contentAlignment = Alignment.Center
      ) {
        MainNavHost(
          navController = navController,
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
      }
    },
  )

  showSnackBarWithCalculationState(
    currentDestination = currentDestination,
    calculationState = calculationState,
    coroutineScope = coroutineScope,
    snackbarHostState = snackbarHostState,
    localContext = localContext
  )

}

/**
 * Shows snackbar using provided calculation state.
 *
 * @param calculationState Calculation ui state.
 * @param coroutineScope Coroutine scope.
 * @param snackbarHostState Snackbar host State.
 * @param localContext Application context.
 */
internal fun showSnackBarWithCalculationState(
  currentDestination: String,
  calculationState: CalculatorUiState,
  coroutineScope: CoroutineScope,
  snackbarHostState: SnackbarHostState,
  localContext: Context,
) {

  if (currentDestination != AppRoute.Home.route) { return }

  val isCalculationFailure = calculationState is CalculatorUiState.WithFailure

  if (!isCalculationFailure) { return }

  val errorMessage = when ((calculationState as CalculatorUiState.WithFailure).exception) {
    is CalculationException.AboveQuantityRange -> R.string.text_home_error_above_range
    is CalculationException.BelowQuantityRange -> R.string.text_home_error_below_range
    is CalculationException.NegativeQuantity -> R.string.text_home_error_negative_amounts
  }

  coroutineScope.launch {
    snackbarHostState.showSnackbar(
      localContext.getString(errorMessage)
    )
  }

}

