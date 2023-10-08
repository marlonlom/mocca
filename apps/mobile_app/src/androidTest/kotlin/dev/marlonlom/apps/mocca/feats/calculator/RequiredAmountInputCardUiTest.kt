/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterial3WindowSizeClassApi
internal class RequiredAmountInputCardUiTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun shouldCheckValidAmountInput() {
    composeTestRule.setContent {
      val amountTextState = rememberSaveable { mutableStateOf("") }
      RequiredAmountInputCard(
        windowSizeUtil = defaultWindowSizeUtility(),
        amountTextState = amountTextState,
        onClearedAmountText = {}
      )
    }
    getAmountInputTitle().assertIsDisplayed()
    val textFieldWithTag = getAmountInputField()
    with(textFieldWithTag) {
      assertIsDisplayed()
      performTextInput("123456")
      for ((key, value) in fetchSemanticsNode().config) {
        if (key.name == "EditableText") {
          assertEquals("123456", value.toString())
        }
      }
    }
    getClearIconButton().assertIsDisplayed()
  }


  @Test
  fun shouldCheckValidAmountInputThenClearIt() {
    composeTestRule.setContent {
      val amountTextState = rememberSaveable { mutableStateOf("") }
      RequiredAmountInputCard(
        windowSizeUtil = defaultWindowSizeUtility(),
        amountTextState = amountTextState,
        onClearedAmountText = {}
      )
    }
    getAmountInputTitle().assertIsDisplayed()

    val textFieldWithTag = getAmountInputField()
    textFieldWithTag.assertIsDisplayed()
    textFieldWithTag.performTextInput("123456")

    val clearIconButton = getClearIconButton()
    clearIconButton.assertIsDisplayed()
    clearIconButton.performClick()
    for ((key, value) in textFieldWithTag.fetchSemanticsNode().config) {
      if (key.name == "EditableText") {
        assertEquals("", value.toString())
      }
    }
  }

  @Test
  fun shouldCheckNegativeAmountInput() {
    composeTestRule.setContent {
      val amountTextState = rememberSaveable { mutableStateOf("") }
      RequiredAmountInputCard(
        windowSizeUtil = defaultWindowSizeUtility(),
        amountTextState = amountTextState,
        onClearedAmountText = {}
      )
    }
    getAmountInputTitle().assertIsDisplayed()
    val textFieldWithTag = getAmountInputField()
    with(textFieldWithTag) {
      assertIsDisplayed()
      performTextInput("-123")
      for ((key, value) in fetchSemanticsNode().config) {
        if (key.name == "EditableText") {
          assertEquals("", value.toString())
        }
      }
    }
  }

  private fun getAmountInputTitle() = composeTestRule.onNodeWithText("How much do you want to send?")

  private fun getAmountInputField() =
    composeTestRule.onNodeWithContentDescription("Required amount text input field")

  private fun getClearIconButton() =
    composeTestRule.onNodeWithContentDescription("Clear icon for amount text input field")

  @Composable
  private fun defaultWindowSizeUtility() = WindowSizeUtil(
    windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(360.dp, 640.dp)),
    isLandscape = false,
    isTabletWidth = false
  )
}
