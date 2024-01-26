/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.wearos.presentation.feats.main

import androidx.compose.runtime.Composable
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import dev.marlonlom.apps.mocca.wearos.presentation.feats.calculator_input.CalculatorTextInput
import dev.marlonlom.apps.mocca.wearos.presentation.feats.calculator_output.CalculatorOutput
import dev.marlonlom.apps.mocca.wearos.presentation.feats.main.NavigationRoutes.Home
import dev.marlonlom.apps.mocca.wearos.presentation.feats.main.NavigationRoutes.Result

/**
 * Application navigation host composable.
 *
 * @author marlonlom
 */
@Composable
fun AppNavHost() {
  val navController = rememberSwipeDismissableNavController()
  SwipeDismissableNavHost(
    navController = navController,
    startDestination = Home.route
  ) {
    composable(route = Home.route) {
      CalculatorTextInput(
        onMoneyAmountReadyAction = { amountText ->
          navController.navigate(Result.makeRoute(amountText))
        }
      )
    }

    composable(
      route = Result.route,
      arguments = Result.navArguments
    ) { navBackStackEntry ->
      val amountText = navBackStackEntry.arguments?.getString(Result.inputAmountArg)!!
      CalculatorOutput(
        amountText = amountText,
        onBackNavigationAction = navController::popBackStack
      )
    }
  }
}

/**
 * Navigation routes sealed class.
 *
 * @author marlonlom
 *
 * @property route Navigation route name
 */
sealed class NavigationRoutes(val route: String) {

  /**
   * Home navigation route single object.
   *
   * @author marlonlom
   */
  data object Home : NavigationRoutes(route = "calculator_input")

  /**
   * Calculation result navigation route single object.
   *
   * @author marlonlom
   */
  data object Result : NavigationRoutes(route = "calculator_results/{inputAmount}") {
    const val inputAmountArg = "inputAmount"
    val navArguments = listOf(navArgument("inputAmount") { defaultValue = "-1" })
    fun makeRoute(amountText: String) = "calculator_results/$amountText"
  }
}
