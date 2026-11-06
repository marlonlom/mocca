/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.main

import android.content.Intent

/**
 * Represents the possible UI states of the Wear application.
 *
 * @author marlonlom
 *
 */
sealed interface WearAppState {

  /**
   * Represents the calculation state with an amount provided.
   *
   * @author marlonlom
   *
   * @property calculatingAmount Amount currently being entered.
   */
  data class CalculationInput(val calculatingAmount: String? = null) : WearAppState

  /**
   * Represents the calculation display state without a predefined amount.
   *
   * @author marlonlom
   */
  data object CalculationDisplay : WearAppState

  /**
   * Represents the fees display state.
   *
   * @author marlonlom
   */
  data object FeesDisplay : WearAppState

  /**
   * Represents an unknown display state.
   *
   * @author marlonlom
   */
  data object Unknown : WearAppState

  /**
   * Factory for creating application states from Android Intents.
   */
  companion object {
    /** Intent extra containing the calculation amount. */
    private const val EXTRA_CALCULATING_AMOUNT = "calculatingAmount"

    /** Intent extra identifying the widget action. */
    private const val EXTRA_WIDGET_ACTION = "widget_action"

    /** Widget action to display the calculation input screen. */
    private const val ACTION_INPUT_CALCULATION = "INPUT_CALCULATION"

    /** Widget action to display the fees screen. */
    private const val ACTION_SHOW_FEES = "SHOW_FEES"

    /**
     * Creates an application state from the provided Intent.
     *
     * @param intent Intent containing the state information.
     * @return The corresponding application state.
     */
    fun fromIntent(intent: Intent?): WearAppState {
      if (intent == null) return Unknown
      if (intent.hasExtra(EXTRA_CALCULATING_AMOUNT)) {
        return CalculationInput(
          calculatingAmount = intent.getStringExtra(EXTRA_CALCULATING_AMOUNT),
        )
      }
      if (intent.hasExtra(EXTRA_WIDGET_ACTION)) {
        return when (intent.getStringExtra(EXTRA_WIDGET_ACTION)) {
          ACTION_INPUT_CALCULATION -> CalculationDisplay
          ACTION_SHOW_FEES -> FeesDisplay
          else -> Unknown
        }
      }
      return Unknown
    }
  }
}

/**
 * Converts a [WearAppState] into a route pair used for navigation mapping.
 */
fun WearAppState.toRoutePair(): Pair<Int, String> = when (this) {
  is WearAppState.CalculationDisplay -> 1 to ""
  is WearAppState.FeesDisplay -> 2 to ""
  is WearAppState.CalculationInput -> 3 to calculatingAmount!!
  WearAppState.Unknown -> 0 to ""
}
