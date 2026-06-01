/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.history.domain

import androidx.compose.runtime.Stable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Represents the result of a successful calculation in the domain layer.
 *
 * @author marlonlom
 *
 * @property calculationId Unique identifier of the calculation.
 * @property amount Base amount used for the calculation.
 * @property fixedFee Fixed fee applied to the amount.
 * @property variableFee Variable fee applied to the amount.
 * @property total Final total amount including all fees.
 * @property createdAt Timestamp of when the calculation was created.
 */
@Stable
data class SuccessfulCalculationDomainData(
  val calculationId: Long,
  val amount: Long,
  val fixedFee: Long,
  val variableFee: Long,
  val total: Long,
  val createdAt: Date,
) {

  /**
   * Returns the first non-zero fee between [fixedFee] and [variableFee].
   *
   * If both are zero, returns 0.
   */
  val feeToUse get() = listOf(fixedFee, variableFee).firstOrNull { it > 0L } ?: 0L

  /**
   * Returns the [createdAt] date formatted as a string.
   *
   * - If the date is today, returns only the time (`HH:mm`).
   * - Otherwise, returns day, abbreviated month, and time (`dd MMM, HH:mm`).
   *
   * @param locale Locale to use for month names. Defaults to [Locale.getDefault].
   * @return Formatted date string.
   */
  fun formattedCreationDate(locale: Locale = Locale.getDefault()): String {
    val calendar = Calendar.getInstance(TimeZone.getDefault())
    calendar.time = createdAt
    val today = Calendar.getInstance(locale)
    val isToday = calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
      calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)
    val isSameYear = calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
      calendar.get(Calendar.DAY_OF_YEAR) != today.get(Calendar.DAY_OF_YEAR)
    val pattern = when {
      isToday -> "HH:mm"
      isSameYear -> "dd MMM, HH:mm"
      else -> "dd MMM ''yy, HH:mm"
    }
    val formatter = SimpleDateFormat(pattern, locale).apply {
      timeZone = TimeZone.getDefault()
    }
    return formatter.format(createdAt)
  }
}
