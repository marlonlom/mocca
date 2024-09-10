/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.feats.settings.parts

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

/**
 * Settings label text composable ui.
 *
 * @author marlonlom
 *
 * @param title Title text as string resource.
 * @param modifier Modifier to be applied.
 * @param subtitle Optional subtitle text, defaults to empty text.
 * @param showSubtitle True/False if subtitle is displayed, defaults to true if [subtitle] is not empty.
 */
@Composable
fun SettingLabelText(
  @StringRes title: Int,
  modifier: Modifier = Modifier,
  subtitle: String = "",
  showSubtitle: Boolean = subtitle.isNotEmpty(),
) = when {
  showSubtitle -> {
    Column(modifier = modifier) {
      Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.bodyMedium,
      )
      Text(
        text = subtitle,
        style = MaterialTheme.typography.labelSmall,
      )
    }
  }

  else -> {
    Text(
      modifier = modifier,
      text = stringResource(id = title),
      style = MaterialTheme.typography.bodyMedium,
    )
  }
}
