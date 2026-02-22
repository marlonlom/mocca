/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.marlonlom.mocca.core.database.dao.SuccessfulCalculationHistoryDao
import dev.marlonlom.mocca.core.database.entities.SuccessfulCalculationHistory

/**
 * Mocca room database class.
 *
 * @author marlonlom
 */
@Database(
  entities = [
    SuccessfulCalculationHistory::class,
  ],
  version = 1,
  exportSchema = false,
)
abstract class MoccaDatabase : RoomDatabase() {

  /**
   * Successful calculation history dao instance.
   *
   * @return calculation history dao
   */
  abstract fun successfulCalculationHistoryDao(): SuccessfulCalculationHistoryDao

  companion object {

    @Volatile
    private var instance: MoccaDatabase? = null

    private const val DATABASE_NAME = "mocca-db"

    /**
     * Returns an instance of Cappa database.
     *
     * @param context Application context.
     *
     * @return Database instance.
     */
    fun getInstance(context: Context): MoccaDatabase = instance ?: synchronized(this) {
      instance ?: buildDatabase(context).also { instance = it }
    }

    private fun buildDatabase(context: Context): MoccaDatabase = Room.databaseBuilder(
      context = context,
      klass = MoccaDatabase::class.java,
      name = DATABASE_NAME,
    ).fallbackToDestructiveMigration(false)
      .build()
  }
}
