/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dev.marlonlom.apps.mocca.dataStore
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorRoute
import dev.marlonlom.apps.mocca.feats.settings.SettingsRepository
import dev.marlonlom.apps.mocca.feats.settings.SettingsRoute
import dev.marlonlom.apps.mocca.feats.settings.SettingsViewModel
import dev.marlonlom.apps.mocca.ui.util.CustomTabsOpener
import dev.marlonlom.apps.mocca.ui.util.FeedbackOpener
import dev.marlonlom.apps.mocca.ui.util.WindowSizeInfo
import timber.log.Timber

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
) {
  composable(AppRoute.Settings.route) {
    val context = LocalContext.current
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
    )
  }
}
