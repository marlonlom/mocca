/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.wearos.presentation.feats.calculator_output

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.CardDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TitleCard
import dev.marlonlom.mocca.calculator.model.CalculationResult
import dev.marlonlom.mocca.R
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
  calculationResult: CalculationResult,
  onBackNavigationAction: () -> Unit
) {
  Column(
    modifier = Modifier
      .padding(top = 40.dp, bottom = 10.dp)
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.spacedBy(5.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        imageVector = Icons.Rounded.CheckCircleOutline,
        tint = MaterialTheme.colors.secondary,
        contentDescription = null,
      )
    }

    TitleCard(
      modifier = Modifier.padding(all = 0.dp),
      onClick = {},
      title = {},
      backgroundPainter = CardDefaults.cardBackgroundPainter(
        startBackgroundColor = MaterialTheme.colors.surface,
        endBackgroundColor = MaterialTheme.colors.surface,
      )
    ) {

      SuccessCalculationTexts(
        titleRes = R.string.text_result_label_fee,
        calculationResultValue = calculationResult.fixedFee.plus(calculationResult.variableFee)
      )

      Spacer(modifier = Modifier.height(10.dp))

      SuccessCalculationTexts(
        titleRes = R.string.text_result_label_total,
        calculationResultValue = calculationResult.total,
      )
    }

    BackNavigationButton(
      isFailure = false,
      onBackNavigationAction = onBackNavigationAction
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
private fun SuccessCalculationTexts(
  titleRes: Int,
  calculationResultValue: Double,
) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(bottom = 2.dp),
    text = stringResource(titleRes),
    maxLines = 1,
    color = MaterialTheme.colors.secondary,
    textAlign = TextAlign.Center,
    fontWeight = FontWeight.Bold,
    style = MaterialTheme.typography.caption3
  )

  Text(
    modifier = Modifier.fillMaxWidth(),
    text = stringResource(
      R.string.text_cop_currency_value,
      calculationResultValue.roundToLong()
    ),
    maxLines = 1,
    color = MaterialTheme.colors.secondary.copy(alpha = 0.75f),
    textAlign = TextAlign.Center,
    style = MaterialTheme.typography.body2
  )
}
