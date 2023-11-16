/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.smartwatch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.marlonlom.apps.mocca.smartwatch.presentation.feats.main.WearAppContent

/**
 * Smartwatch activity class.
 *
 * @author marlonlom
 */
class SmartwatchActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    installSplashScreen()
    setContent {
      WearAppContent()
    }
  }
}
