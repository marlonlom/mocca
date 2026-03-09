/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Represents a successful calculation stored in the local database.
 * Stores the base amount, applied fees, total result, and creation timestamp.
 *
 * @author marlonlom
 *
 * @property id Auto-generated unique identifier for the calculation entry.
 * @property createdAt Timestamp of when the calculation was created. Defaults to the current date/time.
 * @property amount Original amount used in the calculation.
 * @property fixedFee Fixed fee applied to the calculation.
 * @property variableFee Variable fee applied to the calculation.
 * @property total Total amount including fees.
 */
@Entity(tableName = "successful_calculation_history")
data class SuccessfulCalculationHistory(

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo
  val id: Long = 0,

  @ColumnInfo(name = "created_at")
  val createdAt: Date? = Date(),

  @ColumnInfo
  val amount: Long,

  @ColumnInfo
  val fixedFee: Long,

  @ColumnInfo
  val variableFee: Long,

  @ColumnInfo
  val total: Long,
)
