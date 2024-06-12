/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.feats.calculator.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.feats.calculator.slots.ButtonsContentSlot
import dev.marlonlom.mocca.feats.calculator.slots.TopContentSlot
import dev.marlonlom.mocca.ui.main.scaffold.ScaffoldInnerContentType

/**
 * Compact landscape calculator page composable ui.
 *
 * @author marlonlom
 *
 * @param pageData Content slot data.
 */
@Composable
fun CompactLandscapeCalculatorPage(
  pageData: PageContentData
) {
  val contentModifier = when {
    (pageData.windowSizeInfo.scaffoldInnerContentType is ScaffoldInnerContentType.TwoPane)
      .and(pageData.windowSizeInfo.isLandscape) -> Modifier
      .fillMaxSize()
      .padding(top = 20.dp)

    else -> Modifier.fillMaxSize()
  }

  Row(
    modifier = contentModifier
  ) {
    TopContentSlot(
      windowSizeInfo = pageData.windowSizeInfo,
      calculationTextState = pageData.calculationTextState,
      calculatorUiState = pageData.calculatorUiState,
      onSlotClosedAction = pageData.actions.onSlotClosedAction,
      isLandscapeSinglePane = true
    )

    Column(
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      ButtonsContentSlot(
        windowSizeInfo = pageData.windowSizeInfo,
        numberTypingEnabledState = pageData.numberTypingEnabledState,
        onPerformCalculationAction = pageData.actions.onPerformCalculationAction,
        onDeleteLastNumberAction = pageData.actions.onDeleteLastNumberAction,
        onAppendNumberAction = pageData.actions.onAppendNumberAction,
      )
    }
  }
}
