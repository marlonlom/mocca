/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import dev.marlonlom.mocca.core.preferences.model.UserSettings
import dev.marlonlom.mocca.di.appKoinModule
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import dev.marlonlom.mocca.mobile.calculator.fees.R as FeesR
import dev.marlonlom.mocca.mobile.calculator.history.R as HistoryR
import dev.marlonlom.mocca.mobile.calculator.input.R as InputR
import dev.marlonlom.mocca.mobile.ui.R as UiR

@OptIn(ExperimentalMaterial3Api::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
internal class MainContentUiTest {

  @get:Rule
  var rule = createComposeRule()

  private val context get() = InstrumentationRegistry.getInstrumentation().targetContext

  @Before
  fun setUp() {
    stopKoin()
    startKoin {
      androidContext(context.applicationContext)
      modules(appKoinModule)
    }
  }

  @After
  fun tearDown() {
    stopKoin()
  }

  @Test
  fun shouldDisplayNothingWhenLoadingState() {
    rule.setContent {
      MainContent(
        mainUiState = MainUiState.Loading,
        onOnboarded = {},
        onShortcutConsumed = {},
      )
    }
    rule.onNodeWithTag("onboarding_title").assertDoesNotExist()
    rule.onNodeWithTag("onboarding_img").assertDoesNotExist()
  }

  @Test
  fun shouldDisplayOnboardingWhenUserIsOnboarding() {
    rule.setContent {
      MainContent(
        mainUiState = MainUiState.Success(
          userData = UserSettings(
            useDynamicColor = true,
            useDarkTheme = false,
            colorContrast = "STANDARD",
            isOnboarding = true,
          ),
        ),
        onOnboarded = {},
        onShortcutConsumed = {},
      )
    }
    rule.onNodeWithTag("onboarding_img").assertIsDisplayed()
    rule.onNodeWithTag("onboarding_title").assertIsDisplayed()
    rule.onNodeWithTag("onboarding_desc").assertIsDisplayed()
    rule.onNodeWithTag("onboarding_btn").assertIsDisplayed()
  }

  @Test
  fun shouldTriggerOnOnboardedWhenOnboardingButtonClicked() {
    var onboarded = false
    rule.setContent {
      MainContent(
        mainUiState = MainUiState.Success(
          userData = UserSettings(
            useDynamicColor = true,
            useDarkTheme = false,
            colorContrast = "STANDARD",
            isOnboarding = true,
          ),
        ),
        onOnboarded = { onboarded = true },
        onShortcutConsumed = {},
      )
    }
    rule.onNodeWithTag("onboarding_btn").assertIsDisplayed().performClick()
    assertThat(onboarded).isTrue()
  }

  @Test
  fun shouldDisplayCalculatorInputWhenUserIsOnboarded() {
    rule.setContent {
      MainContent(
        mainUiState = MainUiState.Success(
          userData = UserSettings(
            useDynamicColor = true,
            useDarkTheme = false,
            colorContrast = "STANDARD",
            isOnboarding = false,
          ),
        ),
        onOnboarded = {},
        onShortcutConsumed = {},
      )
    }
    rule.onNodeWithText(context.getString(InputR.string.text_input_title)).assertIsDisplayed()
    rule.onNodeWithContentDescription(context.getString(UiR.string.text_settings)).assertIsDisplayed()
    rule.onNodeWithContentDescription(context.getString(UiR.string.text_history)).assertIsDisplayed()
    rule.onNodeWithContentDescription(context.getString(UiR.string.text_fees)).assertIsDisplayed()
  }

  @Test
  fun shouldNavigateToSettingsWhenSettingsIconClicked() {
    rule.setContent {
      MainContent(
        mainUiState = MainUiState.Success(
          userData = UserSettings(
            useDynamicColor = true,
            useDarkTheme = false,
            colorContrast = "STANDARD",
            isOnboarding = false,
          ),
        ),
        onOnboarded = {},
        onShortcutConsumed = {},
      )
    }
    rule.onNodeWithContentDescription(context.getString(UiR.string.text_settings), useUnmergedTree = true)
      .performClick()
  }

  @Test
  fun shouldNavigateToHistoryWhenHistoryIconClicked() {
    rule.setContent {
      MainContent(
        mainUiState = MainUiState.Success(
          userData = UserSettings(
            useDynamicColor = true,
            useDarkTheme = false,
            colorContrast = "STANDARD",
            isOnboarding = false,
          ),
        ),
        onOnboarded = {},
        onShortcutConsumed = {},
      )
    }
    rule.onNodeWithContentDescription(context.getString(UiR.string.text_history), useUnmergedTree = true).performClick()
    rule.onNodeWithText(context.getString(HistoryR.string.text_empty_history_title), useUnmergedTree = true)
      .assertIsDisplayed()
  }

  @Test
  fun shouldNavigateToFeesWhenFeesIconClicked() {
    rule.setContent {
      MainContent(
        mainUiState = MainUiState.Success(
          userData = UserSettings(
            useDynamicColor = true,
            useDarkTheme = false,
            colorContrast = "STANDARD",
            isOnboarding = false,
          ),
        ),
        onOnboarded = {},
        onShortcutConsumed = {},
      )
    }
    rule.onNodeWithContentDescription(context.getString(UiR.string.text_fees), useUnmergedTree = true).performClick()
    rule.onNodeWithText(
      context.getString(FeesR.string.text_price_range, 5000.0, 50000.0),
      useUnmergedTree = true,
    ).assertIsDisplayed()
  }

  @Test
  fun shouldNavigateToFeesAndConsumeShortcutWhenShouldOpenFeesIsTrue() {
    var shortcutConsumed = false
    rule.setContent {
      MainContent(
        mainUiState = MainUiState.Success(
          userData = UserSettings(
            useDynamicColor = true,
            useDarkTheme = false,
            colorContrast = "STANDARD",
            isOnboarding = false,
          ),
          shouldOpenFees = true,
        ),
        onOnboarded = {},
        onShortcutConsumed = { shortcutConsumed = true },
      )
    }
    rule.onNodeWithText(
      context.getString(FeesR.string.text_price_range, 5000.0, 50000.0),
      useUnmergedTree = true,
    ).assertIsDisplayed()
    assertThat(shortcutConsumed).isTrue()
  }

  @Test
  fun shouldPerformCalculationWhenAmountEnteredAndCalculateButtonClicked() {
    rule.setContent {
      MainContent(
        mainUiState = MainUiState.Success(
          userData = UserSettings(
            useDynamicColor = true,
            useDarkTheme = false,
            colorContrast = "STANDARD",
            isOnboarding = false,
          ),
        ),
        onOnboarded = {},
        onShortcutConsumed = {},
      )
    }
    rule.onNodeWithText("5").performClick()
    rule.onNodeWithText("0").performClick()
    rule.onNodeWithText("0").performClick()
    rule.onNodeWithText("0").performClick()
    rule.onNodeWithText(context.getString(UiR.string.text_calculate)).performClick()
  }
}
