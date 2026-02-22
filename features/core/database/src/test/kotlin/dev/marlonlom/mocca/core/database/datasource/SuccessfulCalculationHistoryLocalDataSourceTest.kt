/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.database.datasource

import dev.marlonlom.mocca.core.database.dao.SuccessfulCalculationHistoryDao
import dev.marlonlom.mocca.core.database.entities.SuccessfulCalculationHistory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

internal class SuccessfulCalculationHistoryLocalDataSourceTest {

  private lateinit var dataSource: LocalDataSource
  private val calculationHistoryDao: SuccessfulCalculationHistoryDao = mockk()

  @Before
  fun setup() {
    dataSource = LocalDataSourceImpl(successfulCalculationHistoryDao = calculationHistoryDao)
  }

  @Test
  fun shouldSaveAndRetrieveCalculation() = runBlocking {
    val calculation = SuccessfulCalculationHistory(
      id = 1,
      amount = 100,
      fixedFee = 10,
      variableFee = 5,
      total = 115,
      createdAt = "2026-02-22T12:00:00",
    )
    coEvery { calculationHistoryDao.insert(any()) } returns Unit
    every { calculationHistoryDao.getAllSuccessCalculationHistory() } returns flowOf(listOf(calculation))
    dataSource.insertSuccessCalculationHistory(calculation)
    val result = dataSource.getAllSuccessCalculationHistory().first()
    assertEquals(1, result.size)
    assertEquals(100, result.first().amount)
    coVerify(exactly = 1) { calculationHistoryDao.insert(calculation) }
  }

  @Test
  fun shouldReturnEmptyListWhenNoRecordsExist() = runBlocking {
    every { calculationHistoryDao.getAllSuccessCalculationHistory() } returns flowOf(emptyList())
    val result = dataSource.getAllSuccessCalculationHistory().first()
    assertTrue(result.isEmpty())
    coVerify(exactly = 1) {
      @Suppress("UnusedFlow")
      calculationHistoryDao.getAllSuccessCalculationHistory()
    }
    confirmVerified(calculationHistoryDao)
  }

  @Test
  fun shouldDeleteRecordById() = runBlocking {
    val idToDelete = 99L
    coEvery { calculationHistoryDao.deleteById(idToDelete) } returns Unit
    dataSource.deleteSuccessCalculationHistory(idToDelete)
    coVerify(exactly = 1) { calculationHistoryDao.deleteById(idToDelete) }
  }

  @Test
  fun shouldClearAllRecords() = runBlocking {
    coEvery { calculationHistoryDao.clearAll() } returns Unit
    dataSource.deleteAllSuccessCalculationHistory()
    coVerify(exactly = 1) { calculationHistoryDao.clearAll() }
  }
}
