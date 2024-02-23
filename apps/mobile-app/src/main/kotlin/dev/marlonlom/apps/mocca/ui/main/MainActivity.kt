/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.ui.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.window.layout.WindowInfoTracker
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dev.marlonlom.apps.mocca.dataStore
import dev.marlonlom.apps.mocca.feats.settings.SettingsRepository
import dev.marlonlom.apps.mocca.ui.util.CustomTabsOpener
import dev.marlonlom.apps.mocca.ui.util.DevicePosture
import dev.marlonlom.apps.mocca.ui.util.DevicePostureDetector
import dev.marlonlom.apps.mocca.ui.util.FeedbackOpener
import dev.marlonlom.apps.mocca.ui.util.WindowSizeInfo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.contracts.ExperimentalContracts

/**
 * Main activity class.
 *
 * @author marlonlom
 */
@ExperimentalContracts
@ExperimentalMaterial3WindowSizeClassApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

  /** Main activity view model reference. */
  private val mainViewModel by viewModels<MainViewModel> {
    MainViewModel.factory(SettingsRepository(this.dataStore))
  }

  private val devicePostureFlow = WindowInfoTracker
    .getOrCreate(this@MainActivity)
    .windowLayoutInfo(this@MainActivity)
    .flowWithLifecycle(lifecycle)
    .map { layoutInfo -> DevicePostureDetector.fromLayoutInfo(layoutInfo) }
    .stateIn(
      scope = lifecycleScope,
      started = SharingStarted.Eagerly,
      initialValue = DevicePosture.NormalPosture
    )

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
      val configuration = LocalConfiguration.current
      val windowSizeClass = calculateWindowSizeClass(activity = this)
      val devicePostureState by devicePostureFlow.collectAsStateWithLifecycle()
      val windowSizeInfo = WindowSizeInfo(
        windowSizeClass = windowSizeClass,
        devicePosture = devicePostureState,
        isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE,
        isTabletWidth = configuration.smallestScreenWidthDp >= 600
      )
      Timber.d("[MainActivity] devicePosture=$devicePostureState; windowSizeClass=$windowSizeClass")
      MainContent(
        mainActivityUiState = mainActivityUiState,
        windowSizeInfo = windowSizeInfo,
        mainActions = MainActions(
          onOssLicencesSettingLinkClicked = {
            Timber.d("[MainActivity] opening oss licenses window")
            applicationContext.startActivity(
              Intent(applicationContext, OssLicensesMenuActivity::class.java)
            )
          },
          onOpeningExternalUrlSettingClicked = { urlText ->
            Timber.d("[MainActivity] opening external url: $urlText")
            if (urlText.isNotEmpty()) {
              CustomTabsOpener.openUrl(applicationContext, urlText)
            }
          },
          onFeedbackSettingLinkClicked = {
            Timber.d("[MainActivity] opening feedback window")
            FeedbackOpener.rate(applicationContext)
          }
        )
      )
    }
  }

}
