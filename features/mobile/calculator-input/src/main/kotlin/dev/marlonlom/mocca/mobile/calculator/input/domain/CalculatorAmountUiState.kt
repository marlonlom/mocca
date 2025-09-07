/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.input.domain

/**
 * UI state representing the calculator's current amount input.
 *
 * @author marlonlom
 *
 * @param plainAmount The raw input amount as a plain string.
 * @param formattedAmount The amount formatted as a number (e.g., with commas).
 * @param isValidRange Indicates whether the amount is within the valid range (0 to 3,000,000).
 */
data class CalculatorAmountUiState(
  val plainAmount: String = "0",
  val formattedAmount: String = "0",
  val isValidRange: Boolean = true,
)
