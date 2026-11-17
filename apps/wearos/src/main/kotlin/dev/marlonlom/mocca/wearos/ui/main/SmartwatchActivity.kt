/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.marlonlom.mocca.wearos.ui.theme.MoccaWearosTheme

/**
 * Smartwatch activity class.
 *
 * @author marlonlom
 */
class SmartwatchActivity : ComponentActivity() {

  /** Current intent holding state or action information. */
  private var currentIntent by mutableStateOf<Intent?>(null)

  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    currentIntent = intent
    setupContent()
  }

  override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    setIntent(intent)
    currentIntent = intent
    setupContent()
  }

  /** Sets up the Compose content and evaluates the application state from the current intent. */
  private fun setupContent() {
    setContent {
      MoccaWearosTheme {
        val wearAppState = remember(currentIntent) {
          derivedStateOf {
            val state = WearAppState.fromIntent(intent = currentIntent)
            Log.d("SmartwatchActivity", "state=$state")
            currentIntent?.removeExtra(WearAppState.EXTRA_CALCULATING_AMOUNT)
            currentIntent?.removeExtra(WearAppState.EXTRA_WIDGET_ACTION)
            state
          }
        }
        WearAppContent(wearAppState = { wearAppState.value })
      }
    }
  }
}
