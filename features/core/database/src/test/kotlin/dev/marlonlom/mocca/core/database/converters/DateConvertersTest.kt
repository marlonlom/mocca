/*
 * Copyright 2026 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.core.database.converters

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.util.Date

internal class DateConvertersTest {

  private lateinit var converters: DateConverters

  @Before
  fun setUp() {
    converters = DateConverters()
  }

  @Test
  fun `fromTimestamp should return null when value is null`() {
    val result = converters.fromTimestamp(null)
    assertNull(result)
  }

  @Test
  fun `fromTimestamp should return valid Date when value is provided`() {
    val timestamp = 1715817600000L // 2024-05-16
    val expectedDate = Date(timestamp)
    val result = converters.fromTimestamp(timestamp)
    assertEquals(expectedDate, result)
  }

  @Test
  fun `dateToTimestamp should return null when date is null`() {
    val result = converters.dateToTimestamp(null)
    assertNull(result)
  }

  @Test
  fun `dateToTimestamp should return valid Long when date is provided`() {
    val timestamp = 1715817600000L
    val date = Date(timestamp)
    val result = converters.dateToTimestamp(date)
    assertEquals(timestamp, result)
  }

  @Test
  fun `conversions should be reversible`() {
    val originalTimestamp = System.currentTimeMillis()
    val date = converters.fromTimestamp(originalTimestamp)
    val finalTimestamp = converters.dateToTimestamp(date)
    assertEquals(originalTimestamp, finalTimestamp)
  }
}
