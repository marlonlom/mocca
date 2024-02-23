/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.settings.parts

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ClickableTextRow(
  @StringRes title: Int,
  subtitle: String = "",
  icon: @Composable () -> Unit = {},
  onClicked: () -> Unit = {}
) {
  val annotatedString = buildAnnotatedTitleWithSubtitle(
    title = title,
    showSubtitle = subtitle.isNotEmpty(),
    subtitle = subtitle
  )

  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onClicked() }
      .padding(10.dp),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    icon()
    Text(
      modifier = Modifier.fillMaxWidth(),
      text = annotatedString,
    )
  }
}
