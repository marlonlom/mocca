/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.navigation

import androidx.activity.compose.LocalActivity
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
 * @param home Composable for the home screen, receiving navigation/action callbacks.
 * @param calculatorInput Composable for entering calculator data, receiving a submit callback.
 * @param calculatorOutput Composable for displaying calculation results, receiving the output and a back callback.
 * @param viewFees Composable for viewing fees, receiving a back callback.
 * @param targetDestination A pair containing the target screen identifier and optional route payload.
 * @param navController The navigation controller used to manage app state, defaulting to a swipe-dismissable controller.
 */
@Composable
fun NavigationHost(
  home: @Composable (() -> Unit, () -> Unit) -> Unit,
  calculatorInput: @Composable ((String) -> Unit) -> Unit,
  calculatorOutput: @Composable (String, () -> Unit) -> Unit,
  viewFees: @Composable (() -> Unit) -> Unit,
  targetDestination: Pair<Int, String>,
  navController: NavHostController = rememberSwipeDismissableNavController(),
) = SwipeDismissableNavHost(
  navController = navController,
  startDestination = when (targetDestination.first) {
    1 -> Calculator.route
    2 -> ViewFees.route
    3 -> Result.makeRoute(targetDestination.second)
    else -> Home.route
  },
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
    val activity = LocalActivity.current
    viewFees {
      if (!navController.popBackStack()) {
        activity?.finish()
      }
    }
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
