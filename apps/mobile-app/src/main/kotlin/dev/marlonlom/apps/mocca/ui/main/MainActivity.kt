/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.main

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dev.marlonlom.apps.mocca.dataStore
import dev.marlonlom.apps.mocca.feats.settings.SettingsRepository
import dev.marlonlom.apps.mocca.ui.common.AppScaffold
import dev.marlonlom.apps.mocca.ui.theme.MoccaTheme
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Main activity class.
 *
 * @author marlonlom
 */
@ExperimentalMaterial3WindowSizeClassApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

  /** Main activity view model reference. */
  private val mainViewModel by viewModels<MainViewModel> {
    MainViewModel.factory(SettingsRepository(this.dataStore))
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val splashScreen = installSplashScreen()

    var mainActivityUiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

    lifecycleScope.launch {
      lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        mainViewModel.uiState.onEach { mainActivityUiState = it }.collect()
      }
    }

    splashScreen.setKeepOnScreenCondition {
      when (mainActivityUiState) {
        MainActivityUiState.Loading -> true
        is MainActivityUiState.Success -> false
      }
    }

    enableEdgeToEdge()

    setContent {
      val windowSizeClass = calculateWindowSizeClass(activity = this)
      val windowSizeUtil = WindowSizeUtil(
        windowSizeClass = windowSizeClass,
        isLandscape = LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE,
        isTabletWidth = LocalConfiguration.current.smallestScreenWidthDp >= 600
      )
      Timber.d("[MainActivity] mainActivityUiState=$mainActivityUiState")
      AppContent(mainActivityUiState, windowSizeUtil)
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

  @Composable
  private fun AppContent(
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

}
