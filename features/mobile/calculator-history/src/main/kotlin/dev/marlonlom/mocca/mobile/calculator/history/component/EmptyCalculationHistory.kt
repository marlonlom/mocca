/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.history.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.history.R

/**
 * Empty state shown when there are no calculation history records.
 *
 * @author marlonlom
 *
 */
@Composable
internal fun EmptyCalculationHistory() = Column(
  modifier = Modifier
    .fillMaxHeight()
    .background(MaterialTheme.colorScheme.surface),
  horizontalAlignment = Alignment.CenterHorizontally,
) {
  HorizontalDivider(
    modifier = Modifier
      .fillMaxWidth()
      .padding(bottom = 4.dp),
    thickness = 1.dp,
    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.25f),
  )
  Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column {
      Text(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 4.dp),
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        maxLines = 1,
        text = stringResource(R.string.text_empty_history_title),
      )
      Text(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 10.dp),
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Center,
        text = stringResource(R.string.text_empty_history_detail),
      )
    }
  }
}
