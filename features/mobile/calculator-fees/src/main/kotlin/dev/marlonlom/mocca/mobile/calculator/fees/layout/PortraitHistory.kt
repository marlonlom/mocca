/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.fees.layout

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.fees.component.CalculationFeeListItem
import dev.marlonlom.mocca.mobile.calculator.fees.component.EmptyCalculationFees
import dev.marlonlom.mocca.mobile.calculator.fees.domain.SuccessfulFeesDomainData
import dev.marlonlom.mocca.mobile.ui.component.header.HeaderBar
import dev.marlonlom.mocca.mobile.ui.component.iconbutton.ExitIconButton
import dev.marlonlom.mocca.mobile.ui.window.MobileWindowSize
import dev.marlonlom.mocca.mobile.ui.R as mobileUiR

/**
 * Displays the calculator fees list in portrait mode.
 *
 * @author marlonlom
 *
 * @param feesData Lambda providing the list of calculation fee items.
 * @param mobileWindowSize Current mobile window size to adjust layout.
 * @param showCloseButton Whether to show the close button.
 * @param onCloseButtonClicked Callback invoked when the close button is clicked.
 */
@Composable
internal fun PortraitHistory(
  feesData: () -> List<SuccessfulFeesDomainData>,
  mobileWindowSize: MobileWindowSize,
  showCloseButton: Boolean,
  onCloseButtonClicked: () -> Unit,
) = Column(
  modifier = Modifier
    .background(MaterialTheme.colorScheme.background)
    .padding(horizontal = 20.dp)
    .consumeWindowInsets(WindowInsets.systemBars),
  horizontalAlignment = Alignment.CenterHorizontally,
) {
  HeaderBar(title = {
    Text(
      text = stringResource(mobileUiR.string.text_fees),
      overflow = TextOverflow.Ellipsis,
      maxLines = 1,
    )
  }, isMedium = mobileWindowSize != MobileWindowSize.MOBILE_LANDSCAPE, navigationIcon = {
    if (showCloseButton) {
      ExitIconButton(onButtonClicked = onCloseButtonClicked)
    }
  })
  val data = feesData()
  when {
    data.isEmpty() -> {
      EmptyCalculationFees()
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
        itemsIndexed(
          items = data,
        ) { position, item ->
          CalculationFeeListItem(
            itemPosition = position,
            domainItem = item,
          )
        }

        item {
          Spacer(Modifier.height(16.dp))
        }
      }
    }
  }
}
