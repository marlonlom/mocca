/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.main

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class SmartwatchActivityTest {

  @get:Rule
  var androidComposeTestRule = createAndroidComposeRule<SmartwatchActivity>()

  @Test
  fun shouldDisplayActivityContents() {
    with(androidComposeTestRule) {
      onNodeWithText("Money amount").assertIsDisplayed()
    }
  }

  @Test
  fun shouldShowCalculationBelowRangeFailure() {
    with(androidComposeTestRule) {
      onNodeWithText("Money amount").assertIsDisplayed()
      onNodeWithTag("calculatorButton_9").performClick().performClick().performClick()
      onNodeWithText("✔").performClick()

      onNodeWithText("You cannot send less than COP \$5,000.").assertIsDisplayed()
    }
  }

  @Test
  fun shouldShowCalculationAboveRangeFailure() {
    with(androidComposeTestRule) {
      onNodeWithText("Money amount").assertIsDisplayed()
      onNodeWithTag("calculatorButton_4").performClick()
      onNodeWithTag("calculatorButton_5").performClick()
      onNodeWithTag("calculatorButton_0").performClick().performClick()
        .performClick().performClick().performClick()
      onNodeWithText("✔").performClick()

      onNodeWithText("You cannot send more than COP \$3,000,000.").assertIsDisplayed()
    }
  }

  @Test
  fun shouldShowCalculationSuccess() {
    with(androidComposeTestRule) {
      onNodeWithText("Money amount").assertIsDisplayed()
      onNodeWithTag("calculatorButton_4").performClick()
      onNodeWithTag("calculatorButton_5").performClick()
      onNodeWithTag("calculatorButton_0").performClick().performClick().performClick()
      onNodeWithText("✔").performClick()

      onNodeWithText("Transferring fee").assertIsDisplayed()
      onNodeWithText("COP \$ 4700").assertIsDisplayed()
      onNodeWithText("Total to pay").assertIsDisplayed()
      onNodeWithText("COP \$ 49700").assertIsDisplayed()
    }
  }

  @Test
  fun shouldShowFailureThenSuccessMakingCalculation() {
    with(androidComposeTestRule) {
      onNodeWithText("Money amount").assertIsDisplayed()

      onNodeWithTag("calculatorButton_4").performClick()
      onNodeWithTag("calculatorButton_5").performClick()
      onNodeWithTag("calculatorButton_0").performClick().performClick()
      onNodeWithText("✔").performClick()

      onNodeWithText("You cannot send less than COP \$5,000.").assertIsDisplayed()

      onNodeWithTag("backButtonFromFailure").assertIsDisplayed().performClick()
      onNodeWithTag("calculatorButton_0").performClick()
      onNodeWithText("✔").performClick()

      onNodeWithText("Transferring fee").assertIsDisplayed()
      onNodeWithText("COP \$ 4700").assertIsDisplayed()
      onNodeWithText("Total to pay").assertIsDisplayed()
      onNodeWithText("COP \$ 49700").assertIsDisplayed()
    }
  }
}
