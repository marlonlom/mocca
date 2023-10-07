/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil
import kotlin.math.roundToInt

/**
 * Calculator output card composable ui.
 *
 * @param windowSizeUtil Window size utility.
 * @param calculationUiState Calculation ui state.
 */
@Composable
fun CalculatorOutputCard(
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
    OutputTitleText(windowSizeUtil, R.string.text_home_label_fee)
    OutputMoneyTextField(windowSizeUtil, feeTextState)
    OutputTitleText(windowSizeUtil, R.string.text_home_label_total)
    OutputMoneyTextField(windowSizeUtil, totalCostTextState)
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
    windowSizeUtil.isTabletLandscape -> MaterialTheme.typography.bodyLarge
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

  TextField(
    modifier = Modifier
      .fillMaxWidth()
      .padding(textFieldHorizontalPadding)
      .padding(textFieldBottomPadding),
    value = moneyTextState,
    onValueChange = { },
    colors = TextFieldDefaults.colors(
      disabledTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
      unfocusedContainerColor = Color.Transparent,
      focusedContainerColor = Color.Transparent,
      disabledContainerColor = Color.Transparent,
      disabledIndicatorColor = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent,
      focusedIndicatorColor = Color.Transparent
    ),
    textStyle = textFieldStyle,
    singleLine = true,
    leadingIcon = {
      Icon(
        Icons.Rounded.AttachMoney,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onTertiaryContainer
      )
    },
    enabled = false
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
