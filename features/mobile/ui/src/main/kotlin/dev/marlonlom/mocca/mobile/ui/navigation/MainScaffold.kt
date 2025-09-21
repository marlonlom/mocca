/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.PaneScaffoldDirective
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
 * @param adaptiveInfo The [WindowAdaptiveInfo] used to determine the layout. Defaults to the current window adaptive info.
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainScaffold(
  listPaneContent: @Composable (MainScaffoldAction) -> Unit,
  detailPaneContent: @Composable (MainScaffoldAction) -> Unit,
  adaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
) {
  val mobileWindowSize: MobileWindowSize =
    MobileWindowSize.fromWindowSizeClass(adaptiveInfo.windowSizeClass)
  val foldState: FoldState = FoldablePosture.getFoldState(adaptiveInfo.windowPosture)
  val navigator = rememberListDetailPaneScaffoldNavigator<AppDestination>(
    scaffoldDirective = when (mobileWindowSize) {
      MobileWindowSize.MOBILE_LANDSCAPE -> PaneScaffoldDirective(
        maxHorizontalPartitions = 1,
        horizontalPartitionSpacerSize = 0.dp,
        maxVerticalPartitions = 1,
        verticalPartitionSpacerSize = 0.dp,
        defaultPanePreferredWidth = 360.dp,
        excludedBounds = emptyList(),
      )

      else -> calculatePaneScaffoldDirective(adaptiveInfo)
    },
  )
  val scaffoldAction = MainScaffoldAction(mobileWindowSize, foldState, navigator)
  NavigableListDetailPaneScaffold(
    modifier = Modifier
      .background(MaterialTheme.colorScheme.background)
      .fillMaxWidth()
      .safeContentPadding(),
    navigator = navigator,
    defaultBackBehavior = BackNavigationBehavior.PopUntilScaffoldValueChange,
    listPane = { listPaneContent(scaffoldAction) },
    detailPane = { detailPaneContent(scaffoldAction) },
  )
}
