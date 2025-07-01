/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.ui.navigation

/**
 * A sealed interface representing the possible navigation routes within the application.
 *
 * @author marlonlom
 *
 */
sealed interface AppRoute {
  /**
   * The route for the home screen.
   *
   * @author marlonlom
   *
   */
  data object Home : AppRoute

  /**
   * The route for the result screen, carrying an amount as a string.
   *
   * @author marlonlom
   *
   * @param amountText The amount to display on the result screen.
   */
  data class Result(val amountText: String) : AppRoute

  /**
   * The route for the settings screen.
   *
   * @author marlonlom
   *
   */
  data object Settings : AppRoute
}
