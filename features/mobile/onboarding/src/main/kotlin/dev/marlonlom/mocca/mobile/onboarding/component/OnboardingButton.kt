/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.onboarding.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.onboarding.R

/**
 * A button for the onboarding screen.
 *
 * @author marlonlom
 *
 * @param onButtonClicked The action to perform when the button is clicked.
 * @param isWide If true, the button will occupy more horizontal space.
 */
@Composable
internal fun OnboardingButton(onButtonClicked: () -> Unit, isWide: Boolean = false) = Button(
  modifier = (if (isWide) Modifier.fillMaxWidth() else Modifier)
    .padding(vertical = 20.dp)
    .testTag("onboarding_btn"),
  onClick = { onButtonClicked() },
  colors = ButtonDefaults.buttonColors(
    containerColor = MaterialTheme.colorScheme.primaryContainer,
    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
  ),
) {
  Text(
    text = stringResource(R.string.text_onboarding_button),
    style = MaterialTheme.typography.bodyLarge,
  )
}
