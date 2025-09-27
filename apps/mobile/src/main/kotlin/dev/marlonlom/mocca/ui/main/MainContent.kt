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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.feats.settings.SettingsRoute
import dev.marlonlom.mocca.mobile.calculator.input.CalculatorInputScreen
import dev.marlonlom.mocca.mobile.calculator.output.CalculatorOutputScreen
import dev.marlonlom.mocca.mobile.onboarding.OnboardingScreen
import dev.marlonlom.mocca.mobile.ui.navigation.AppDestination
import dev.marlonlom.mocca.mobile.ui.navigation.MainScaffold
import dev.marlonlom.mocca.mobile.ui.navigation.MainScaffoldAction
import dev.marlonlom.mocca.mobile.ui.theme.MoccaTheme
import dev.marlonlom.mocca.ui.util.WindowSizeInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
            content = {
              OnboardingScreen { onOnboarded() }
            },
          )
        }

        else -> {
          val coroutineScope = rememberCoroutineScope()
          val listPane: @Composable (CoroutineScope, MainScaffoldAction) -> Unit = { scope, action ->
            CalculatorInputScreen(
              mobileWindowSize = action.mobileWindowSize,
              onCalculationReady = { amountText ->
                scope.launch {
                  action.gotoDetail(AppDestination.Calculating(amountText))
                }
              },
              onHistoryButtonClicked = {},
              onRatesButtonClicked = {},
              onSettingsButtonClicked = {
                scope.launch {
                  action.gotoDetail(AppDestination.Settings)
                }
              },
            )
          }

          MainScaffold(
            listPaneContent = { scaffoldAction -> listPane(coroutineScope, scaffoldAction) },
            detailPaneContent = { scaffoldAction ->
              val currentDestination = scaffoldAction.currentDestination
              when (currentDestination) {
                is AppDestination.Calculating -> {
                  CalculatorOutputScreen(
                    mobileWindowSize = scaffoldAction.mobileWindowSize,
                    requestedAmount = currentDestination.amountText,
                    onCloseButtonClicked = {
                      coroutineScope.launch {
                        scaffoldAction.goBack()
                      }
                    },
                  )
                }

                AppDestination.History -> {}

                AppDestination.Settings -> {
                  SettingsRoute(
                    windowSizeInfo = windowSizeInfo,
                    mainActions = mainActions,
                    onBackNavigationAction = {
                      coroutineScope.launch { scaffoldAction.goBack() }
                    },
                  )
                }

                else -> {
                  Box(
                    modifier = Modifier
                      .fillMaxSize()
                      .background(MaterialTheme.colorScheme.surfaceVariant)
                      .padding(20.dp),
                    contentAlignment = Alignment.Center,
                  ) {
                    Text(
                      text = "${scaffoldAction.mobileWindowSize}\n${scaffoldAction.foldState}",
                      color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                  }
                }
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
