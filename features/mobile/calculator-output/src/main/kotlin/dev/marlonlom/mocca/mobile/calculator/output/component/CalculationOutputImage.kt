/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.output.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.output.R
import dev.marlonlom.mocca.mobile.calculator.output.domain.CalculatorOutputState

/**
 * Displays an image or icon based on the state of the calculation.
 *
 * @author marlonlom
 *
 * @param uiState The state that determines which image to show (e.g., success, failure, empty).
 */
@Composable
internal fun CalculationOutputImage(uiState: CalculatorOutputState) = Image(
  painter = painterResource(
    when {
      uiState.isSuccess() -> R.drawable.img_success
      uiState.isFailure() -> R.drawable.img_failure
      else -> R.drawable.img_calculate
    },
  ),
  contentDescription = null,
  modifier = Modifier
    .testTag(
      when {
        uiState.isSuccess() -> "calculation_success_img"
        uiState.isFailure() -> "calculation_failed_img"
        else -> "calculation_pending_img"
      },
    )
    .fillMaxWidth()
    .heightIn(min = 128.dp, max = 256.dp),
)
