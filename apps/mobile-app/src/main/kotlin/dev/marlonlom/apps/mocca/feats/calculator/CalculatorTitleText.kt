/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.calculator

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil

/**
 * Calculator title text composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeUtil Window size utility.
 */
@Composable
fun CalculatorTitleText(
  windowSizeUtil: WindowSizeUtil
) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .paddingFromBaseline(top = 40.dp, bottom = 20.dp),
    text = buildAnnotatedString {
      val firstTextStyle = when {
        windowSizeUtil.isTabletLandscape -> MaterialTheme.typography.titleMedium
        else -> MaterialTheme.typography.headlineSmall
      }
      withStyle(
        style = SpanStyle(
          fontWeight = FontWeight.Normal,
          fontSize = firstTextStyle.fontSize,
          fontStyle = firstTextStyle.fontStyle
        )
      ) {
        append(stringResource(R.string.text_home_headline_01))
      }

      val secondTextStyle = when {
        windowSizeUtil.isTabletLandscape -> MaterialTheme.typography.headlineLarge
        else -> MaterialTheme.typography.displayMedium
      }
      withStyle(
        style = SpanStyle(
          fontWeight = FontWeight.Bold,
          fontSize = secondTextStyle.fontSize,
          fontStyle = secondTextStyle.fontStyle
        )
      ) {
        append(stringResource(R.string.text_home_headline_02))
      }
    }
  )
}
