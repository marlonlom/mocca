/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.history.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxDefaults
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.history.R
import dev.marlonlom.mocca.mobile.calculator.history.domain.SuccessfulCalculationDomainData

/**
 * A single row representing a successful calculation entry.
 *
 * @author marlonlom
 *
 * @param domainItem The data model containing the expression, result, and timestamp.
 * @param onDeleteItem Callback passing the unique [domainItem] ID for removal.
 * @param modifier Standard Compose modifier for layout adjustments.
 */
@Composable
internal fun CalculationHistoryItem(
  domainItem: SuccessfulCalculationDomainData,
  onDeleteItem: (Long) -> Unit,
  modifier: Modifier = Modifier,
) {
  val dismissState = rememberSwipeToDismissBoxState(
    initialValue = SwipeToDismissBoxValue.Settled,
    positionalThreshold = SwipeToDismissBoxDefaults.positionalThreshold,
  )

  SwipeToDismissBox(
    state = dismissState,
    modifier = modifier.fillMaxSize(),
    enableDismissFromStartToEnd = false,
    enableDismissFromEndToStart = true,
    backgroundContent = {
      when (dismissState.dismissDirection) {
        SwipeToDismissBoxValue.EndToStart -> {
          Icon(
            imageVector = Icons.Rounded.Delete,
            contentDescription = stringResource(R.string.text_delete_history),
            modifier = Modifier
              .fillMaxSize()
              .background(MaterialTheme.colorScheme.surface)
              .wrapContentSize(Alignment.CenterEnd)
              .padding(12.dp),
            tint = MaterialTheme.colorScheme.onSurface,
          )
        }

        else -> {}
      }
    },
    onDismiss = { dismissBoxValue ->
      when (dismissBoxValue) {
        SwipeToDismissBoxValue.EndToStart -> {
          onDeleteItem(domainItem.calculationId)
        }

        else -> {}
      }
    },
    content = {
      Column(
        modifier = modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp),
      ) {
        HorizontalDivider(
          modifier = modifier.padding(bottom = 4.dp),
          thickness = 1.dp,
          color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.25f),
        )
        Text(
          modifier = Modifier.fillMaxWidth(),
          maxLines = 1,
          style = MaterialTheme.typography.labelSmall,
          text = domainItem.formattedCreationDate(),
        )
        Text(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
          fontStyle = FontStyle.Italic,
          style = MaterialTheme.typography.labelMedium,
          textAlign = TextAlign.End,
          maxLines = 1,
          text = stringResource(
            R.string.text_amount_with_fee,
            domainItem.amount,
            domainItem.feeToUse,
          ),
        )
        Text(
          modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
          style = MaterialTheme.typography.displaySmall,
          fontWeight = FontWeight.Bold,
          textAlign = TextAlign.End,
          maxLines = 1,
          text = stringResource(
            R.string.text_amount_total,
            domainItem.total,
          ),
        )
      }
    },
  )
}
