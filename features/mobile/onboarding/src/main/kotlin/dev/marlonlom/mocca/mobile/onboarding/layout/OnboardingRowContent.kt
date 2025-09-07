/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.onboarding.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.onboarding.component.OnboardingButton
import dev.marlonlom.mocca.mobile.onboarding.component.OnboardingDescription
import dev.marlonlom.mocca.mobile.onboarding.component.OnboardingImage
import dev.marlonlom.mocca.mobile.onboarding.component.OnboardingSpacer
import dev.marlonlom.mocca.mobile.onboarding.component.OnboardingSpacer20
import dev.marlonlom.mocca.mobile.onboarding.component.OnboardingTitle

/**
 * Displays onboarding content in a [Row].
 *
 * @author marlonlom
 *
 * @param onOnboarded The action to perform when onboarding is completed.
 * @param widthFraction The fraction of the available width the content should occupy.
 */
@Composable
internal fun OnboardingRowContent(onOnboarded: () -> Unit, widthFraction: Float = 1.0f) {
  Row(
    modifier = Modifier.fillMaxWidth(widthFraction),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center,
  ) {
    Column(
      Modifier
        .weight(1.0f)
        .padding(if (widthFraction < 1.0f) 0.dp else 20.dp),
    ) {
      OnboardingSpacer()
      OnboardingImage()
      OnboardingSpacer()
    }

    OnboardingSpacer20()

    Column(
      Modifier
        .weight(1.0f)
        .padding(if (widthFraction < 1.0f) 0.dp else 20.dp),
    ) {
      OnboardingSpacer()
      OnboardingTitle(TextAlign.Start)
      OnboardingDescription(TextAlign.Start)
      OnboardingSpacer()
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        OnboardingButton(onButtonClicked = { onOnboarded() })
      }
    }
  }
}
