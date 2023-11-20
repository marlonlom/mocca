/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToInt

/**
 * Calculator results card composable ui.
 *
 * @param windowSizeUtil Window size utility.
 * @param calculationUiState Calculation ui state.
 */
@Composable
fun CalculatorResultsCard(
  windowSizeUtil: WindowSizeUtil,
  calculationUiState: CalculatorUiState
) {
  val feeTextState = if (calculationUiState is CalculatorUiState.WithSuccess) {
    val resultFeeSum = calculationUiState.response.fixedFee + calculationUiState.response.variableFee
    "${resultFeeSum.roundToInt()}"
  } else ""

  val totalCostTextState = if (calculationUiState is CalculatorUiState.WithSuccess) {
    "${calculationUiState.response.total.roundToInt()}"
  } else ""

  Card(
    modifier = Modifier.fillMaxWidth(),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.tertiaryContainer,
      contentColor = MaterialTheme.colorScheme.onTertiaryContainer
    ),
    border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary)
  ) {
    val rowPadding =
      if (windowSizeUtil.isTabletLandscape) {
        PaddingValues(bottom = 20.dp)
      } else {
        PaddingValues(bottom = 10.dp)
      }
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(rowPadding),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
      ) {
        OutputTitleText(windowSizeUtil, R.string.text_home_label_fee)
        OutputMoneyTextField(windowSizeUtil, feeTextState)
      }
      Column(
        verticalArrangement = Arrangement.Bottom
      ) {
        OutputTitleText(windowSizeUtil, R.string.text_home_label_total)
        OutputMoneyTextField(windowSizeUtil, totalCostTextState)
      }
    }
  }
}

/**
 * Output money text field composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeUtil Window size utility.
 * @param moneyTextState Money text ui state.
 */
@Composable
internal fun OutputMoneyTextField(
  windowSizeUtil: WindowSizeUtil,
  moneyTextState: String
) {

  val textFieldStyle = when {
    windowSizeUtil.isTabletLandscape -> MaterialTheme.typography.titleMedium
    else -> MaterialTheme.typography.titleLarge
  }

  val textFieldHorizontalPadding = when {
    windowSizeUtil.isTabletLandscape -> PaddingValues(horizontal = 10.dp)
    else -> PaddingValues(horizontal = 20.dp)
  }

  val textFieldBottomPadding = when {
    windowSizeUtil.isTabletLandscape -> PaddingValues(bottom = 0.dp)
    else -> PaddingValues(bottom = 10.dp)
  }

  val moneyTextOrEmpty = when {
    moneyTextState.isEmpty() -> ""
    else -> moneyTextState.toDouble().let {
      NumberFormat.getCurrencyInstance(
        Locale("es", "co")
      ).apply {
        maximumFractionDigits = 0
      }.format(it)
    }
  }
  Text(
    text = moneyTextOrEmpty,
    modifier = Modifier
      .fillMaxWidth()
      .padding(textFieldHorizontalPadding)
      .padding(textFieldBottomPadding),
    style = textFieldStyle,
    fontWeight = FontWeight.Bold,
  )
}

/**
 * Output money text field composable ui.
 *
 * @param windowSizeUtil Window size utility.
 * @param titleRes Title string resource.
 */
@Composable
internal fun OutputTitleText(
  windowSizeUtil: WindowSizeUtil,
  @StringRes titleRes: Int
) {
  val labelHorizontalPadding = when {
    windowSizeUtil.isTabletLandscape -> PaddingValues(horizontal = 10.dp)
    else -> PaddingValues(horizontal = 20.dp)
  }

  val labelVerticalPadding = when {
    windowSizeUtil.isTabletLandscape -> PaddingValues(bottom = 0.dp, top = 10.dp)
    else -> PaddingValues(bottom = 0.dp, top = 20.dp)
  }

  val labelTextStyle = when {
    windowSizeUtil.isTabletLandscape -> MaterialTheme.typography.bodySmall
    else -> MaterialTheme.typography.bodyMedium
  }

  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(labelHorizontalPadding)
      .padding(labelVerticalPadding),
    text = stringResource(titleRes),
    style = labelTextStyle,
    fontWeight = FontWeight.Normal
  )
}
