/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca

import android.content.Intent
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorViewModel
import dev.marlonlom.apps.mocca.feats.settings.SettingsRepository
import dev.marlonlom.apps.mocca.feats.settings.SettingsViewModel
import dev.marlonlom.apps.mocca.feats.settings.UserPreferences
import dev.marlonlom.apps.mocca.ui.common.MainScaffold
import dev.marlonlom.apps.mocca.ui.theme.MoccaTheme
import dev.marlonlom.apps.mocca.ui.util.CustomTabsOpener
import dev.marlonlom.apps.mocca.ui.util.FeedbackOpener
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil
import timber.log.Timber

/**
 * Main activity class.
 *
 * @author marlonlom
 */
@ExperimentalMaterial3WindowSizeClassApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

  /** Calculator view model reference. */
  private val calculatorViewModel by viewModels<CalculatorViewModel> { CalculatorViewModel.Factory }

  /** Settings view model reference. */
  private val settingsViewModel by viewModels<SettingsViewModel> {
    SettingsViewModel.factory(SettingsRepository(this.dataStore))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    installSplashScreen()

    setContent {

      val windowSizeClass = calculateWindowSizeClass(activity = this)
      val windowSizeUtil = WindowSizeUtil(
        windowSizeClass = windowSizeClass,
        isLandscape = LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE,
        isTabletWidth = LocalConfiguration.current.smallestScreenWidthDp >= 600
      )
      val settingsState = settingsViewModel.settingsUiState.collectAsStateWithLifecycle()

      AppContent(settingsState, windowSizeUtil)
    }
  }


  @Composable
  private fun AppContent(
    settingsState: State<UserPreferences>,
    windowSizeUtil: WindowSizeUtil
  ) {
    MoccaTheme(
      darkTheme = if (isSystemInDarkTheme()) true else settingsState.value.darkTheme,
      dynamicColor = settingsState.value.dynamicColors
    ) {
      val calculationState by calculatorViewModel.uiState.collectAsStateWithLifecycle()
      MainScaffold(
        windowSizeUtil = windowSizeUtil,
        calculationState = calculationState,
        doCalculation = calculatorViewModel::calculate,
        onClearedAmountText = calculatorViewModel::reset,
        settingsUiState = settingsState,
        onBooleanSettingChanged = settingsViewModel::toggleBooleanPreference,
        onOssLicencesSettingLinkClicked = {
          startActivity(Intent(this, OssLicensesMenuActivity::class.java))
        },
        onOpeningExternalUrlSettingClicked = { urlText ->
          Timber.d("[AppContent] opening external url: $urlText")
          if (urlText.isNotEmpty()) {
            CustomTabsOpener.openUrl(this, urlText)
          }
        }
      ) {
        Timber.d("[AppContent] opening feedback window")
        FeedbackOpener.rate(this)
      }
    }
  }

}
