/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.settings.parts

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

/**
 * Boolean setting switch composable ui.
 *
 * @author marlonlom
 *
 * @param title Title as string resource
 * @param subtitle Subtitle as string resource
 * @param checked Switch value state.
 * @param onChecked Action for switch checked.
 * @param enabled True/False if switch is enabled, defaults to true.
 * @param showSubtitle True/False if subtitle is displayed, defaults to true.
 */
@Composable
internal fun BooleanSettingSwitch(
  @StringRes title: Int,
  @StringRes subtitle: Int,
  checked: Boolean,
  onChecked: (Boolean) -> Unit,
  enabled: Boolean = true,
  showSubtitle: Boolean = true
) {
  val annotatedString = buildAnnotatedTitleWithSubtitle(
    title = title,
    showSubtitle = showSubtitle,
    subtitle = stringResource(subtitle)
  )

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(10.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = annotatedString,
    )
    Switch(
      enabled = enabled,
      checked = checked,
      onCheckedChange = { onChecked(it) }
    )
  }
}

@Composable
internal fun buildAnnotatedTitleWithSubtitle(
  title: Int,
  showSubtitle: Boolean,
  subtitle: String
): AnnotatedString {
  val annotatedString = buildAnnotatedString {
    withStyle(
      style = SpanStyle(
        fontSize = MaterialTheme.typography.bodyMedium.fontSize
      )
    ) {
      append(stringResource(title))
    }
    if (showSubtitle) {
      append("\n")
      withStyle(
        style = SpanStyle(
          fontSize = MaterialTheme.typography.labelMedium.fontSize
        )
      ) {
        append(subtitle)
      }
    }
  }
  return annotatedString
}
