/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.di

import androidx.lifecycle.SavedStateHandle
import dev.marlonlom.mocca.calculator.Calculator
import dev.marlonlom.mocca.calculator.RequestedQuantity
import dev.marlonlom.mocca.mobile.calculator.output.domain.CalculatorOutputViewModel
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Calculator output mobile ui Koin module.
 *
 * @author marlonlom
 */
val calculatorOutputKoinModule = module {
  single<CalculatorOutputViewModel> {
    CalculatorOutputViewModel(
      savedStateHandle = SavedStateHandle(),
      doCalculation = { Calculator.calculate(RequestedQuantity(orderValue = it)) },
    )
  } bind CalculatorOutputViewModel::class
}
