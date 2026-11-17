/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.marlonlom.mocca.wearos.ui.theme.MoccaWearosTheme

/**
 * Smartwatch activity class.
 *
 * @author marlonlom
 */
class SmartwatchActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    setContent {
      MoccaWearosTheme {
        val wearAppState = remember {
          derivedStateOf {
            WearAppState.fromIntent(intent = intent)
          }
        }
        WearAppContent(wearAppState = wearAppState.value)
      }
    }
  }
}
