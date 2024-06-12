/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.ui.navigation

/**
 * Navigation Screen route sealed class.
 *
 * @property route Route id.
 */
sealed class AppRoute(val route: String) {
  data object Home : AppRoute("home")
  data object Settings : AppRoute("settings")
}
