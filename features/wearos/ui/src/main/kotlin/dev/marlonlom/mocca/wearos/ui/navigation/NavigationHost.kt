/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import dev.marlonlom.mocca.wearos.ui.navigation.NavigationRoutes.Calculator
import dev.marlonlom.mocca.wearos.ui.navigation.NavigationRoutes.Home
import dev.marlonlom.mocca.wearos.ui.navigation.NavigationRoutes.Result
import dev.marlonlom.mocca.wearos.ui.navigation.NavigationRoutes.ViewFees

/**
 * Navigation host that wires together app screens using a NavHostController.
 *
 * @author marlonlom
 *
 * @param home Home screen with primary and secondary navigation actions.
 * @param calculatorInput Screen for entering calculation input.
 * @param calculatorOutput Screen for displaying results and handling navigation.
 * @param viewFees Screen showing fee information.
 * @param navController Navigation controller (defaults to swipe-dismissable controller).
 */
@Composable
fun NavigationHost(
  home: @Composable (() -> Unit, () -> Unit) -> Unit,
  calculatorInput: @Composable ((String) -> Unit) -> Unit,
  calculatorOutput: @Composable (String, () -> Unit) -> Unit,
  viewFees: @Composable (() -> Unit) -> Unit,
  navController: NavHostController = rememberSwipeDismissableNavController(),
) = SwipeDismissableNavHost(
  navController = navController,
  startDestination = Home.route,
) {
  composable(route = Home.route) {
    home(
      {
        navController.navigate(Calculator.route)
      },
      {
        navController.navigate(ViewFees.route)
      },
    )
  }

  composable(route = ViewFees.route) {
    viewFees(navController::popBackStack)
  }

  composable(route = Calculator.route) {
    calculatorInput { amountText ->
      navController.navigate(Result.makeRoute(amountText))
    }
  }

  composable(
    route = Result.route,
    arguments = Result.navArguments,
  ) { navBackStackEntry ->
    val amountText = navBackStackEntry.arguments?.getString(Result.INPUT_AMOUNT_ARG)!!
    calculatorOutput(amountText, navController::popBackStack)
  }
}
