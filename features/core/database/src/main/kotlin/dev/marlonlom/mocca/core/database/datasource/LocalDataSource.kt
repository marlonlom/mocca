/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.database.datasource

import dev.marlonlom.mocca.core.database.entities.SuccessfulCalculationHistory
import kotlinx.coroutines.flow.Flow

/**
 * Provides local data access for successful calculation history.
 *
 * This interface defines methods to insert, retrieve, and delete
 * successful calculation records stored in the local database.
 *
 *  @author marlonlom
 */
interface LocalDataSource {

  /**
   * Inserts a new successful calculation record into the history.
   *
   * @param calculationHistory The calculation record to insert.
   */
  suspend fun insertSuccessCalculationHistory(calculationHistory: SuccessfulCalculationHistory)

  /**
   * Returns up to the latest 20 successful calculation history records,
   * ordered by creation timestamp descending.
   *
   * @return A [Flow] emitting a list of successful calculation records.
   */
  fun getAllSuccessCalculationHistory(): Flow<List<SuccessfulCalculationHistory>>

  /**
   * Deletes all successful calculation history records.
   */
  suspend fun deleteAllSuccessCalculationHistory()

  /**
   * Deletes a single successful calculation history record by its ID.
   *
   * @param calculationHistoryId The ID of the calculation record to delete.
   */
  suspend fun deleteSuccessCalculationHistory(calculationHistoryId: Long)
}
