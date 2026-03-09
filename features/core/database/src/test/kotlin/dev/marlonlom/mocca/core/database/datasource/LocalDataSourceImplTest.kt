/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.database.datasource

import dev.marlonlom.mocca.core.database.dao.SuccessfulCalculationHistoryDao
import dev.marlonlom.mocca.core.database.entities.SuccessfulCalculationHistory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Date

class LocalDataSourceImplTest {

  private val dao: SuccessfulCalculationHistoryDao = mockk()
  private lateinit var dataSource: LocalDataSource

  @Before
  fun setUp() {
    dataSource = LocalDataSourceImpl(dao)
  }

  @Test
  fun `insertSuccessCalculationHistory should delegate call to dao`() = runTest {
    val history = createMockHistory()
    coEvery { dao.insert(any()) } returns Unit
    dataSource.insertSuccessCalculationHistory(history)
    coVerify(exactly = 1) { dao.insert(history) }
  }

  @Test
  fun `getAllSuccessCalculationHistory should return flow of history from dao`() = runTest {
    val mockList = listOf(createMockHistory(id = 1), createMockHistory(id = 2))
    val expectedFlow = flowOf(mockList)
    every { dao.getAllSuccessCalculationHistory() } returns expectedFlow
    dataSource.getAllSuccessCalculationHistory().collect { list ->
      assertEquals(2, list.size)
      assertEquals(1000L, list[0].amount)
    }
  }

  @Test
  fun `deleteAllSuccessCalculationHistory should trigger dao clearAll`() = runTest {
    coEvery { dao.clearAll() } returns Unit
    dataSource.deleteAllSuccessCalculationHistory()
    coVerify(exactly = 1) { dao.clearAll() }
  }

  @Test
  fun `deleteSuccessCalculationHistory should call dao deleteById`() = runTest {
    val testId = 99L
    coEvery { dao.deleteById(any()) } returns Unit
    dataSource.deleteSuccessCalculationHistory(testId)
    coVerify(exactly = 1) { dao.deleteById(testId) }
  }

  private fun createMockHistory(id: Long = 0) = SuccessfulCalculationHistory(
    id = id,
    createdAt = Date(),
    amount = 1000L,
    fixedFee = 50L,
    variableFee = 20L,
    total = 1070L,
  )
}
