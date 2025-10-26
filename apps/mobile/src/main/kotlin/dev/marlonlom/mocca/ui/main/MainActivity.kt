/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.contracts.ExperimentalContracts

/**
 * Mobile app main activity class.
 *
 * @author marlonlom
 */
@ExperimentalContracts
@ExperimentalMaterial3WindowSizeClassApi
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

  /** Main activity view model reference. */
  private val mainViewModel: MainViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val splashScreen = installSplashScreen()

    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)

    lifecycleScope.launch {
      lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        mainViewModel.uiState.onEach { mainUiState = it }.collect()
      }
    }

    splashScreen.setKeepOnScreenCondition {
      when (mainUiState) {
        MainUiState.Loading -> true
        is MainUiState.Success -> false
      }
    }

    WindowCompat.enableEdgeToEdge(window)

    setContent {
      MainContent(
        mainUiState = mainUiState,
        onOnboarded = mainViewModel::setOnboardingComplete,
      )
    }
  }
}
