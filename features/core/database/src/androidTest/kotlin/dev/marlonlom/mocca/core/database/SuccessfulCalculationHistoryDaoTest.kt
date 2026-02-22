/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.database

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.mocca.core.database.dao.SuccessfulCalculationHistoryDao
import dev.marlonlom.mocca.core.database.entities.SuccessfulCalculationHistory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class SuccessfulCalculationHistoryDaoTest {

  @get:Rule
  val instantExecutorRule = InstantTaskExecutorRule()

  private lateinit var database: MoccaDatabase
  private lateinit var dao: SuccessfulCalculationHistoryDao

  @Before
  fun setup() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    database = Room.inMemoryDatabaseBuilder(context, MoccaDatabase::class.java)
      .allowMainThreadQueries()
      .build()
    dao = database.successfulCalculationHistoryDao()
  }

  @After
  fun teardown() {
    database.close()
  }

  @Test
  fun shouldReturnInsertedRecordAfterInserting() = runBlocking {
    val calculation = SuccessfulCalculationHistory(
      amount = 100,
      fixedFee = 10,
      variableFee = 5,
      total = 115,
      createdAt = java.time.LocalDateTime.now().toString(),
    )
    dao.insert(calculation)
    val result = dao.getAllSuccessCalculationHistory().first()
    assertThat(result).hasSize(1)
    val inserted = result.first()
    assertThat(inserted.amount).isEqualTo(100)
    assertThat(inserted.fixedFee).isEqualTo(10)
    assertThat(inserted.variableFee).isEqualTo(5)
    assertThat(inserted.total).isEqualTo(115)
    assertThat(inserted.createdAt).isNotNull()
  }

  @Test
  fun shouldReturnAllInsertedRecords() = runBlocking {
    repeat(10) { i ->
      dao.insert(
        SuccessfulCalculationHistory(
          amount = i.toLong(),
          fixedFee = 1,
          variableFee = 1,
          total = i.toLong() + 2,
        ),
      )
    }
    val results = dao.getAllSuccessCalculationHistory().first()
    assertThat(results).hasSize(10)
  }

  @Test
  fun shouldRemoveRecordAfterDeletingById() = runBlocking {
    val calculation = SuccessfulCalculationHistory(
      id = 12345,
      amount = 50,
      fixedFee = 5,
      variableFee = 2,
      total = 57,
    )
    dao.insert(calculation)
    dao.deleteById(12345)
    val results = dao.getAllSuccessCalculationHistory().first()
    assertThat(results).isEmpty()
  }

  @Test
  fun shouldRemoveAllRecordsAfterClearingAll() = runBlocking {
    repeat(5) {
      dao.insert(
        SuccessfulCalculationHistory(
          amount = it.toLong(),
          fixedFee = 1,
          variableFee = 1,
          total = it.toLong() + 2,
        ),
      )
    }

    dao.clearAll()
    val results = dao.getAllSuccessCalculationHistory().first()
    assertThat(results).isEmpty()
  }
}
