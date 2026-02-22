/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.marlonlom.mocca.core.database.entities.SuccessfulCalculationHistory
import kotlinx.coroutines.flow.Flow

/**
 * DAO for accessing the history of successful calculations.
 *
 * Provides methods to insert new calculation records, retrieve
 * the most recent records, and delete records individually or completely.
 *
 * @author marlonlom
 *
 */
@Dao
interface SuccessfulCalculationHistoryDao {

  /**
   * Inserts a new successful calculation record into the history.
   *
   * @param calculation The calculation record to insert.
   */
  @Insert
  suspend fun insert(calculation: SuccessfulCalculationHistory)

  /**
   * Retrieves all historical calculations, ordered by creation timestamp descending.
   *
   * @return A [Flow] emitting a list of successful calculation records.
   */
  @Query("SELECT * FROM successful_calculation_history ORDER BY created_at DESC")
  fun getAllSuccessCalculationHistory(): Flow<List<SuccessfulCalculationHistory>>

  /**
   * Deletes a calculation record by its ID.
   *
   * @param id The ID of the record to delete.
   */
  @Query("DELETE FROM successful_calculation_history WHERE id = :id")
  suspend fun deleteById(id: Long)

  /**
   * Deletes all historical calculation records.
   */
  @Query("DELETE FROM successful_calculation_history")
  suspend fun clearAll()
}
