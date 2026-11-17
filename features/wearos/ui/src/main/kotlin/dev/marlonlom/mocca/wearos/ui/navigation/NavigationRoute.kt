/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Default amount text used when no calculated amount is provided.
 */
private const val DEFAULT_AMOUNT_TEXT = "-1"

/**
 * Navigation routes sealed class.
 *
 * @author marlonlom
 *
 */
@Serializable
sealed interface NavigationRoute : NavKey {

  /**
   * Home navigation route single object.
   *
   * @author marlonlom
   */
  @Serializable
  data object Home : NavigationRoute

  /**
   * Calculation fees listing navigation route single object.
   *
   * @author marlonlom
   */
  @Serializable
  data object ViewFees : NavigationRoute

  /**
   * Calculation input navigation route single object.
   *
   * @author marlonlom
   */
  @Serializable
  data object Calculator : NavigationRoute

  /**
   * Navigation route for displaying a calculation result.
   *
   * @author marlonlom
   *
   * @property amountText The calculated monetary amount as text.
   */
  @Serializable
  data class Result(val amountText: String = DEFAULT_AMOUNT_TEXT) : NavigationRoute
}
