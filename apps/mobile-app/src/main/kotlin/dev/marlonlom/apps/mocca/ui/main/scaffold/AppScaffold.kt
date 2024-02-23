/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.main.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorRoute
import dev.marlonlom.apps.mocca.feats.settings.SettingsRoute
import dev.marlonlom.apps.mocca.ui.main.MainActions
import dev.marlonlom.apps.mocca.ui.navigation.AppNavHost
import dev.marlonlom.apps.mocca.ui.navigation.AppRoute
import dev.marlonlom.apps.mocca.ui.util.WindowSizeInfo
import timber.log.Timber

/**
 * Main scaffold container composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeInfo Window size class.
 * @param navController Navigation controller.
 */
@ExperimentalMaterial3Api
@Composable
fun AppScaffold(
  windowSizeInfo: WindowSizeInfo,
  mainActions: MainActions,
  navController: NavHostController = rememberNavController(),
) {

  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination?.route ?: AppRoute.Home.route

  Scaffold(
    modifier = Modifier.fillMaxWidth(),
    contentWindowInsets = WindowInsets(0, 0, 0, 0),
    containerColor = MaterialTheme.colorScheme.surface,
    contentColor = MaterialTheme.colorScheme.onSurface,
    topBar = {
      val couldShowTopBar = windowSizeInfo.isTabletLandscape.not()
        .and(windowSizeInfo.indicateInnerContent == ScaffoldInnerContentType.SinglePane)

      if (couldShowTopBar) {
        AppTopBar(
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
    content = { paddingValues ->
      Box(
        modifier = Modifier
          .safeDrawingPadding()
          .padding(paddingValues),
        contentAlignment = Alignment.Center
      ) {
        when (windowSizeInfo.indicateInnerContent) {
          ScaffoldInnerContentType.SinglePane -> {
            AppNavHost(
              navController = navController,
              windowSizeInfo = windowSizeInfo,
              mainActions = mainActions
            )
          }

          is ScaffoldInnerContentType.TwoPane -> {
            val fraction = (windowSizeInfo.indicateInnerContent as ScaffoldInnerContentType.TwoPane).hingeRatio
            Row {
              Column(
                modifier = Modifier
                  .fillMaxWidth(fraction)
                  .fillMaxHeight(),
              ) {
                CalculatorRoute(windowSizeInfo)
              }

              Column(
                modifier = Modifier
                  .fillMaxHeight()
                  .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.25f)),
                horizontalAlignment = Alignment.CenterHorizontally,
              ) {
                SettingsRoute(
                  windowSizeInfo = windowSizeInfo,
                  mainActions = mainActions
                )
              }
            }
          }
        }
      }
    },
  )
}
