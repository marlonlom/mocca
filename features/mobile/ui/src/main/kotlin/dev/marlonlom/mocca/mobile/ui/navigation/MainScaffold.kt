/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.mobile.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.marlonlom.mocca.mobile.ui.folding.FoldState
import dev.marlonlom.mocca.mobile.ui.folding.FoldablePosture
import dev.marlonlom.mocca.mobile.ui.window.MobileWindowSize

/**
 * A composable function that provides a main scaffold for adaptive layouts.
 *
 * @author marlonlom
 *
 * @param listPaneContent The composable content to be displayed in the list pane.
 * @param detailPaneContent The composable content to be displayed in the detail pane.
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainScaffold(
  listPaneContent: @Composable (MobileWindowSize, FoldState) -> Unit,
  detailPaneContent: @Composable (MobileWindowSize, FoldState) -> Unit,
) {
  val navigator = rememberListDetailPaneScaffoldNavigator<AppDestination>()
  val adaptiveInfo = currentWindowAdaptiveInfo()
  val mobileWindowSize: MobileWindowSize = MobileWindowSize.fromWindowSizeClass(adaptiveInfo.windowSizeClass)
  val foldState: FoldState = FoldablePosture.getFoldState(adaptiveInfo.windowPosture)
  NavigableListDetailPaneScaffold(
    modifier = Modifier
      .background(MaterialTheme.colorScheme.background)
      .fillMaxWidth()
      .safeContentPadding(),
    navigator = navigator,
    listPane = { listPaneContent(mobileWindowSize, foldState) },
    detailPane = { detailPaneContent(mobileWindowSize, foldState) },
  )
}
