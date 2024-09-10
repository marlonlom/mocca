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
import dev.marlonlom.mocca.wearos.features.calculator.input.CalculatorInputScreen
import dev.marlonlom.mocca.wearos.features.calculator.output.CalculatorOutputScreen
import dev.marlonlom.mocca.wearos.ui.navigation.NavigationRoutes.Home
import dev.marlonlom.mocca.wearos.ui.navigation.NavigationRoutes.Result

/**
 * Application navigation host composable.
 *
 * @author marlonlom
 */
@Composable
fun NavigationHost(navController: NavHostController = rememberSwipeDismissableNavController()) =
  SwipeDismissableNavHost(
    navController = navController,
    startDestination = Home.route,
  ) {
    composable(route = Home.route) {
      CalculatorInputScreen(
        onCalculationReadyAction = { amountText ->
          navController.navigate(Result.makeRoute(amountText))
        },
      )
    }

    composable(
      route = Result.route,
      arguments = Result.navArguments,
    ) { navBackStackEntry ->
      val amountText = navBackStackEntry.arguments?.getString(Result.INPUT_AMOUNT_ARG)!!
      CalculatorOutputScreen(
        amountText = amountText,
        onBackNavigationAction = navController::popBackStack,
      )
    }
  }
