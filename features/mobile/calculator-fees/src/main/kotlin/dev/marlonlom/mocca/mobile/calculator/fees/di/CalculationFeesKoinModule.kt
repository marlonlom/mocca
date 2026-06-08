/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.fees.di

import dev.marlonlom.mocca.calculator.Calculator
import dev.marlonlom.mocca.calculator.model.OrderFees
import dev.marlonlom.mocca.mobile.calculator.fees.domain.CalculatorFeesViewModel
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Calculator fees mobile ui Koin module.
 *
 * @author marlonlom
 */
val calculatorFeesKoinModule = module {
  single<CalculatorFeesViewModel> {
    CalculatorFeesViewModel(
      calculatorFees = {
        OrderFees.entries.toList()
      },
      calculatorVariableFeeFactor = {
        Calculator.VARIABLE_FEE_FACTOR
      },
    )
  } bind CalculatorFeesViewModel::class
}
