/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.navigation

/**
 * Represents the different navigation destinations within the mobile app.
 *
 * @author marlonlom
 */
sealed class AppDestination {

  /**
   * Destination representing the main Calculator screen.
   *
   * @author marlonlom
   */
  data object Calculator : AppDestination()

  /**
   * Destination representing the Calculating screen,
   * which holds the current calculation state.
   *
   * @author marlonlom
   *
   * @param amountText The input or result currently being calculated.
   */
  data class Calculating(val amountText: String) : AppDestination()

  /**
   * Destination representing the History screen.
   *
   * @author marlonlom
   */
  data object History : AppDestination()

  /**
   * Destination representing the Settings screen.
   *
   * @author marlonlom
   */
  data object Settings : AppDestination()
}
