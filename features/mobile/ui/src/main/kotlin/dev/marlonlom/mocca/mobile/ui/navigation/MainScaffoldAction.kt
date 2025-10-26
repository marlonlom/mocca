/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.navigation

import android.util.Log
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import dev.marlonlom.mocca.mobile.ui.folding.FoldState
import dev.marlonlom.mocca.mobile.ui.window.MobileWindowSize

/**
 * Represents the main scaffold action handler for navigating between panes
 * in a three-pane adaptive UI using Material3.
 *
 * @author marlonlom
 *
 * @param mobileWindowSize The current window size classification for mobile layout adaptation.
 * @param foldState The current fold posture state of the device (e.g., folded, unfolded).
 * @param navigator A navigator that manages navigation between panes using [ThreePaneScaffoldNavigator].
 *
 * @see ExperimentalMaterial3AdaptiveApi
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
class MainScaffoldAction(
  val mobileWindowSize: MobileWindowSize,
  val foldState: FoldState,
  private val navigator: ThreePaneScaffoldNavigator<AppDestination>,
) {

  /**
   * The currently active [AppDestination] or [AppDestination.Calculator] if none is active.
   */
  val currentDestination = navigator.currentDestination?.contentKey ?: AppDestination.Calculator

  /**
   * Navigates to the given [destination] and opens it in the secondary pane
   * of the three-pane scaffold layout.
   *
   * @param destination The [AppDestination] to navigate to.
   */
  suspend fun gotoDetail(destination: AppDestination) {
    Log.d("MainScaffoldAction", "gotoDetail: destination=$destination")
    navigator.navigateTo(
      pane = ThreePaneScaffoldRole.Primary,
      contentKey = destination,
    )
  }

  suspend fun goBack() {
    navigator.navigateBack()
  }

  fun arePrimarySecondaryPanesExpanded(): Boolean = navigator.scaffoldValue.let {
    return it.primary == PaneAdaptedValue.Expanded && it.secondary == PaneAdaptedValue.Expanded
  }
}
