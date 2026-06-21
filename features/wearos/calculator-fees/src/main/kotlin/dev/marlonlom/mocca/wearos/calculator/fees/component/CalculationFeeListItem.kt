/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.fees.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import dev.marlonlom.mocca.wearos.calculator.fees.R
import dev.marlonlom.mocca.wearos.calculator.fees.domain.CalculatingFeesDomainData
import dev.marlonlom.mocca.wearos.calculator.fees.domain.ColombianPesoFormatter

/**
 * A list item component that displays the fee details for a single [CalculatingFeesDomainData] record.
 *
 * @author marlonlom
 *
 * @param domainItem The domain data model containing the fee information to be rendered.
 */
@Composable
internal fun CalculationFeeListItem(
  position: Int,
  domainItem: CalculatingFeesDomainData,
) = Box(
  modifier = Modifier
    .testTag("calculation_fee_list_item_$position")
    .fillMaxWidth()
    .height(48.dp)
    .background(
      color = MaterialTheme.colorScheme.surfaceContainerLow,
      shape = RoundedCornerShape(percent = 50),
    )
    .padding(horizontal = 20.dp),
  contentAlignment = Alignment.CenterStart,
) {
  Column(
    verticalArrangement = Arrangement.Center,
  ) {
    Text(
      text = if (domainItem.haveVariableFee) {
        stringResource(
          R.string.text_fee_percentage,
          domainItem.variableFeeFactor,
        )
      } else {
        ColombianPesoFormatter.formatCOP(domainItem.fixedFee.toLong())
      },
      maxLines = 1,
      fontWeight = FontWeight.Bold,
      style = MaterialTheme.typography.labelMedium,
      color = MaterialTheme.colorScheme.onSecondaryContainer,
    )
    Spacer(modifier = Modifier.height(2.dp))
    Text(
      text = stringResource(
        R.string.text_price_range,
        ColombianPesoFormatter.format(domainItem.min.toLong()),
        ColombianPesoFormatter.format(domainItem.max.toLong()),
      ),
      maxLines = 1,
      style = MaterialTheme.typography.bodyExtraSmall,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
  }
}
