/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import dev.marlonlom.apps.mocca.ui.main.scaffold.AppScaffold
import dev.marlonlom.apps.mocca.ui.theme.MoccaTheme
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil

/**
 * Main content composable ui.
 *
 * @author marlonlom
 *
 * @param mainActivityUiState Main activity ui state.
 * @param windowSizeUtil Window size utility.
 */
@ExperimentalMaterial3Api
@Composable
internal fun MainContent(
  mainActivityUiState: MainActivityUiState,
  windowSizeUtil: WindowSizeUtil
) {
  MoccaTheme(
    darkTheme = shouldUseDarkTheme(mainActivityUiState),
    dynamicColor = shouldUseDynamicTheming(mainActivityUiState)
  ) {
    AppScaffold(
      windowSizeUtil = windowSizeUtil
    )
  }
}


/**
 * Returns `true` if the dynamic color is used, as a function of the [uiState].
 *
 * @param uiState Main activity ui state.
 * @return true/false
 */
@Composable
private fun shouldUseDynamicTheming(
  uiState: MainActivityUiState,
): Boolean = when (uiState) {
  MainActivityUiState.Loading -> false
  is MainActivityUiState.Success -> uiState.userData.dynamicColors
}

/**
 * Returns `true` if the dark theme is used, as a function of the [uiState].
 *
 * @param uiState Main activity ui state.
 * @return true/false.
 */
@Composable
private fun shouldUseDarkTheme(
  uiState: MainActivityUiState,
): Boolean = if (isSystemInDarkTheme()) true else when (uiState) {
  MainActivityUiState.Loading -> isSystemInDarkTheme()
  is MainActivityUiState.Success -> uiState.userData.darkTheme
}
