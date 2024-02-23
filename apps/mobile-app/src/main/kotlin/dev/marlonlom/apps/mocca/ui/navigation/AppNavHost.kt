/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorRoute
import dev.marlonlom.apps.mocca.feats.settings.SettingsRoute
import dev.marlonlom.apps.mocca.ui.main.MainActions
import dev.marlonlom.apps.mocca.ui.util.WindowSizeInfo

/**
 * Main navigation host composable ui.
 *
 * @author marlonlom
 *
 * @param navController Navigation controller.
 * @param windowSizeInfo Window size class.
 * @param startDestination Start destination route name.
 */
@Composable
fun AppNavHost(
  navController: NavHostController,
  windowSizeInfo: WindowSizeInfo,
  mainActions: MainActions,
  startDestination: String = AppRoute.Home.route
) {
  NavHost(
    navController = navController,
    startDestination = startDestination
  ) {
    calculatorScreen(
      windowSizeInfo = windowSizeInfo,
    )
    settingsScreen(
      windowSizeInfo = windowSizeInfo,
      mainActions = mainActions
    )
  }
}

/**
 * Navigation graph builder extension for calculator screen composable route.
 *
 * @param windowSizeInfo Window size class.
 */
private fun NavGraphBuilder.calculatorScreen(
  windowSizeInfo: WindowSizeInfo,
) {
  composable(AppRoute.Home.route) {
    CalculatorRoute(windowSizeInfo = windowSizeInfo)
  }
}

/**
 * Navigation graph builder extension for settings screen composable route.
 *
 * @param windowSizeInfo Window size class.
 */
private fun NavGraphBuilder.settingsScreen(
  windowSizeInfo: WindowSizeInfo,
  mainActions: MainActions,
) {
  composable(AppRoute.Settings.route) {
    SettingsRoute(
      windowSizeInfo = windowSizeInfo,
      mainActions = mainActions
    )
    /*val context = LocalContext.current
    val settingsViewModel = SettingsViewModel.factory(
      SettingsRepository(context.dataStore)
    ).create(SettingsViewModel::class.java)
    val settingsUiState = settingsViewModel.settingsUiState.collectAsStateWithLifecycle()

    SettingsRoute(
      windowSizeInfo = windowSizeInfo,
      userPreferences = settingsUiState.value,
      onBooleanSettingChanged = settingsViewModel::toggleBooleanPreference,
      onOssLicencesSettingLinkClicked = {
        context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
      },
      onOpeningExternalUrlSettingClicked = { urlText ->
        Timber.d("[AppContent] opening external url: $urlText")
        if (urlText.isNotEmpty()) {
          CustomTabsOpener.openUrl(context, urlText)
        }
      },
      onFeedbackSettingLinkClicked = {
        Timber.d("[AppContent] opening feedback window")
        FeedbackOpener.rate(context)
      }
    )*/
  }
}
