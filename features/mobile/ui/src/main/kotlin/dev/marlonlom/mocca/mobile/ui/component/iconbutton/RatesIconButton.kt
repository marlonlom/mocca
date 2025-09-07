/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.component.iconbutton

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.TableChart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.marlonlom.mocca.mobile.ui.R

@Composable
fun RatesIconButton(onButtonClicked: () -> Unit) = IconButton(onClick = onButtonClicked) {
  Icon(
    imageVector = Icons.Rounded.TableChart,
    contentDescription = stringResource(R.string.text_rates),
  )
}
