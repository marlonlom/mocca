/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a historical record of a successfully completed calculation.
 *
 * Stores the base amount, applied fees, total result, and creation timestamp.
 *
 * @author marlonlom
 */
@Entity(tableName = "successful_calculation_history")
data class SuccessfulCalculationHistory(

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo
  val id: Long = 0,

  @ColumnInfo(
    name = "created_at",
    defaultValue = "CURRENT_TIMESTAMP",
  )
  val createdAt: String? = null,

  @ColumnInfo
  val amount: Long,

  @ColumnInfo
  val fixedFee: Long,

  @ColumnInfo
  val variableFee: Long,

  @ColumnInfo
  val total: Long,
)
