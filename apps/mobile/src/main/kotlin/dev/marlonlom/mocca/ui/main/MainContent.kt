/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import dev.marlonlom.mocca.mobile.ui.theme.MoccaTheme
import dev.marlonlom.mocca.ui.main.scaffold.AppScaffold
import dev.marlonlom.mocca.ui.util.WindowSizeInfo

/**
 * Main content composable ui.
 *
 * @author marlonlom
 *
 * @param mainUiState Main activity ui state.
 * @param windowSizeInfo Window size information utility.
 */
@ExperimentalMaterial3Api
@Composable
internal fun MainContent(mainUiState: MainUiState, windowSizeInfo: WindowSizeInfo, mainActions: MainActions) =
  MoccaTheme(
    darkTheme = mainUiState.shouldUseDarkTheme(),
    dynamicColor = mainUiState.shouldUseDynamicTheming(),
  ) {
    AppScaffold(
      windowSizeInfo = windowSizeInfo,
      mainActions = mainActions,
    )
  }

/**
 * Main actions data class.
 *
 * @author marlonlom
 *
 * @property onOssLicencesSettingLinkClicked Action for oss licences setting clicked.
 * @property onOpeningExternalUrlSettingClicked Action for opening external url.
 * @property onFeedbackSettingLinkClicked Action for feedback setting clicked.
 */
data class MainActions(
  val onOssLicencesSettingLinkClicked: () -> Unit,
  val onOpeningExternalUrlSettingClicked: (String) -> Unit,
  val onFeedbackSettingLinkClicked: () -> Unit,
)
