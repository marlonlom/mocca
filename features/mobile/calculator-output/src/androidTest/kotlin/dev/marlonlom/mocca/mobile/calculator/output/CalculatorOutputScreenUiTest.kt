/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import dev.marlonlom.mocca.core.database.datasource.LocalDataSource
import dev.marlonlom.mocca.core.database.entities.SuccessfulCalculationHistory
import dev.marlonlom.mocca.mobile.calculator.output.di.calculatorOutputKoinModule
import dev.marlonlom.mocca.mobile.ui.window.MobileWindowSize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
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
internal class CalculatorOutputScreenUiTest {

  @get:Rule
  var rule = createComposeRule()

  @Before
  fun setUp() {
    startKoin {
      androidContext(InstrumentationRegistry.getInstrumentation().targetContext.applicationContext)
      modules(
        calculatorOutputKoinModule,
        module {
          single<LocalDataSource> {
            object : LocalDataSource {
              override suspend fun insertSuccessCalculationHistory(calculationHistory: SuccessfulCalculationHistory) {}
              override fun getAllSuccessCalculationHistory(): Flow<List<SuccessfulCalculationHistory>> =
                flowOf(emptyList())

              override suspend fun deleteAllSuccessCalculationHistory() {}
              override suspend fun deleteSuccessCalculationHistory(calculationHistoryId: Long) {}
            }
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
  fun shouldDisplayFailureLandscapeOutput() {
    rule.setContent {
      CalculatorOutputScreen(
        mobileWindowSize = MobileWindowSize.MOBILE_LANDSCAPE,
        requestedAmount = "350",
        onCloseButtonClicked = {},
      )
    }
    rule.onNodeWithTag(testTag = "calculation_failed_img").assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_calculation_failed), useUnmergedTree = true)
      .assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_below_range_error), useUnmergedTree = true)
      .assertIsDisplayed()
  }

  @Test
  fun shouldDisplayFailurePortraitOutput() {
    rule.setContent {
      CalculatorOutputScreen(
        mobileWindowSize = MobileWindowSize.MOBILE_PORTRAIT,
        requestedAmount = "3500000",
        onCloseButtonClicked = {},
      )
    }
    rule.onNodeWithTag(testTag = "calculation_failed_img").assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_calculation_failed), useUnmergedTree = true)
      .assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_above_range_error), useUnmergedTree = true)
      .assertIsDisplayed()
  }

  @Test
  fun shouldDisplaySuccessLandscapeOutput() {
    rule.setContent {
      CalculatorOutputScreen(
        mobileWindowSize = MobileWindowSize.MOBILE_LANDSCAPE,
        requestedAmount = "5000",
        onCloseButtonClicked = {},
      )
    }
    rule.onNodeWithText(text = getStringRes(R.string.text_calculation_successful), useUnmergedTree = true)
      .assertIsDisplayed()
    rule.onNodeWithTag(testTag = "calculation_success_img").assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_label_requested_amount), useUnmergedTree = true)
      .assertIsDisplayed()
    rule.onNodeWithText(text = "cop $ 5,000", useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_label_fee), useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithText(text = "cop $ 4,700", useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_label_total), useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithText(text = "cop $ 9,700", useUnmergedTree = true).assertIsDisplayed()
  }

  @Test
  fun shouldDisplaySuccessPortraitOutput() {
    rule.setContent {
      CalculatorOutputScreen(
        mobileWindowSize = MobileWindowSize.MOBILE_PORTRAIT,
        requestedAmount = "5000",
        onCloseButtonClicked = {},
      )
    }
    rule.onNodeWithText(text = getStringRes(R.string.text_calculation_successful), useUnmergedTree = true)
      .assertIsDisplayed()
    rule.onNodeWithTag(testTag = "calculation_success_img").assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_label_requested_amount), useUnmergedTree = true)
      .assertIsDisplayed()
    rule.onNodeWithText(text = "cop $ 5,000", useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_label_fee), useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithText(text = "cop $ 4,700", useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithText(text = getStringRes(R.string.text_label_total), useUnmergedTree = true).assertIsDisplayed()
    rule.onNodeWithText(text = "cop $ 9,700", useUnmergedTree = true).assertIsDisplayed()
  }

  private fun getStringRes(@StringRes resId: Int): String =
    InstrumentationRegistry.getInstrumentation().targetContext.getString(resId)
}
