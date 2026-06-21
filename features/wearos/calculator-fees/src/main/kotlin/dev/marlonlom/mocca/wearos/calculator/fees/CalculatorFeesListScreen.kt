/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.fees

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.itemsIndexed
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import dev.marlonlom.mocca.wearos.calculator.fees.component.CalculationFeeListItem
import dev.marlonlom.mocca.wearos.calculator.fees.domain.CalculatingFeesDomainData
import dev.marlonlom.mocca.wearos.calculator.fees.domain.CalculatorFeesProvider

/**
 * Displays a list-based screen showing calculation fees.
 *
 * @author marlonlom
 *
 * @param listState Lazy column state.
 * @param onBackNavigationAction Callback invoked when the user requests to navigate
 * back to the previous screen.
 */
@Composable
fun CalculatorFeesListScreen(listState: ScalingLazyListState, onBackNavigationAction: () -> Unit) {
  BackHandler {
    onBackNavigationAction()
  }
  val feesListingsState: List<CalculatingFeesDomainData> = remember {
    CalculatorFeesProvider.provideFees()
  }
  ScalingLazyColumn(
    state = listState,
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 20.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
  ) {
    item {
      Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium,
        text = stringResource(R.string.text_fees),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
      )
      Spacer(Modifier.height(10.dp))
    }
    itemsIndexed(items = feesListingsState) { position, item ->
      CalculationFeeListItem(
        position = position,
        domainItem = item,
      )
    }
  }
}
