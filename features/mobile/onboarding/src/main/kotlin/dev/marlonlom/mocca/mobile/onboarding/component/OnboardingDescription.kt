/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.onboarding.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dev.marlonlom.mocca.mobile.onboarding.R

/**
 * A description text for the onboarding screen.
 *
 * @author marlonlom
 *
 * @param textAlign The alignment of the text within its layout.
 */
@Composable
internal fun OnboardingDescription(textAlign: TextAlign = TextAlign.Center) = Text(
  modifier = Modifier.testTag("onboarding_desc"),
  text = stringResource(R.string.text_onboarding_detail),
  textAlign = textAlign,
  color = MaterialTheme.colorScheme.onBackground,
  style = MaterialTheme.typography.bodyLarge,
)
