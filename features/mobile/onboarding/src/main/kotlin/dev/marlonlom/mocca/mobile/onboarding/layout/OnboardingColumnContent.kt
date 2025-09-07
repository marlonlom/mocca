/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.onboarding.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.marlonlom.mocca.mobile.onboarding.component.OnboardingButton
import dev.marlonlom.mocca.mobile.onboarding.component.OnboardingDescription
import dev.marlonlom.mocca.mobile.onboarding.component.OnboardingImage
import dev.marlonlom.mocca.mobile.onboarding.component.OnboardingSpacer
import dev.marlonlom.mocca.mobile.onboarding.component.OnboardingTitle

/**
 * Displays onboarding content in a [Column].
 *
 * @author marlonlom
 *
 * @param onOnboarded The action to perform when onboarding is completed.
 * @param widthFraction The fraction of the available width the content should occupy.
 * @param heightFraction The fraction of the available height the content should occupy.
 */
@Composable
internal fun OnboardingColumnContent(
  onOnboarded: () -> Unit,
  widthFraction: Float = 1.0f,
  heightFraction: Float = 1.0f,
) = Column(
  modifier = Modifier
    .fillMaxWidth(widthFraction)
    .fillMaxHeight(heightFraction),
  verticalArrangement = Arrangement.Center,
  horizontalAlignment = Alignment.CenterHorizontally,
) {
  OnboardingSpacer()
  OnboardingImage()
  OnboardingSpacer()
  OnboardingTitle()
  OnboardingDescription()
  OnboardingSpacer()
  OnboardingButton(
    onButtonClicked = { onOnboarded() },
    isWide = true,
  )
}
