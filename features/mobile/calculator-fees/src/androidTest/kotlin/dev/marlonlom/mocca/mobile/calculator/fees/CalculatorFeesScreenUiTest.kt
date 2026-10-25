/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.fees

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import dev.marlonlom.mocca.calculator.Calculator
import dev.marlonlom.mocca.calculator.model.OrderFees
import dev.marlonlom.mocca.mobile.calculator.fees.di.calculatorFeesKoinModule
import dev.marlonlom.mocca.mobile.calculator.fees.domain.CalculatorFeesViewModel
import dev.marlonlom.mocca.mobile.calculator.fees.domain.SuccessfulFeesDomainData
import dev.marlonlom.mocca.mobile.calculator.fees.util.InstrumentationUtil
import dev.marlonlom.mocca.mobile.ui.window.MobileWindowSize
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class CalculatorFeesScreenUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Before
  fun setUp() {
    startKoin {
      androidContext(InstrumentationRegistry.getInstrumentation().targetContext.applicationContext)
      modules(
        calculatorFeesKoinModule,
        module {
          single<CalculatorFeesViewModel> {
            CalculatorFeesViewModel(
              calculatorFees = {
                OrderFees.entries.toList()
              },
              calculatorVariableFeeFactor = {
                Calculator.VARIABLE_FEE_FACTOR
              },
            )
          }
        },
      )
    }
  }

  @After
  fun tearDown() {
    stopKoin()
  }

  @Test
  fun shouldDisplayCalculatorFeesScreenWithNonEmptyFees() {
    val domainData = SuccessfulFeesDomainData(
      min = 5_000.0,
      max = 50_000.0,
      fixedFee = 4_700.0,
      haveVariableFee = false,
      variableFeeFactor = 0.0,
    )
    rule.setContent {
      CalculatorFeesScreen(
        mobileWindowSize = MobileWindowSize.MOBILE_LANDSCAPE,
        showCloseButton = true,
        onCloseButtonClicked = {},
      )
    }
    rule.onNodeWithText(
      text = InstrumentationUtil.getStringRes(
        R.string.text_price_range,
        domainData.min,
        domainData.max,
      ),
      useUnmergedTree = true,
    ).assertIsDisplayed()
    rule.onNodeWithText(
      text = InstrumentationUtil.getStringRes(
        R.string.text_fee_amount,
        domainData.fixedFee,
      ),
      useUnmergedTree = true,
    ).assertIsDisplayed()
  }
}
