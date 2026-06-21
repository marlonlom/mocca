/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.fees.domain

import java.text.NumberFormat
import java.util.Locale

/**
 * Utility object for formatting monetary values in Colombian Pesos (COP) and creating
 * compact numeric representations.
 *
 * @author marlonlom
 */
object ColombianPesoFormatter {

  /**
   * Formats a raw numeric [value] into a standard Colombian Peso currency string representation.
   *
   * This method applies the `es-CO` locale rules, ensuring the correct currency symbols,
   * thousands separators, and truncates all decimal fractional digits since COP does not
   * practically use cents.
   *
   * Example: `1500000` becomes `"$ 1.500.000"` (exact spacing depends on OS/Java runtime locale data).
   *
   * @param value The amount in Colombian Pesos as a [Long].
   * @return A localized, formatted currency [String] with zero decimal digits.
   */
  fun formatCOP(value: Long): String = NumberFormat
    .getCurrencyInstance(Locale.forLanguageTag("es-CO"))
    .also {
      it.maximumFractionDigits = 0
      it.minimumFractionDigits = 0
    }
    .format(value)

  /**
   * Formats a numeric [value] into a compact, human-readable abbreviation (e.g., thousands as 'K', millions as 'M').
   *
   * The abbreviation rounds down to a single decimal place if a fraction exists, dropping trailing
   * zeros if it evaluates to a whole number.
   *
   * Examples:
   * - `format(500)` -> `"$500"`
   * - `format(1500)` -> `"$1.5K"`
   * - `format(2000000)` -> `"$2M"`
   *
   * @param value The amount to be formatted as a [Long].
   * @param symbol The currency prefix to append. Defaults to `"$"` if not provided.
   * @return A compact formatted [String] representation of the value.
   */
  fun format(value: Long, symbol: String = "$"): String = symbol + when {
    value >= 1_000_000 -> formatDecimal(value / 1_000_000.0) + "M"
    value >= 1_000 -> formatDecimal(value / 1_000.0) + "K"
    else -> value.toString()
  }

  /**
   * Truncates a [Double] value to one decimal place and formats it as a string.
   *
   * If the truncated value has no fractional part (e.g., `2.0`), it drops the decimal
   * entirely and returns a whole number string (`"2"`).
   *
   * @param value The decimal number to format.
   * @return A [String] representation of the number rounded to a single decimal point.
   */
  private fun formatDecimal(value: Double): String {
    val rounded = (value * 10).toInt() / 10.0
    return if (rounded % 1.0 == 0.0) {
      rounded.toInt().toString()
    } else {
      rounded.toString()
    }
  }
}
