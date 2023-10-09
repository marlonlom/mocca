/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package dev.marlonlom.apps.mocca.smartwatch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import dev.marlonlom.apps.mocca.smartwatch.R
import dev.marlonlom.apps.mocca.smartwatch.presentation.theme.MoccaTheme

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
      WearApp("Android")
    }
  }
}

@Composable
fun WearApp(greetingName: String) {
  MoccaTheme {
    val listState = rememberScalingLazyListState()

    Scaffold(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.surface),
      timeText = {
        if (!listState.isScrollInProgress) {
          TimeText()
        }
      },
      vignette = {
        Vignette(
          vignettePosition = VignettePosition.TopAndBottom
        )
      },
      positionIndicator = {
        PositionIndicator(
          scalingLazyListState = listState,
        )
      },
      content = {
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(MaterialTheme.colors.background)
            .selectableGroup(),
          verticalArrangement = Arrangement.Center
        ) {
          Greeting(greetingName = greetingName)
        }
      }
    )
  }
}

@Composable
fun Greeting(greetingName: String) {
  Text(
    modifier = Modifier.fillMaxWidth(),
    textAlign = TextAlign.Center,
    text = stringResource(R.string.hello_world, greetingName)
  )
}
