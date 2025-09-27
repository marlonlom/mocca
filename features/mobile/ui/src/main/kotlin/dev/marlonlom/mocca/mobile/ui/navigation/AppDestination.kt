/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Represents the different navigation destinations within the mobile app.
 *
 * @author marlonlom
 */
sealed class AppDestination : Parcelable {

  /**
   * Destination representing the main Calculator screen.
   *
   * @author marlonlom
   */
  @Parcelize
  data object Calculator : AppDestination()

  /**
   * Destination representing the Calculating screen,
   * which holds the current calculation state.
   *
   * @author marlonlom
   *
   * @param amountText The input or result currently being calculated.
   */
  @Parcelize
  data class Calculating(val amountText: String) : AppDestination()

  /**
   * Destination representing the History screen.
   *
   * @author marlonlom
   */
  @Parcelize
  data object History : AppDestination()

  /**
   * Destination representing the Settings screen.
   *
   * @author marlonlom
   */
  @Parcelize
  data object Settings : AppDestination()
}
