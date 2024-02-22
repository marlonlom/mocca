/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.ui.main.scaffold.isCompactHeight
import dev.marlonlom.apps.mocca.ui.main.scaffold.isMediumWidth
import dev.marlonlom.apps.mocca.ui.util.WindowSizeInfo

/**
 * Money amount input text composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeInfo Window size information utility.
 * @param amountTextState Calculation amount ui state string value.
 */
@Composable
fun MoneyAmountInput(
  windowSizeInfo: WindowSizeInfo,
  amountTextState: MutableState<String>,
) {

  val labelTextStyle = when {
    (windowSizeInfo.windowSizeClass.isMediumWidth).and(windowSizeInfo.windowSizeClass.isCompactHeight.not()) -> MaterialTheme.typography.titleLarge
    else -> MaterialTheme.typography.bodyLarge
  }

  val amountTextStyle = when {
    (windowSizeInfo.windowSizeClass.isMediumWidth).and(windowSizeInfo.windowSizeClass.isCompactHeight.not()) -> MaterialTheme.typography.displayLarge
    else -> MaterialTheme.typography.headlineLarge
  }

  val moneyIconSize = when {
    (windowSizeInfo.windowSizeClass.isMediumWidth).and(windowSizeInfo.windowSizeClass.isCompactHeight.not()) -> 64.dp
    else -> 48.dp
  }

  Column(
    Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp)
  ) {
    Text(
      modifier = Modifier.fillMaxWidth(),
      fontWeight = FontWeight.Bold,
      color = MaterialTheme.colorScheme.secondary,
      style = labelTextStyle,
      maxLines = 2,
      textAlign = TextAlign.End,
      text = stringResource(id = R.string.text_home_label_amount)
    )
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .heightIn(min = moneyIconSize),
      horizontalArrangement = Arrangement.spacedBy(20.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        imageVector = Icons.Rounded.AttachMoney,
        contentDescription = null,
        modifier = Modifier.size(moneyIconSize),
        tint = MaterialTheme.colorScheme.secondary
      )
      Text(
        modifier = Modifier.fillMaxWidth(),
        text = amountTextState.value,
        color = MaterialTheme.colorScheme.secondary,
        style = amountTextStyle,
        lineHeight = amountTextStyle.lineHeight,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        textAlign = TextAlign.End
      )
    }
  }
}
