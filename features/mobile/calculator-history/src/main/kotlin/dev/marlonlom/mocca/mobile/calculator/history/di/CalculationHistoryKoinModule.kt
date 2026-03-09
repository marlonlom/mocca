/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.history.di

import dev.marlonlom.mocca.core.database.datasource.LocalDataSource
import dev.marlonlom.mocca.mobile.calculator.history.domain.CalculatorHistoryViewModel
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Calculator history mobile ui Koin module.
 *
 * @author marlonlom
 */
val calculatorHistoryKoinModule = module {
  single<CalculatorHistoryViewModel> {
    CalculatorHistoryViewModel(historyDataSource = get<LocalDataSource>())
  } bind CalculatorHistoryViewModel::class
}
