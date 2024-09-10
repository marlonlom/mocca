/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.navigation

import androidx.navigation.navArgument

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

    /** Input amount argument name as constant. */
    const val INPUT_AMOUNT_ARG = "inputAmount"

    /** Result navigation route arguments as list. */
    val navArguments = listOf(navArgument(INPUT_AMOUNT_ARG) { defaultValue = "-1" })

    /**
     * Generates dynamic route for result navigation route.
     *
     * @param amountText Money amount as text.
     */
    fun makeRoute(amountText: String) = "calculator_results/$amountText"
  }
}
