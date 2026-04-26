/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.main

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.oss.licenses.v2.OssLicensesMenuActivity
import dev.marlonlom.mocca.mobile.calculator.history.CalculatorHistoryScreen
import dev.marlonlom.mocca.mobile.calculator.input.CalculatorInputScreen
import dev.marlonlom.mocca.mobile.calculator.output.CalculatorOutputScreen
import dev.marlonlom.mocca.mobile.onboarding.OnboardingScreen
import dev.marlonlom.mocca.mobile.settings.SettingsScreen
import dev.marlonlom.mocca.mobile.settings.domain.SettingActions
import dev.marlonlom.mocca.mobile.ui.navigation.AppDestination
import dev.marlonlom.mocca.mobile.ui.navigation.MainScaffold
import dev.marlonlom.mocca.mobile.ui.navigation.MainScaffoldAction
import dev.marlonlom.mocca.mobile.ui.theme.MoccaTheme
import dev.marlonlom.mocca.mobile.ui.util.CustomTabsOpener
import dev.marlonlom.mocca.mobile.ui.util.FeedbackOpener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Main content composable ui.
 *
 * @author marlonlom
 *
 * @param mainUiState Main activity ui state.
 * @param onOnboarded Action for onboarding finished.
 */
@ExperimentalMaterial3Api
@Composable
internal fun MainContent(mainUiState: MainUiState, onOnboarded: () -> Unit) = MoccaTheme(
  darkTheme = mainUiState.shouldUseDarkTheme(),
  dynamicColor = mainUiState.shouldUseDynamicTheming(),
  colorContrast = mainUiState.shouldUseColorContrast(),
) {
  when (mainUiState) {
    is MainUiState.Success -> {
      when {
        mainUiState.userData.isOnboarding -> {
          Box(
            modifier = Modifier
              .fillMaxSize()
              .background(MaterialTheme.colorScheme.background)
              .systemBarsPadding()
              .consumeWindowInsets(WindowInsets.navigationBars),
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
              onHistoryButtonClicked = {
                scope.launch {
                  action.gotoDetail(AppDestination.History)
                }
              },
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
              when (val currentDestination = scaffoldAction.currentDestination) {
                AppDestination.History -> {
                  CalculatorHistoryScreen(
                    mobileWindowSize = scaffoldAction.mobileWindowSize,
                    showCloseButton = scaffoldAction.arePrimarySecondaryPanesExpanded().not(),
                    onCloseButtonClicked = {
                      coroutineScope.launch {
                        scaffoldAction.goBack()
                      }
                    },
                  )
                }

                AppDestination.Settings -> {
                  val currentCtx = LocalContext.current
                  SettingsScreen(
                    mobileWindowSize = scaffoldAction.mobileWindowSize,
                    showCloseButton = scaffoldAction.arePrimarySecondaryPanesExpanded().not(),
                    onCloseButtonClicked = {
                      coroutineScope.launch {
                        scaffoldAction.goBack()
                      }
                    },
                    actions = SettingActions(
                      onOssLicencesDisplayed = {
                        Log.d("MainContent", "Opening oss licenses window")
                        OssLicensesMenuActivity.setActivityTitle("Legal Notices")
                        currentCtx.startActivity(
                          Intent(currentCtx, OssLicensesMenuActivity::class.java),
                        )
                      },
                      onOpeningExternalUrl = { urlText ->
                        Log.d("MainContent", "Opening external url: $urlText")
                        if (urlText.isNotEmpty()) {
                          CustomTabsOpener.openUrl(currentCtx, urlText)
                        }
                      },
                      onFeedbackDisplayed = {
                        Log.d("MainContent", "Opening feedback window")
                        FeedbackOpener.rate(currentCtx)
                      },
                    ),
                  )
                }

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

                else -> {
                  CalculatorOutputScreen(
                    mobileWindowSize = scaffoldAction.mobileWindowSize,
                    requestedAmount = "",
                    onCloseButtonClicked = {
                      coroutineScope.launch {
                        scaffoldAction.goBack()
                      }
                    },
                  )
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
