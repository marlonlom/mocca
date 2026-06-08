/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.fees.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.fees.R
import dev.marlonlom.mocca.mobile.calculator.fees.domain.SuccessfulFeesDomainData

/**
 * A single row representing a calculation fee entry.
 *
 * @author marlonlom
 *
 * @param itemPosition The position of the calculation fee item in the list.
 * @param domainItem The data model containing calculation fee details.
 * @param modifier Standard Compose modifier for layout adjustments.
 */
@Composable
internal fun CalculationFeeListItem(
  itemPosition: Int,
  domainItem: SuccessfulFeesDomainData,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp),
  ) {
    DateWithLine("#${itemPosition.plus(1)}", Modifier)
    println(domainItem)
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp),
      fontStyle = FontStyle.Italic,
      style = MaterialTheme.typography.labelMedium,
      textAlign = TextAlign.End,
      maxLines = 1,
      color = MaterialTheme.colorScheme.onBackground,
      text = stringResource(
        R.string.text_price_range,
        domainItem.min,
        domainItem.max,
      ),
    )
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp),
      style = MaterialTheme.typography.displaySmall,
      color = MaterialTheme.colorScheme.onBackground,
      fontWeight = FontWeight.Bold,
      textAlign = TextAlign.End,
      maxLines = 1,
      text = if (domainItem.haveVariableFee) {
        stringResource(
          R.string.text_fee_percentage,
          domainItem.variableFeeFactor,
        )
      } else {
        stringResource(
          R.string.text_fee_amount,
          domainItem.fixedFee,
        )
      },
    )
  }
}

/**
 * Composable that shows a date/time label with a trailing horizontal line
 * expanding to fill the remaining width.
 *
 * @param text The date/time text to display.
 * @param modifier Optional [Modifier] for styling and layout.
 */
@Composable
private fun DateWithLine(text: String, modifier: Modifier = Modifier) = Row(
  modifier = modifier.fillMaxWidth(),
  verticalAlignment = Alignment.CenterVertically,
) {
  Text(
    modifier = Modifier.padding(end = 8.dp),
    maxLines = 1,
    style = MaterialTheme.typography.labelSmall,
    color = MaterialTheme.colorScheme.onBackground,
    text = text,
  )

  HorizontalDivider(
    modifier = Modifier.weight(1f),
    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.25f),
    thickness = 1.dp,
  )
}
