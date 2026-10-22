/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.marlonlom.mocca.wearos.ui.theme.MoccaWearosTheme

/** Amount currently being calculated. */
private const val CALCULATING_AMOUNT = "calculatingAmount"

/**
 * Smartwatch activity class.
 *
 * @author marlonlom
 */
class SmartwatchActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    val calculatingAmount = intent?.getStringExtra(CALCULATING_AMOUNT)
    setContent {
      MoccaWearosTheme {
        WearAppContent(calculatingAmount = calculatingAmount)
      }
    }
  }
}
