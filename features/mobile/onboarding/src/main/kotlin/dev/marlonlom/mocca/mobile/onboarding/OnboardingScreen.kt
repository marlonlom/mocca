/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.onboarding

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowDpSize
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import dev.marlonlom.mocca.mobile.onboarding.layout.OnboardingColumnContent
import dev.marlonlom.mocca.mobile.onboarding.layout.OnboardingRowContent
import dev.marlonlom.mocca.mobile.ui.folding.FoldState
import dev.marlonlom.mocca.mobile.ui.folding.FoldablePosture
import dev.marlonlom.mocca.mobile.ui.window.MobileWindowSize

/**
 * A screen to guide users through the onboarding process.
 *
 * @author marlonlom
 *
 * @param onOnboarded The action to perform when the onboarding process is completed.
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun OnboardingScreen(onOnboarded: () -> Unit) {
  val mobileLayout = MobileWindowSize.fromWindowSizeClass(
    windowSizeClass = WindowSizeClass.calculateFromSize(currentWindowDpSize()),
  )
  val foldState: FoldState = FoldablePosture.getFoldState(
    windowPosture = currentWindowAdaptiveInfo().windowPosture,
  )

  when (mobileLayout) {
    MobileWindowSize.MOBILE_PORTRAIT -> {
      OnboardingColumnContent(onOnboarded = { onOnboarded() })
    }

    MobileWindowSize.MOBILE_LANDSCAPE -> {
      OnboardingRowContent(onOnboarded = { onOnboarded() })
    }

    MobileWindowSize.TABLET_PORTRAIT -> {
      OnboardingColumnContent(
        onOnboarded = { onOnboarded() },
        widthFraction = 0.60f,
        heightFraction = 0.75f,
      )
    }

    MobileWindowSize.TABLET_LANDSCAPE, MobileWindowSize.DESKTOP -> {
      OnboardingRowContent(
        onOnboarded = { onOnboarded() },
        widthFraction = if (foldState == FoldState.Flat) 0.75f else 1.0f,
      )
    }
  }
}
