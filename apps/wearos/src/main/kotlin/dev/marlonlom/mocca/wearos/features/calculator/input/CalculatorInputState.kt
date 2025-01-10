/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.features.calculator.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

/**
 * Composable function that remembers the value of the calculator input state.
 *
 * @author marlonlom
 *
 * @param initialText Default text for calculator input value.
 * @return Remembered value of the calculator input state.
 */
@Composable
fun rememberCalculatorInputState(initialText: String = CalculatorInputState.TEXT_ZERO): CalculatorInputState =
  rememberSaveable(initialText, saver = CalculatorInputState.SAVER) {
    CalculatorInputState(initialText)
  }

/**
 * Calculator input state.
 *
 * @author marlonlom
 *
 * @constructor
 * Constructs an instance of calculator input state using the initial text.
 *
 * @param initialText Default text for calculator input value.
 */
@Stable
class CalculatorInputState(initialText: String = TEXT_ZERO) {

  /** Text value for calculator input state. */
  var textValue by mutableStateOf(initialText)
    private set

  /**
   * Updates the text value using the digit text.
   *
   * @param digitText Calculator button text.
   */
  fun update(digitText: String) {
    textValue = when {
      textValue == TEXT_ZERO -> if (isDeleteOrDoneButtonText(digitText)) TEXT_ZERO else digitText
      digitText == "⌫" -> if (textValue.length == 1) TEXT_ZERO else textValue.substring(0, textValue.length - 1)
      else -> {
        textValue.plus(
          if ((textValue.length >= 7).or(isDeleteOrDoneButtonText(digitText))) {
            TEXT_EMPTY
          } else {
            digitText
          },
        )
      }
    }
  }

  /**
   * Checks if provided calculator button text is delete character or the check/done character.
   *
   * @param digitText Calculator button text.
   */
  private fun isDeleteOrDoneButtonText(digitText: (String)) = "✔;⌫".split(";").contains(digitText)

  /**
   * Companion object that contains useful constants for the Calculator input state class.
   *
   * @author marlonlom
   *
   */
  companion object {
    /** Default value for Zero as String. */
    internal const val TEXT_ZERO = "0"

    /** Default value for empty text as String. */
    private const val TEXT_EMPTY = ""

    /** Configured Saver for being used when remembering the value of the calculator input state. */
    val SAVER: Saver<CalculatorInputState, *> = listSaver(
      save = { listOf(it.textValue) },
      restore = { CalculatorInputState(it[0]) },
    )
  }
}
