/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.database.datasource

import dev.marlonlom.mocca.core.database.dao.SuccessfulCalculationHistoryDao
import dev.marlonlom.mocca.core.database.entities.SuccessfulCalculationHistory
import kotlinx.coroutines.flow.Flow

/**
 * Local data source concrete implementation class.
 *
 * @param successfulCalculationHistoryDao DAO for accessing successful calculation history.
 *
 * @author marlonlom
 */
class LocalDataSourceImpl(private val successfulCalculationHistoryDao: SuccessfulCalculationHistoryDao) :
  LocalDataSource {

  override suspend fun insertSuccessCalculationHistory(calculationHistory: SuccessfulCalculationHistory) =
    successfulCalculationHistoryDao.insert(calculationHistory)

  override fun getAllSuccessCalculationHistory(): Flow<List<SuccessfulCalculationHistory>> =
    successfulCalculationHistoryDao.getAllSuccessCalculationHistory()

  override suspend fun deleteAllSuccessCalculationHistory() = successfulCalculationHistoryDao.clearAll()

  override suspend fun deleteSuccessCalculationHistory(calculationHistoryId: Long) =
    successfulCalculationHistoryDao.deleteById(calculationHistoryId)
}
