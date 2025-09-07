/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.input.di

import dev.marlonlom.mocca.calculator.Calculator
import dev.marlonlom.mocca.mobile.calculator.input.domain.CalculatorInputViewModel
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Calculator input mobile ui Koin module.
 *
 * @author marlonlom
 */
val calculatorInputKoinModule = module {
  single<CalculatorInputViewModel> {
    CalculatorInputViewModel(amountRange = Calculator.calculatorAmountRange())
  } bind CalculatorInputViewModel::class
}
