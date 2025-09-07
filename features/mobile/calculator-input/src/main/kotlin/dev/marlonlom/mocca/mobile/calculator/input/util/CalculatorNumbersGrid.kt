/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.input.util

import dev.marlonlom.mocca.mobile.calculator.input.util.CalculatorNumbersGrid.sequenceList

/**
 * Object that generates a reversed 3x3 calculator-style number grid.
 *
 * The [sequenceList] property returns a list of strings, each representing a row
 * of numbers from a calculator layout (e.g., "7 8 9", "4 5 6", "1 2 3").
 *
 * @author marlonlom
 */
internal object CalculatorNumbersGrid {

  /**
   * A list of strings representing calculator-style number rows,
   * starting from the top row (7 to 9) down to the bottom (1 to 3).
   *
   * @return List of strings with numbers in calculator format.
   */
  val sequenceList: () -> List<String>
    get() = {
      val sequenceLines = mutableListOf<String>()
      for (i in 2 downTo 0) {
        val startNum = i * 3 + 1
        val line = StringBuilder()
        for (j in 0 until 3) {
          line.append("${startNum + j} ")
        }
        sequenceLines.add(line.toString().trimEnd())
      }
      sequenceLines.toList()
    }
}
