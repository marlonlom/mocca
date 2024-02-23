/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.settings.slots

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DefaultSettingsSlot(
  @StringRes title: Int,
  content: @Composable () -> Unit
) {
  Column(
    modifier = Modifier.padding(bottom = 10.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .paddingFromBaseline(top = 40.dp, bottom = 20.dp),
      text = stringResource(title),
      style = MaterialTheme.typography.titleSmall,
      fontWeight = FontWeight.Bold,
    )
    content()
  }
}
