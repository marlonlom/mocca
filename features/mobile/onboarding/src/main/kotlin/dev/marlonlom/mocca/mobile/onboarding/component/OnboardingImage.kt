/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import dev.marlonlom.mocca.mobile.onboarding.R

/**
 * An image for the onboarding screen.
 *
 * @author marlonlom
 */
@Composable
internal fun OnboardingImage() = Image(
  painter = painterResource(R.drawable.img_budget),
  contentDescription = null,
  modifier = Modifier
    .testTag("onboarding_img")
    .fillMaxWidth(),
)
