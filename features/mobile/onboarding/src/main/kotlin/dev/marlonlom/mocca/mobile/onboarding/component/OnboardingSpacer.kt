/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.onboarding.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A vertical spacer with a predefined height for use within a [androidx.compose.foundation.layout.Column].
 *
 * @author marlonlom
 */
@Composable
internal fun ColumnScope.OnboardingSpacer() = Spacer(Modifier.weight(1.0f))

/**
 * A horizontal spacer with a predefined width of 20.dp.
 *
 * @author marlonlom
 */
@Composable
internal fun OnboardingSpacer20() = Spacer(Modifier.width(20.dp))
