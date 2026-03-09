/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.database.converters

import androidx.room.TypeConverter
import java.util.Date

/**
 * Provides [TypeConverter]s to convert between [Date] and [Long] for Room database.
 *
 * @author marlonlom
 */
class DateConverters {

  /**
   * Converts a timestamp in milliseconds to a [Date].
   *
   * @param value The timestamp in milliseconds, or null.
   * @return Corresponding [Date] object, or null if input is null.
   */
  @TypeConverter
  fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

  /**
   * Converts a [Date] to a timestamp in milliseconds.
   *
   * @param date The [Date] object to convert, or null.
   * @return Timestamp in milliseconds, or null if input is null.
   */
  @TypeConverter
  fun dateToTimestamp(date: Date?): Long? = date?.time
}
