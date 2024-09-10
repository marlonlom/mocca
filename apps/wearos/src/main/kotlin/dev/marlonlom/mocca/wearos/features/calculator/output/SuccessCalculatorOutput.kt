/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.features.calculator.output

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import dev.marlonlom.mocca.R
import dev.marlonlom.mocca.calculator.model.CalculationResult
import kotlin.math.roundToLong

/**
 * Success calculation output composable ui.
 *
 * @author marlonlom
 *
 * @param calculationResult Calculation result object.
 * @param onBackNavigationAction Action for back navigation.
 */
@Composable
internal fun SuccessCalculatorOutput(
  scrollState: ScrollState,
  calculationResult: CalculationResult,
  onBackNavigationAction: () -> Unit,
) {
  Column(
    modifier = Modifier
      .padding(top = 30.dp, bottom = 10.dp)
      .verticalScroll(scrollState),
    verticalArrangement = Arrangement.spacedBy(5.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Icon(
        painter = painterResource(R.drawable.ic_rounded_check_circle),
        tint = MaterialTheme.colorScheme.tertiary,
        contentDescription = null,
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    SuccessCalculationTexts(
      titleRes = R.string.text_result_label_fee,
      calculationResultValue = calculationResult.fixedFee.plus(calculationResult.variableFee),
    )

    Spacer(modifier = Modifier.height(4.dp))

    SuccessCalculationTexts(
      titleRes = R.string.text_result_label_total,
      calculationResultValue = calculationResult.total,
    )

    Spacer(modifier = Modifier.height(10.dp))

    BackNavigationButton(
      isFailure = false,
      onBackNavigationAction = onBackNavigationAction,
    )
  }
}

/**
 * @author marlonlom
 *
 * @param titleRes
 * @param calculationResultValue
 */
@Composable
private fun SuccessCalculationTexts(titleRes: Int, calculationResultValue: Double) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(bottom = 2.dp),
    text = stringResource(titleRes),
    maxLines = 1,
    color = MaterialTheme.colorScheme.tertiary,
    textAlign = TextAlign.Center,
    fontWeight = FontWeight.Bold,
    style = MaterialTheme.typography.bodyExtraSmall,
  )

  Text(
    modifier = Modifier.fillMaxWidth(),
    text = stringResource(
      R.string.text_cop_currency_value,
      calculationResultValue.roundToLong(),
    ),
    maxLines = 1,
    color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.75f),
    textAlign = TextAlign.Center,
    style = MaterialTheme.typography.bodySmall,
  )
}
