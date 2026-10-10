/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.fees.component

import androidx.compose.runtime.Composable
import dev.marlonlom.mocca.mobile.calculator.fees.R
import dev.marlonlom.mocca.mobile.ui.component.empty.EmptyStateContent

/**
 * Empty state shown when there are no calculation fees records.
 *
 * @author marlonlom
 *
 */
@Composable
internal fun EmptyCalculationFees() = EmptyStateContent(
  emptyTitleText = R.string.text_empty_fees_title,
  emptyDetailsText = R.string.text_empty_fees_detail,
)
