/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.history.component

import androidx.compose.runtime.Composable
import dev.marlonlom.mocca.mobile.calculator.history.R
import dev.marlonlom.mocca.mobile.ui.component.empty.EmptyStateContent

/**
 * Empty state shown when there are no calculation history records.
 *
 * @author marlonlom
 *
 */
@Composable
internal fun EmptyCalculationHistory() = EmptyStateContent(
  emptyTitleText = R.string.text_empty_history_title,
  emptyDetailsText = R.string.text_empty_history_detail,
)
