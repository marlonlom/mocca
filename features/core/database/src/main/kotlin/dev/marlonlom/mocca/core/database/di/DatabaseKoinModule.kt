/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.database.di

import dev.marlonlom.mocca.core.database.MoccaDatabase
import dev.marlonlom.mocca.core.database.datasource.LocalDataSource
import dev.marlonlom.mocca.core.database.datasource.LocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Database Koin module.
 *
 * @author marlonlom
 */
val databaseKoinModule = module {
  single<LocalDataSource> {
    val moccaDatabase = MoccaDatabase.getInstance(androidContext())
    LocalDataSourceImpl(
      successfulCalculationHistoryDao = moccaDatabase.successfulCalculationHistoryDao(),
    )
  } bind LocalDataSource::class
}
