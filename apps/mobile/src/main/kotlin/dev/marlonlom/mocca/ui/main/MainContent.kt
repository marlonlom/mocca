/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.mobile.calculator.input.CalculatorInputScreen
import dev.marlonlom.mocca.mobile.onboarding.OnboardingScreen
import dev.marlonlom.mocca.mobile.ui.navigation.MainScaffold
import dev.marlonlom.mocca.mobile.ui.theme.MoccaTheme
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
internal fun MainContent(
  mainUiState: MainUiState,
  onOnboarded: () -> Unit,
  windowSizeInfo: WindowSizeInfo,
  mainActions: MainActions,
) = MoccaTheme(
  darkTheme = mainUiState.shouldUseDarkTheme(),
  dynamicColor = mainUiState.shouldUseDynamicTheming(),
) {
  when (mainUiState) {
    is MainUiState.Success -> {
      when {
        mainUiState.userData.isOnboarding -> {
          Box(
            modifier = Modifier
              .fillMaxSize()
              .background(MaterialTheme.colorScheme.background)
              .safeContentPadding(),
            contentAlignment = Alignment.Center,
          ) {
            OnboardingScreen { onOnboarded() }
          }
        }

        else -> {
          MainScaffold(
            listPaneContent = { mobileWindowSize, foldState ->
              CalculatorInputScreen(
                mobileWindowSize = mobileWindowSize,
                onCalculationReady = {},
                onHistoryButtonClicked = {},
                onRatesButtonClicked = {},
                onSettingsButtonClicked = {},
              )
            },
            detailPaneContent = { mobileWindowSize, foldState ->
              Box(
                modifier = Modifier
                  .fillMaxSize()
                  .background(MaterialTheme.colorScheme.surfaceVariant)
                  .padding(20.dp),
                contentAlignment = Alignment.Center,
              ) {
                Text(
                  text = "$mobileWindowSize\n$foldState",
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
              }
            },
          )
        }
      }
    }

    else -> {}
  }
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
