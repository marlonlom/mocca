/*
 * Copyright 2026 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.wearos.calculator.tile.domain

import kotlin.math.absoluteValue

/**
 * Calculator state holder for Wear OS tile.
 *
 * @author marlonlom
 */
internal object CalculatorStateHolder {

  /** Maximum character limit for the calculator amount input text. */
  private const val AMOUNT_TEXT_LIMIT = 8

  /** Currently accumulated input text displayed in the calculator tile. */
  private var accumulatedText: String = 0.toString()

  /**
   * Calculator state data containing the amount.
   *
   * @property amount The input amount as a Long, limited to a maximum of 6 digits.
   */
  data class State(
    val amount: Long,
  )

  /**
   * Initializes and returns a [State] instance from the given typed text.
   *
   * @param selectedText The selected text retrieved from state map.
   * @return A [State] instance with an amount of type [Long] (max 6 digits).
   */
  fun from(selectedText: String?): State {
    when {
      selectedText == "-" -> {
        accumulatedText = if (accumulatedText.length <= 1) {
          0.toString()
        } else {
          accumulatedText.dropLast(1)
        }
      }

      selectedText != null && selectedText.all { it.isDigit() } -> {
        accumulatedText = if (accumulatedText == "0") {
          selectedText
        } else {
          if (accumulatedText.length < AMOUNT_TEXT_LIMIT) {
            accumulatedText + selectedText
          } else {
            accumulatedText
          }
        }
      }
    }

    val limitedText =
      if (accumulatedText.length > AMOUNT_TEXT_LIMIT) accumulatedText.take(AMOUNT_TEXT_LIMIT) else accumulatedText
    val amount = limitedText.toLongOrNull()?.absoluteValue ?: 0L
    return State(amount)
  }
}
