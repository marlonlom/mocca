/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.onboarding.component

import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.onboarding.R

/**
 * A title text for the onboarding screen.
 *
 * @author marlonlom
 *
 * @param textAlign The alignment of the text within its layout.
 */
@Composable
internal fun OnboardingTitle(textAlign: TextAlign = TextAlign.Center) = Text(
  modifier = Modifier
    .paddingFromBaseline(top = 40.dp, bottom = 20.dp)
    .testTag("onboarding_title"),
  text = stringResource(R.string.text_onboarding_welcome),
  color = MaterialTheme.colorScheme.onBackground,
  fontWeight = FontWeight.Bold,
  textAlign = textAlign,
  style = MaterialTheme.typography.titleLarge,
)
