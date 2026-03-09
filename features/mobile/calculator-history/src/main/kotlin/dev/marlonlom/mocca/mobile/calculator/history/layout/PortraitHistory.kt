/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.history.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.history.component.CalculationHistoryItem
import dev.marlonlom.mocca.mobile.calculator.history.component.EmptyCalculationHistory
import dev.marlonlom.mocca.mobile.calculator.history.component.iconbutton.ClearHistoryIconButton
import dev.marlonlom.mocca.mobile.calculator.history.domain.SuccessfulCalculationDomainData
import dev.marlonlom.mocca.mobile.ui.component.header.HeaderBar
import dev.marlonlom.mocca.mobile.ui.component.iconbutton.ExitIconButton
import dev.marlonlom.mocca.mobile.ui.window.MobileWindowSize
import dev.marlonlom.mocca.mobile.ui.R as mobileUiR

/**
 * Displays the calculator history in portrait mode.
 *
 * @author marlonlom
 *
 * @param historyData Lambda providing the list of historical calculation items.
 * @param mobileWindowSize Current mobile window size to adjust layout.
 * @param showCloseButton Whether to show the close button.
 * @param onCloseButtonClicked Callback invoked when the close button is clicked.
 * @param onClearHistoryButtonClicked Callback invoked when the clear history button is clicked.
 * @param onDeleteButtonClicked Callback invoked when the delete button is clicked.
 */
@Composable
internal fun PortraitHistory(
  historyData: () -> List<SuccessfulCalculationDomainData>,
  mobileWindowSize: MobileWindowSize,
  showCloseButton: Boolean,
  onCloseButtonClicked: () -> Unit,
  onClearHistoryButtonClicked: () -> Unit,
  onDeleteButtonClicked: (Long) -> Unit,
) = Column(
  modifier = Modifier
    .background(MaterialTheme.colorScheme.background)
    .padding(horizontal = 20.dp)
    .consumeWindowInsets(WindowInsets.systemBars),
  horizontalAlignment = Alignment.CenterHorizontally,
) {
  HeaderBar(title = {
    Text(
      text = stringResource(mobileUiR.string.text_history),
      overflow = TextOverflow.Ellipsis,
      maxLines = 1,
    )
  }, isMedium = mobileWindowSize != MobileWindowSize.MOBILE_LANDSCAPE, navigationIcon = {
    if (showCloseButton) {
      ExitIconButton(onButtonClicked = onCloseButtonClicked)
    }
  }, navigationActions = {
    ClearHistoryIconButton(onButtonClicked = onClearHistoryButtonClicked)
  })
  val data = historyData()
  when {
    data.isEmpty() -> {
      EmptyCalculationHistory()
    }

    else -> {
      LazyColumn(
        modifier = Modifier.then(
          when (mobileWindowSize) {
            MobileWindowSize.MOBILE_LANDSCAPE -> Modifier.widthIn(max = 640.dp)
            else -> Modifier.fillMaxWidth()
          },
        ),
      ) {
        items(
          items = data,
          key = { it.calculationId },
        ) { item ->
          CalculationHistoryItem(
            domainItem = item,
            onDeleteItem = onDeleteButtonClicked,
            modifier = Modifier.animateItem(),
          )
        }

        item {
          Spacer(Modifier.height(16.dp))
        }
      }
    }
  }
}
