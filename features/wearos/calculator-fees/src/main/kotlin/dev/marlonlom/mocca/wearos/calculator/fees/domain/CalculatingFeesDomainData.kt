/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.fees.domain

/**
 * Represents the result of a calculation in the domain layer.
 *
 * @author marlonlom
 *
 * @property min Minimum value for the fee range.
 * @property max Maximum value for the fee range.
 * @property fixedFee Fixed fee value.
 * @property haveVariableFee Indicates if variable fee is included.
 * @property variableFeeFactor Variable fee factor value.
 *
 */
data class CalculatingFeesDomainData(
  val min: Double,
  val max: Double,
  val fixedFee: Double,
  val haveVariableFee: Boolean,
  val variableFeeFactor: Double,
)
