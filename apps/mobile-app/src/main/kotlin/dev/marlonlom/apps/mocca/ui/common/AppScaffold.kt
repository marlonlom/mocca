/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
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
import dev.marlonlom.apps.mocca.ui.navigation.AppRoute
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil
import timber.log.Timber

/**
 * Main scaffold container composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeUtil Window size class.
 * @param navController Navigation controller.
 */
@ExperimentalMaterial3Api
@Composable
fun AppScaffold(
  windowSizeUtil: WindowSizeUtil,
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
      if (!windowSizeUtil.isTabletLandscape) {
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
          .padding(paddingValues)
          .fillMaxWidth(),
        contentAlignment = Alignment.Center
      ) {
        AppNavHost(
          navController = navController,
          windowSizeUtil = windowSizeUtil,
        )
      }
    },
  )
}
